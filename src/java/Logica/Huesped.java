
package Logica;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/*Aplico herencia huesped hereda de persona*/
@Entity
public class Huesped extends Persona implements Serializable {
    
    @Basic
    String profesion;
    @OneToMany 
    List <Reserva> listaReservaHuesped;

    public Huesped() {
    }

    public Huesped(String profesion, List<Reserva> listaReservaHuesped, int id_persona, String nombre, String apellido, String dni, Date fecha_nac, String direccion) {
        super(id_persona, nombre, apellido, dni, fecha_nac, direccion);
        this.profesion = profesion;
        this.listaReservaHuesped = listaReservaHuesped;
    }

    public List<Reserva> getListaReservaHuesped() {
        return listaReservaHuesped;
    }

    public void setListaReservaHuesped(List<Reserva> listaReservaHuesped) {
        this.listaReservaHuesped = listaReservaHuesped;
    }



    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public int getId_persona() {
        return id_persona;
    }

    @Override
    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    @Override
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public Date getFecha_nac() {
        return fecha_nac;
    }

    @Override
    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    


    
}
