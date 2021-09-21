
package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    
    //  Inicializo cada uno de mis JPA controler mediante una insatancia en una variable   
   
    PersonaJpaController personaJPA = new PersonaJpaController();
    HuespedJpaController huespedJPA = new HuespedJpaController();
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();    
    HabitacionJpaController habitacionJPA = new HabitacionJpaController();
    ReservaJpaController reservaJPA = new ReservaJpaController();

    

    public void crearHabitacion(Habitacion nuevaHabitacion) {

        habitacionJPA.create(nuevaHabitacion);

    }

    public List<Habitacion> traerListaHabitaciones() {
    
        return habitacionJPA.findHabitacionEntities();
   
    }

    public void eliminarHabitacion(int id) {
        try {
            
            habitacionJPA.destroy(id);
        
        } catch (NonexistentEntityException ex) {
        
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    public Habitacion buscarHabitacion(int id) {
       
         return habitacionJPA.findHabitacion(id);
         
    }

    public void actualizarHabitacion(Habitacion editHabitacion) {
        
        try {
            
            habitacionJPA.edit(editHabitacion);
        
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Usuario> traerListaUsuarios() {
       
       return usuarioJPA.findUsuarioEntities();
        
        
    }

    public void crearEmpleado(Empleado nuevoEmpleado) {
       
        empleadoJPA.create(nuevoEmpleado);
   
    
    }

    public Empleado buscarEmpleadoAdmin(int idAdmin) {
        
        return empleadoJPA.findEmpleado(idAdmin);
   
    }

    public void crearUsuario(Usuario nuevoUsuario) {
        
        usuarioJPA.create(nuevoUsuario);
    }

    public List<Empleado> traerListaEmpleados() {
        
        return empleadoJPA.findEmpleadoEntities();
        
        
    }

    public void crearHuesped(Huesped nuevoHuesped) {
        
       huespedJPA.create(nuevoHuesped);
    
    }

    public List<Huesped> traerListaHuespedes() {
        
        return huespedJPA.findHuespedEntities();
    }

    public Huesped buscarHuesped(int huespedReserva) {
        
        return huespedJPA.findHuesped(huespedReserva);
    }

    public Usuario buscarUsuario(int idUsuario) {
        
        return usuarioJPA.findUsuario(idUsuario);
    }

    public void crearReserva(Reserva nuevaReserva) {

        reservaJPA.create(nuevaReserva);

    }

    public Habitacion buscarHabitacionParaReserva(int idHabitacion) {

            return habitacionJPA.findHabitacion(idHabitacion);

    }

    public List<Reserva> traerListaReservas() {
        
        return reservaJPA.findReservaEntities();
   
    
    }

    public void eliminarHuesped(int id) {

        try {
            huespedJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void actualizarHuesped(Huesped editHuesped) {
        
        try {
            huespedJPA.edit(editHuesped);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void eliminarReserva(int id) {
        
        try {
            reservaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    public Reserva buscarReserva(int id) {
        
        return reservaJPA.findReserva(id);
    }

    public void actualizarReserva(Reserva editReserva) {

        try {
            reservaJPA.edit(editReserva);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

}
