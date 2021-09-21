package Logica;

import Logica.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-21T01:07:37")
@StaticMetamodel(Habitacion.class)
public class Habitacion_ { 

    public static volatile SingularAttribute<Habitacion, Integer> piso;
    public static volatile SingularAttribute<Habitacion, String> tematica;
    public static volatile SingularAttribute<Habitacion, Integer> tipo_habitacion;
    public static volatile SingularAttribute<Habitacion, Integer> num_habitacion;
    public static volatile SingularAttribute<Habitacion, Double> precio_noche;
    public static volatile ListAttribute<Habitacion, Reserva> listaReservaHabitacion;
    public static volatile SingularAttribute<Habitacion, Integer> id_habitacion;

}