/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Habitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Chango
 */
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("SistemaDeReservaDeHabitacion_PU");    


    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        if (habitacion.getListaReservaHabitacion() == null) {
            habitacion.setListaReservaHabitacion(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaReservaHabitacion = new ArrayList<Reserva>();
            for (Reserva listaReservaHabitacionReservaToAttach : habitacion.getListaReservaHabitacion()) {
                listaReservaHabitacionReservaToAttach = em.getReference(listaReservaHabitacionReservaToAttach.getClass(), listaReservaHabitacionReservaToAttach.getId_reserva());
                attachedListaReservaHabitacion.add(listaReservaHabitacionReservaToAttach);
            }
            habitacion.setListaReservaHabitacion(attachedListaReservaHabitacion);
            em.persist(habitacion);
            for (Reserva listaReservaHabitacionReserva : habitacion.getListaReservaHabitacion()) {
                Habitacion oldTipoHabitacionOfListaReservaHabitacionReserva = listaReservaHabitacionReserva.getTipoHabitacion();
                listaReservaHabitacionReserva.setTipoHabitacion(habitacion);
                listaReservaHabitacionReserva = em.merge(listaReservaHabitacionReserva);
                if (oldTipoHabitacionOfListaReservaHabitacionReserva != null) {
                    oldTipoHabitacionOfListaReservaHabitacionReserva.getListaReservaHabitacion().remove(listaReservaHabitacionReserva);
                    oldTipoHabitacionOfListaReservaHabitacionReserva = em.merge(oldTipoHabitacionOfListaReservaHabitacionReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getId_habitacion());
            List<Reserva> listaReservaHabitacionOld = persistentHabitacion.getListaReservaHabitacion();
            List<Reserva> listaReservaHabitacionNew = habitacion.getListaReservaHabitacion();
            List<Reserva> attachedListaReservaHabitacionNew = new ArrayList<Reserva>();
            for (Reserva listaReservaHabitacionNewReservaToAttach : listaReservaHabitacionNew) {
                listaReservaHabitacionNewReservaToAttach = em.getReference(listaReservaHabitacionNewReservaToAttach.getClass(), listaReservaHabitacionNewReservaToAttach.getId_reserva());
                attachedListaReservaHabitacionNew.add(listaReservaHabitacionNewReservaToAttach);
            }
            listaReservaHabitacionNew = attachedListaReservaHabitacionNew;
            habitacion.setListaReservaHabitacion(listaReservaHabitacionNew);
            habitacion = em.merge(habitacion);
            for (Reserva listaReservaHabitacionOldReserva : listaReservaHabitacionOld) {
                if (!listaReservaHabitacionNew.contains(listaReservaHabitacionOldReserva)) {
                    listaReservaHabitacionOldReserva.setTipoHabitacion(null);
                    listaReservaHabitacionOldReserva = em.merge(listaReservaHabitacionOldReserva);
                }
            }
            for (Reserva listaReservaHabitacionNewReserva : listaReservaHabitacionNew) {
                if (!listaReservaHabitacionOld.contains(listaReservaHabitacionNewReserva)) {
                    Habitacion oldTipoHabitacionOfListaReservaHabitacionNewReserva = listaReservaHabitacionNewReserva.getTipoHabitacion();
                    listaReservaHabitacionNewReserva.setTipoHabitacion(habitacion);
                    listaReservaHabitacionNewReserva = em.merge(listaReservaHabitacionNewReserva);
                    if (oldTipoHabitacionOfListaReservaHabitacionNewReserva != null && !oldTipoHabitacionOfListaReservaHabitacionNewReserva.equals(habitacion)) {
                        oldTipoHabitacionOfListaReservaHabitacionNewReserva.getListaReservaHabitacion().remove(listaReservaHabitacionNewReserva);
                        oldTipoHabitacionOfListaReservaHabitacionNewReserva = em.merge(oldTipoHabitacionOfListaReservaHabitacionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = habitacion.getId_habitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId_habitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaReservaHabitacion = habitacion.getListaReservaHabitacion();
            for (Reserva listaReservaHabitacionReserva : listaReservaHabitacion) {
                listaReservaHabitacionReserva.setTipoHabitacion(null);
                listaReservaHabitacionReserva = em.merge(listaReservaHabitacionReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Habitacion findHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
