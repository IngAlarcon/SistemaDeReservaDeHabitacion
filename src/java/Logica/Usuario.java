
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    int id_usuario;
    @Basic
    String nombre_usuario;
    String contrasenia;
    @OneToOne
    Empleado empleado;
    @OneToMany    
    List <Reserva> listaReservaEmpleado;
    

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario, String contrasenia, Empleado empleado, List<Reserva> listaReservaEmpleado) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.empleado = empleado;
        this.listaReservaEmpleado = listaReservaEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    public List<Reserva> getListaReservaEmpleado() {
        return listaReservaEmpleado;
    }

    public void setListaReservaEmpleado(List<Reserva> listaReservaEmpleado) {
        this.listaReservaEmpleado = listaReservaEmpleado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
    
    
}
