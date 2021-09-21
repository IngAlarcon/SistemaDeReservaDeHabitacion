
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Habitacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)        
    int id_habitacion;
    @Basic
    int piso;
    int num_habitacion;
    String tematica;
    int tipo_habitacion;
    double precio_noche;
     @OneToMany 
     //@CascadeOnDelete       
     List <Reserva> listaReservaHabitacion;
    


    public Habitacion() {
    }

    public Habitacion(int id_habitacion, int piso, int num_habitacion, String tematica, int tipo_habitacion, double precio_noche, List<Reserva> listaReservaHabitacion) {
        this.id_habitacion = id_habitacion;
        this.piso = piso;
        this.num_habitacion = num_habitacion;
        this.tematica = tematica;
        this.tipo_habitacion = tipo_habitacion;
        this.precio_noche = precio_noche;
        this.listaReservaHabitacion = listaReservaHabitacion;
    }

    public List<Reserva> getListaReservaHabitacion() {
        return listaReservaHabitacion;
    }

    public void setListaReservaHabitacion(List<Reserva> listaReservaHabitacion) {
        this.listaReservaHabitacion = listaReservaHabitacion;
    }


    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(int tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }
    
    
    
    
    
    
}
