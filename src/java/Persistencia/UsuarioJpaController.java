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
import Logica.Reserva;
import Logica.Usuario;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public UsuarioJpaController() {

        emf = Persistence.createEntityManagerFactory("SistemaDeReservaDeHabitacion_PU");    


    }
    
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getListaReservaEmpleado() == null) {
            usuario.setListaReservaEmpleado(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaReservaEmpleado = new ArrayList<Reserva>();
            for (Reserva listaReservaEmpleadoReservaToAttach : usuario.getListaReservaEmpleado()) {
                listaReservaEmpleadoReservaToAttach = em.getReference(listaReservaEmpleadoReservaToAttach.getClass(), listaReservaEmpleadoReservaToAttach.getId_reserva());
                attachedListaReservaEmpleado.add(listaReservaEmpleadoReservaToAttach);
            }
            usuario.setListaReservaEmpleado(attachedListaReservaEmpleado);
            em.persist(usuario);
            for (Reserva listaReservaEmpleadoReserva : usuario.getListaReservaEmpleado()) {
                Usuario oldReservaUsuarioOfListaReservaEmpleadoReserva = listaReservaEmpleadoReserva.getReservaUsuario();
                listaReservaEmpleadoReserva.setReservaUsuario(usuario);
                listaReservaEmpleadoReserva = em.merge(listaReservaEmpleadoReserva);
                if (oldReservaUsuarioOfListaReservaEmpleadoReserva != null) {
                    oldReservaUsuarioOfListaReservaEmpleadoReserva.getListaReservaEmpleado().remove(listaReservaEmpleadoReserva);
                    oldReservaUsuarioOfListaReservaEmpleadoReserva = em.merge(oldReservaUsuarioOfListaReservaEmpleadoReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId_usuario());
            List<Reserva> listaReservaEmpleadoOld = persistentUsuario.getListaReservaEmpleado();
            List<Reserva> listaReservaEmpleadoNew = usuario.getListaReservaEmpleado();
            List<Reserva> attachedListaReservaEmpleadoNew = new ArrayList<Reserva>();
            for (Reserva listaReservaEmpleadoNewReservaToAttach : listaReservaEmpleadoNew) {
                listaReservaEmpleadoNewReservaToAttach = em.getReference(listaReservaEmpleadoNewReservaToAttach.getClass(), listaReservaEmpleadoNewReservaToAttach.getId_reserva());
                attachedListaReservaEmpleadoNew.add(listaReservaEmpleadoNewReservaToAttach);
            }
            listaReservaEmpleadoNew = attachedListaReservaEmpleadoNew;
            usuario.setListaReservaEmpleado(listaReservaEmpleadoNew);
            usuario = em.merge(usuario);
            for (Reserva listaReservaEmpleadoOldReserva : listaReservaEmpleadoOld) {
                if (!listaReservaEmpleadoNew.contains(listaReservaEmpleadoOldReserva)) {
                    listaReservaEmpleadoOldReserva.setReservaUsuario(null);
                    listaReservaEmpleadoOldReserva = em.merge(listaReservaEmpleadoOldReserva);
                }
            }
            for (Reserva listaReservaEmpleadoNewReserva : listaReservaEmpleadoNew) {
                if (!listaReservaEmpleadoOld.contains(listaReservaEmpleadoNewReserva)) {
                    Usuario oldReservaUsuarioOfListaReservaEmpleadoNewReserva = listaReservaEmpleadoNewReserva.getReservaUsuario();
                    listaReservaEmpleadoNewReserva.setReservaUsuario(usuario);
                    listaReservaEmpleadoNewReserva = em.merge(listaReservaEmpleadoNewReserva);
                    if (oldReservaUsuarioOfListaReservaEmpleadoNewReserva != null && !oldReservaUsuarioOfListaReservaEmpleadoNewReserva.equals(usuario)) {
                        oldReservaUsuarioOfListaReservaEmpleadoNewReserva.getListaReservaEmpleado().remove(listaReservaEmpleadoNewReserva);
                        oldReservaUsuarioOfListaReservaEmpleadoNewReserva = em.merge(oldReservaUsuarioOfListaReservaEmpleadoNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId_usuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId_usuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaReservaEmpleado = usuario.getListaReservaEmpleado();
            for (Reserva listaReservaEmpleadoReserva : listaReservaEmpleado) {
                listaReservaEmpleadoReserva.setReservaUsuario(null);
                listaReservaEmpleadoReserva = em.merge(listaReservaEmpleadoReserva);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
