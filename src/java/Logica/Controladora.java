
package Logica;

import Persistencia.ControladoraPersistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controladora {
    
    //Inicializo mi controladora persistencia
    ControladoraPersistencia controlPersist = new ControladoraPersistencia();

    public void crearHabitacion(int numeroPiso, int numeroHabitacion, String tematicaHabitacion, int tipoHabitacion, double precioHabitacion) {
        
        //Creo mi objeto habitacion
        Habitacion nuevaHabitacion = new Habitacion();
        //Seteo mi objeto habitacion
        nuevaHabitacion.setPiso(numeroPiso);
        nuevaHabitacion.setNum_habitacion(numeroHabitacion);
        nuevaHabitacion.setTematica(tematicaHabitacion);
        nuevaHabitacion.setTipo_habitacion(tipoHabitacion);
        nuevaHabitacion.setPrecio_noche(precioHabitacion);
        
        //llamo mi controladoraPersitencia y llamo un metodo
        
        controlPersist.crearHabitacion(nuevaHabitacion);
        

    }

    public List<Habitacion> traerListaHabitaciones() {
        
        return controlPersist.traerListaHabitaciones();

    }

    public void eliminarHabitacion(int id) {
        
        controlPersist.eliminarHabitacion(id);
    }

    public Habitacion buscarHabitacion(int id) {
        
        return controlPersist.buscarHabitacion(id);
  
    }

    public void actualizarHabitacion(Habitacion editHabitacion) {
        
        controlPersist.actualizarHabitacion(editHabitacion);
    
    }

    public boolean buscarVerificarUsuario(String usuario, String contrasenia) {
        
        //Traigo mi lista de usuario 
        List<Usuario> listaUsuarios = controlPersist.traerListaUsuarios();
        
        //Reviso si mi lista viene vacia o no
        if(listaUsuarios != null ){
            //Verifico se usuario y contraseña coinciden
            for(Usuario usu : listaUsuarios ){
                
                if( usu.getNombre_usuario().equals(usuario) && usu.getContrasenia().equals(contrasenia)){
                    
                    return true;

                }

            }       
        
         }
        
        return false;       
    
    }

    public void crearEmpleado(String nombreEmpleado, String apellidoEmpleado, String dniEmpleado, Date fechaN, String direccionEmpleado, String cargoEmpleado) {
  
            
        //Creo mi objeto Empleado
        Empleado nuevoEmpleado = new Empleado();
        //Seteo mi objeto Empleado
        nuevoEmpleado.setNombre(nombreEmpleado);
        nuevoEmpleado.setApellido(apellidoEmpleado);
        nuevoEmpleado.setDni(dniEmpleado);
        nuevoEmpleado.setFecha_nac(fechaN);
        nuevoEmpleado.setDireccion(direccionEmpleado);
        nuevoEmpleado.setCargo(cargoEmpleado);
        
        //llamo mi controladoraPersitencia y llamo un metodo
        
        controlPersist.crearEmpleado(nuevoEmpleado);
        

    }

    public void crearUsuario(String usuarioAdmin, String passAdmin) {
        //Creo mi objeto 
        int idAdmin = 1;
        Empleado empleadoAdmin = controlPersist.buscarEmpleadoAdmin(idAdmin);
        Usuario nuevoUsuario = new Usuario();
        //Seteo mi objeto Empleado
        nuevoUsuario.setNombre_usuario(usuarioAdmin);
        nuevoUsuario.setContrasenia(passAdmin);
        nuevoUsuario.setEmpleado(empleadoAdmin);

        
        //llamo mi controladoraPersitencia y llamo un metodo
        
        controlPersist.crearUsuario(nuevoUsuario); 
   
    }



    public List<Usuario> traerListaUsuarios() {
        
       return controlPersist.traerListaUsuarios();
    
    }

    public List<Empleado> traerListaEmpleados() {
        
        
        return controlPersist.traerListaEmpleados();
        
        
    }

    public void crearUsuarioEmplaedo(String usuarioEmpleado, String passEmpleado, int idEmpleado) {
       
        Empleado empleadoAdmin = controlPersist.buscarEmpleadoAdmin(idEmpleado);
        Usuario nuevoUsuario = new Usuario();
        //Seteo mi objeto Empleado
        nuevoUsuario.setNombre_usuario(usuarioEmpleado);
        nuevoUsuario.setContrasenia(passEmpleado);
        nuevoUsuario.setEmpleado(empleadoAdmin);

        
        //llamo mi controladoraPersitencia y llamo un metodo
        
        controlPersist.crearUsuario(nuevoUsuario); 



    }

    public boolean empleadoConUSuario(int idEmpleado) {
        
                //Traigo mi lista de usuario 
       List<Usuario> listaUsuarios = controlPersist.traerListaUsuarios();
        
        //Reviso si mi lista viene vacia o no
        if(listaUsuarios != null ){
            //Verifico se usuario y contraseña coinciden
            for(Usuario usu : listaUsuarios ){
                
                if( usu.getEmpleado().getId_persona() == idEmpleado ){
                    
                    return false;

                }

            }       
        
         }
        
        return true;   
        
        
    }

    public void crearHuesped(String nombreHuesped, String apellidoHuesped, String dniHuesped, Date fechaNHuesped, String direccionHuesped, String profesionHuesped) {

        
               
        //Creo mi objeto 
        Huesped nuevoHuesped = new Huesped();
        //Seteo mi objeto Empleado
        nuevoHuesped.setNombre(nombreHuesped);
        nuevoHuesped.setApellido(apellidoHuesped);
        nuevoHuesped.setDni(dniHuesped);
        nuevoHuesped.setFecha_nac(fechaNHuesped);
        nuevoHuesped.setDireccion(direccionHuesped);
        nuevoHuesped.setProfesion(profesionHuesped);
        
        //llamo mi controladoraPersitencia y llamo un metodo
        
        controlPersist.crearHuesped(nuevoHuesped);

    }

    public List<Huesped> traerListaHuespedes() {
 
            return controlPersist.traerListaHuespedes();
    
    }

    public double buscarPrecioHAbitacion(int habitacionReserva) {
        
       Habitacion habitacionParaRegistro = controlPersist.buscarHabitacion(habitacionReserva);
        
      return habitacionParaRegistro.precio_noche;
    
    }

    public int buscarIdUsuario(String usuReserva) {
        
       //Traigo mi lista de usuario 
       List<Usuario> listaUsuarios = controlPersist.traerListaUsuarios();   
        int idEncontrado=0;
         //Verifico se usuario y contraseña coinciden
            for(Usuario usu : listaUsuarios ){
                
                if( usu.getNombre_usuario().equals(usuReserva) ){
                    
                    idEncontrado = usu.getId_usuario();

                }

            }  
            
            return idEncontrado;
    
    
    }

    public String traerInfoHuesped(int huespedReserva) {
        
        String info="";
        
        Huesped huespedRegistrado = controlPersist.buscarHuesped(huespedReserva);
        
        info = huespedRegistrado.getApellido()+" "+huespedRegistrado.getNombre()+ " - Dni: " +huespedRegistrado.getDni();
        
        return info;
    
    }

    public String traerInfoHabitacion(int habitacionReserva) {
        
           String info="";
        
        Habitacion huespedRegistrado = controlPersist.buscarHabitacion(habitacionReserva);
        
        info = huespedRegistrado.getTematica()+" - Para "+huespedRegistrado.getTipo_habitacion()+" personas - Piso: "+ huespedRegistrado.getPiso()+" - Habitacion N°:" +huespedRegistrado.getNum_habitacion();
        
        return info;
    
    }


    public Usuario buscarUsuario(int idUsuario) {
    
        return controlPersist.buscarUsuario(idUsuario);
  
    }

    public Huesped buscarHuesped(int idHuespede) {
        
        return controlPersist.buscarHuesped(idHuespede);

    }

    public Habitacion buscarHabitacionParaReserva(int idHabitacion) {

        return controlPersist.buscarHabitacionParaReserva(idHabitacion);

    }

    public void crearReserva(Usuario usuarioReserva, Huesped huespedReserva, Date fechaCheck_in, Date fechaCheck_out, Date fechaReserva, int cantidadPersonas, Habitacion habitacionReserva, double precioTotal) {

        Reserva nuevaReserva = new Reserva();
        //Seteo mi objeto 
        
        nuevaReserva.setCheck_in(fechaCheck_in);
        nuevaReserva.setCheck_out(fechaCheck_out);
        nuevaReserva.setFecha_reserva(fechaReserva);
        nuevaReserva.setCantidad_personas(cantidadPersonas);
        nuevaReserva.setPrecio_total(precioTotal);
        nuevaReserva.setReservaHuesped(huespedReserva);
        nuevaReserva.setReservaUsuario(usuarioReserva);
        nuevaReserva.setTipoHabitacion(habitacionReserva);

        //llamo mi controladoraPersitencia y llamo un metodo
        
         controlPersist.crearReserva(nuevaReserva); 
        
    
    }

    public List<Reserva> traerListaReservas() {
        
        return controlPersist.traerListaReservas();
        
    
    }

    public void eliminarHuesped(int id) {
        
        controlPersist.eliminarHuesped(id);

    }


/// Metos de Conversion
    
     public String habitacionIntAString(int tipoHabitacion) {
        
        String verTipoHabitacion= "";  
                                            
                                            
        if(tipoHabitacion == 1){
            verTipoHabitacion="Simple";

        }else{

            if(tipoHabitacion == 2){
                verTipoHabitacion="Doble";                                           

            }else{

                if(tipoHabitacion == 3){
                    verTipoHabitacion="Triple";                                                 

                }else{

                    if(tipoHabitacion == 4 || tipoHabitacion < 7){
                        verTipoHabitacion="Multiple";                                                         

                    }
                }

            }
        }

        return verTipoHabitacion;
    
    }
    

    public String convertirFechaStringInput(Date fecha) {
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        String fechaComoCadena = formato.format(fecha);

        return fechaComoCadena;
    
    }

    public Date convertirFechaADate(String fecha) {
        
        Date fechaStringADate = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd"); 

        try {
            fechaStringADate = formato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return fechaStringADate;
        
        
    }

    public void actualizarHuesped(Huesped editHuesped) {

        controlPersist.actualizarHuesped(editHuesped);
    }

    public double calculoPrecioTotalReserva(String fechaCheck_inReserva, String fechaCheck_outReserva,double precioNoche) {
        
            double precioTotalReserva;
        
            Date fechaCheck_in = convertirFechaADate(fechaCheck_inReserva);
            Date fechaCheck_out = convertirFechaADate(fechaCheck_outReserva);

            //Dias de reserva para calcular monto total
            //Diferencia entre dos fechas
            int milisecondsByDay = 86400000;
            int diasReserva = (int) ((fechaCheck_out.getTime()-fechaCheck_in.getTime()) / milisecondsByDay);
        
        
            if( diasReserva == 0 ){
                
                precioTotalReserva = precioNoche;
                
            }else{
                //con presio y los dias calculo monto total
                 precioTotalReserva = diasReserva*precioNoche;              
            
            }
        return precioTotalReserva;
        
    }

    
    
    public void eliminarReserva(int id) {


        controlPersist.eliminarReserva(id);



    }

    public Reserva buscarReserva(int id) {
        
        return controlPersist.buscarReserva(id);
        
    }

    public void actualizaridReserva(Reserva editReserva) {
        
         controlPersist.actualizarReserva(editReserva);
        
        
    }
    
    
    
    
}


