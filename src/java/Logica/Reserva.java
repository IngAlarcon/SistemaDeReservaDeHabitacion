
package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_reserva;
    @Basic   
    @Temporal(TemporalType.DATE)        
    Date check_in;
    @Temporal(TemporalType.DATE)
    Date check_out;
    @Temporal(TemporalType.DATE)
    Date fecha_reserva;
    int cantidad_personas;
    double precio_total;  
    @OneToOne(cascade=CascadeType.PERSIST)
    Huesped reservaHuesped;
    @OneToOne(cascade=CascadeType.PERSIST)
    Usuario reservaUsuario;    
    @OneToOne(cascade=CascadeType.PERSIST)
    Habitacion tipoHabitacion;

    public Reserva() {
        
    }

    public Reserva(int id_reserva, Date check_in, Date check_out, Date fecha_reserva, int cantidad_personas, double precio_total, Huesped reservaHuesped, Usuario reservaUsuario, Habitacion tipoHabitacion) {
        this.id_reserva = id_reserva;
        this.check_in = check_in;
        this.check_out = check_out;
        this.fecha_reserva = fecha_reserva;
        this.cantidad_personas = cantidad_personas;
        this.precio_total = precio_total;
        this.reservaHuesped = reservaHuesped;
        this.reservaUsuario = reservaUsuario;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Huesped getReservaHuesped() {
        return reservaHuesped;
    }

    public void setReservaHuesped(Huesped reservaHuesped) {
        this.reservaHuesped = reservaHuesped;
    }

    public Usuario getReservaUsuario() {
        return reservaUsuario;
    }

    public void setReservaUsuario(Usuario reservaUsuario) {
        this.reservaUsuario = reservaUsuario;
    }



    public Habitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(Habitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }



    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public int getCantidad_personas() {
        return cantidad_personas;
    }

    public void setCantidad_personas(int cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }
    

    
}
