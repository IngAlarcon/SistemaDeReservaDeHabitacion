/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Huesped;
import Logica.Usuario;
import Logica.Habitacion;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Chango
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ReservaJpaController() {
        
        emf = Persistence.createEntityManagerFactory("SistemaDeReservaDeHabitacion_PU");    


    }

    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped reservaHuesped = reserva.getReservaHuesped();
            if (reservaHuesped != null) {
                reservaHuesped = em.getReference(reservaHuesped.getClass(), reservaHuesped.getId_persona());
                reserva.setReservaHuesped(reservaHuesped);
            }
            Usuario reservaUsuario = reserva.getReservaUsuario();
            if (reservaUsuario != null) {
                reservaUsuario = em.getReference(reservaUsuario.getClass(), reservaUsuario.getId_usuario());
                reserva.setReservaUsuario(reservaUsuario);
            }
            Habitacion tipoHabitacion = reserva.getTipoHabitacion();
            if (tipoHabitacion != null) {
                tipoHabitacion = em.getReference(tipoHabitacion.getClass(), tipoHabitacion.getId_habitacion());
                reserva.setTipoHabitacion(tipoHabitacion);
            }
            em.persist(reserva);
            if (reservaHuesped != null) {
                reservaHuesped.getListaReservaHuesped().add(reserva);
                reservaHuesped = em.merge(reservaHuesped);
            }
            if (reservaUsuario != null) {
                reservaUsuario.getListaReservaEmpleado().add(reserva);
                reservaUsuario = em.merge(reservaUsuario);
            }
            if (tipoHabitacion != null) {
                tipoHabitacion.getListaReservaHabitacion().add(reserva);
                tipoHabitacion = em.merge(tipoHabitacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getId_reserva());
            Huesped reservaHuespedOld = persistentReserva.getReservaHuesped();
            Huesped reservaHuespedNew = reserva.getReservaHuesped();
            Usuario reservaUsuarioOld = persistentReserva.getReservaUsuario();
            Usuario reservaUsuarioNew = reserva.getReservaUsuario();
            Habitacion tipoHabitacionOld = persistentReserva.getTipoHabitacion();
            Habitacion tipoHabitacionNew = reserva.getTipoHabitacion();
            if (reservaHuespedNew != null) {
                reservaHuespedNew = em.getReference(reservaHuespedNew.getClass(), reservaHuespedNew.getId_persona());
                reserva.setReservaHuesped(reservaHuespedNew);
            }
            if (reservaUsuarioNew != null) {
                reservaUsuarioNew = em.getReference(reservaUsuarioNew.getClass(), reservaUsuarioNew.getId_usuario());
                reserva.setReservaUsuario(reservaUsuarioNew);
            }
            if (tipoHabitacionNew != null) {
                tipoHabitacionNew = em.getReference(tipoHabitacionNew.getClass(), tipoHabitacionNew.getId_habitacion());
                reserva.setTipoHabitacion(tipoHabitacionNew);
            }
            reserva = em.merge(reserva);
            if (reservaHuespedOld != null && !reservaHuespedOld.equals(reservaHuespedNew)) {
                reservaHuespedOld.getListaReservaHuesped().remove(reserva);
                reservaHuespedOld = em.merge(reservaHuespedOld);
            }
            if (reservaHuespedNew != null && !reservaHuespedNew.equals(reservaHuespedOld)) {
                reservaHuespedNew.getListaReservaHuesped().add(reserva);
                reservaHuespedNew = em.merge(reservaHuespedNew);
            }
            if (reservaUsuarioOld != null && !reservaUsuarioOld.equals(reservaUsuarioNew)) {
                reservaUsuarioOld.getListaReservaEmpleado().remove(reserva);
                reservaUsuarioOld = em.merge(reservaUsuarioOld);
            }
            if (reservaUsuarioNew != null && !reservaUsuarioNew.equals(reservaUsuarioOld)) {
                reservaUsuarioNew.getListaReservaEmpleado().add(reserva);
                reservaUsuarioNew = em.merge(reservaUsuarioNew);
            }
            if (tipoHabitacionOld != null && !tipoHabitacionOld.equals(tipoHabitacionNew)) {
                tipoHabitacionOld.getListaReservaHabitacion().remove(reserva);
                tipoHabitacionOld = em.merge(tipoHabitacionOld);
            }
            if (tipoHabitacionNew != null && !tipoHabitacionNew.equals(tipoHabitacionOld)) {
                tipoHabitacionNew.getListaReservaHabitacion().add(reserva);
                tipoHabitacionNew = em.merge(tipoHabitacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reserva.getId_reserva();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
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
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getId_reserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Huesped reservaHuesped = reserva.getReservaHuesped();
            if (reservaHuesped != null) {
                reservaHuesped.getListaReservaHuesped().remove(reserva);
                reservaHuesped = em.merge(reservaHuesped);
            }
            Usuario reservaUsuario = reserva.getReservaUsuario();
            if (reservaUsuario != null) {
                reservaUsuario.getListaReservaEmpleado().remove(reserva);
                reservaUsuario = em.merge(reservaUsuario);
            }
            Habitacion tipoHabitacion = reserva.getTipoHabitacion();
            if (tipoHabitacion != null) {
                tipoHabitacion.getListaReservaHabitacion().remove(reserva);
                tipoHabitacion = em.merge(tipoHabitacion);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
