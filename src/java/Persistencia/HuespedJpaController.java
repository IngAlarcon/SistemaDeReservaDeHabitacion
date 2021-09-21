/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Huesped;
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
public class HuespedJpaController implements Serializable {

    public HuespedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public HuespedJpaController() {

        emf = Persistence.createEntityManagerFactory("SistemaDeReservaDeHabitacion_PU");    


    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Huesped huesped) {
        if (huesped.getListaReservaHuesped() == null) {
            huesped.setListaReservaHuesped(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaReservaHuesped = new ArrayList<Reserva>();
            for (Reserva listaReservaHuespedReservaToAttach : huesped.getListaReservaHuesped()) {
                listaReservaHuespedReservaToAttach = em.getReference(listaReservaHuespedReservaToAttach.getClass(), listaReservaHuespedReservaToAttach.getId_reserva());
                attachedListaReservaHuesped.add(listaReservaHuespedReservaToAttach);
            }
            huesped.setListaReservaHuesped(attachedListaReservaHuesped);
            em.persist(huesped);
            for (Reserva listaReservaHuespedReserva : huesped.getListaReservaHuesped()) {
                Huesped oldReservaHuespedOfListaReservaHuespedReserva = listaReservaHuespedReserva.getReservaHuesped();
                listaReservaHuespedReserva.setReservaHuesped(huesped);
                listaReservaHuespedReserva = em.merge(listaReservaHuespedReserva);
                if (oldReservaHuespedOfListaReservaHuespedReserva != null) {
                    oldReservaHuespedOfListaReservaHuespedReserva.getListaReservaHuesped().remove(listaReservaHuespedReserva);
                    oldReservaHuespedOfListaReservaHuespedReserva = em.merge(oldReservaHuespedOfListaReservaHuespedReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huesped huesped) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped persistentHuesped = em.find(Huesped.class, huesped.getId_persona());
            List<Reserva> listaReservaHuespedOld = persistentHuesped.getListaReservaHuesped();
            List<Reserva> listaReservaHuespedNew = huesped.getListaReservaHuesped();
            List<Reserva> attachedListaReservaHuespedNew = new ArrayList<Reserva>();
            for (Reserva listaReservaHuespedNewReservaToAttach : listaReservaHuespedNew) {
                listaReservaHuespedNewReservaToAttach = em.getReference(listaReservaHuespedNewReservaToAttach.getClass(), listaReservaHuespedNewReservaToAttach.getId_reserva());
                attachedListaReservaHuespedNew.add(listaReservaHuespedNewReservaToAttach);
            }
            listaReservaHuespedNew = attachedListaReservaHuespedNew;
            huesped.setListaReservaHuesped(listaReservaHuespedNew);
            huesped = em.merge(huesped);
            for (Reserva listaReservaHuespedOldReserva : listaReservaHuespedOld) {
                if (!listaReservaHuespedNew.contains(listaReservaHuespedOldReserva)) {
                    listaReservaHuespedOldReserva.setReservaHuesped(null);
                    listaReservaHuespedOldReserva = em.merge(listaReservaHuespedOldReserva);
                }
            }
            for (Reserva listaReservaHuespedNewReserva : listaReservaHuespedNew) {
                if (!listaReservaHuespedOld.contains(listaReservaHuespedNewReserva)) {
                    Huesped oldReservaHuespedOfListaReservaHuespedNewReserva = listaReservaHuespedNewReserva.getReservaHuesped();
                    listaReservaHuespedNewReserva.setReservaHuesped(huesped);
                    listaReservaHuespedNewReserva = em.merge(listaReservaHuespedNewReserva);
                    if (oldReservaHuespedOfListaReservaHuespedNewReserva != null && !oldReservaHuespedOfListaReservaHuespedNewReserva.equals(huesped)) {
                        oldReservaHuespedOfListaReservaHuespedNewReserva.getListaReservaHuesped().remove(listaReservaHuespedNewReserva);
                        oldReservaHuespedOfListaReservaHuespedNewReserva = em.merge(oldReservaHuespedOfListaReservaHuespedNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = huesped.getId_persona();
                if (findHuesped(id) == null) {
                    throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.");
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
            Huesped huesped;
            try {
                huesped = em.getReference(Huesped.class, id);
                huesped.getId_persona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaReservaHuesped = huesped.getListaReservaHuesped();
            for (Reserva listaReservaHuespedReserva : listaReservaHuesped) {
                listaReservaHuespedReserva.setReservaHuesped(null);
                listaReservaHuespedReserva = em.merge(listaReservaHuespedReserva);
            }
            em.remove(huesped);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huesped> findHuespedEntities() {
        return findHuespedEntities(true, -1, -1);
    }

    public List<Huesped> findHuespedEntities(int maxResults, int firstResult) {
        return findHuespedEntities(false, maxResults, firstResult);
    }

    private List<Huesped> findHuespedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huesped.class));
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

    public Huesped findHuesped(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huesped.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuespedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huesped> rt = cq.from(Huesped.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
