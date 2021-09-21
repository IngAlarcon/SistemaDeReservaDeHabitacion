package Logica;

import Logica.Habitacion;
import Logica.Huesped;
import Logica.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-21T01:07:37")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Date> check_out;
    public static volatile SingularAttribute<Reserva, Integer> cantidad_personas;
    public static volatile SingularAttribute<Reserva, Date> fecha_reserva;
    public static volatile SingularAttribute<Reserva, Date> check_in;
    public static volatile SingularAttribute<Reserva, Huesped> reservaHuesped;
    public static volatile SingularAttribute<Reserva, Double> precio_total;
    public static volatile SingularAttribute<Reserva, Integer> id_reserva;
    public static volatile SingularAttribute<Reserva, Usuario> reservaUsuario;
    public static volatile SingularAttribute<Reserva, Habitacion> tipoHabitacion;

}