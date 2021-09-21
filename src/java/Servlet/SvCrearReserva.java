
package Servlet;

import Logica.Controladora;
import Logica.Habitacion;
import Logica.Huesped;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvCrearReserva", urlPatterns = {"/SvCrearReserva"})
public class SvCrearReserva extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
 
            Controladora control = new Controladora();

            List <Habitacion> listaHabitaciones = control.traerListaHabitaciones();
            List<Huesped> listaHuespedes = control.traerListaHuespedes();

            HttpSession misession = request.getSession();

            misession.setAttribute("listaHabitaciones", listaHabitaciones);
            misession.setAttribute("listaHuespedes", listaHuespedes);
            
            
            response.sendRedirect("crearReserva.jsp");              

        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

            Controladora control = new Controladora();
            
            
            
            //con el nombre del usurio busco su id
            String usuReserva = request.getParameter("usuReserva");
            
            //Guardo el id del huesped que quiere hacer la reserva
            int huespedReserva = Integer.parseInt(request.getParameter("huespedReserva"));

            String fechaCheck_inReserva = request.getParameter("fechaCheck_in");
            String fechaCheck_outReserva = request.getParameter("fechaCheck_out");
            String fechaReserva = request.getParameter("fechaReserva");

            int cantidadReserva = Integer.parseInt(request.getParameter("cantidadReserva"));
         
            int habitacionReserva = Integer.parseInt(request.getParameter("habitacionReserva"));
            String infoHuesped = control.traerInfoHuesped(huespedReserva);
            String infoHabitacion = control.traerInfoHabitacion(habitacionReserva);
            
             //Tranformando fecha que viene del jsp par calcular los dias
             Date fechaCheck_in = control.convertirFechaADate(fechaCheck_inReserva);
             Date fechaCheck_out = control.convertirFechaADate(fechaCheck_outReserva);             
             int milisecondsByDay = 86400000;
             int diasReserva = (int) ((fechaCheck_out.getTime()-fechaCheck_in.getTime()) / milisecondsByDay);             
             
             
             
             //Buscar precio de la habitacion
             double precioNoche = control.buscarPrecioHAbitacion(habitacionReserva);           
             double precioTotalReserva = control.calculoPrecioTotalReserva(fechaCheck_inReserva, fechaCheck_outReserva, precioNoche);
             //Buscar id de mi usurio           
             int idUsurioReserva = control.buscarIdUsuario(usuReserva);
            

            request.getSession().setAttribute("huespedReserva", huespedReserva);
            request.getSession().setAttribute("infoHuesped", infoHuesped);
            request.getSession().setAttribute("infoHabitacion", infoHabitacion);
            request.getSession().setAttribute("fechaCheck_inReserva", fechaCheck_inReserva);
            request.getSession().setAttribute("fechaCheck_outReserva", fechaCheck_outReserva);        
            request.getSession().setAttribute("fechaReserva", fechaReserva);
            request.getSession().setAttribute("cantidadReserva", cantidadReserva);
            request.getSession().setAttribute("diasReserva", diasReserva);
            request.getSession().setAttribute("precioNoche", precioNoche);
            request.getSession().setAttribute("habitacionReserva", habitacionReserva);  
            request.getSession().setAttribute("precioTotalReserva", precioTotalReserva);             
            request.getSession().setAttribute("idUsurioReserva", idUsurioReserva);
            
            response.sendRedirect("confirmacionReserva.jsp");  

        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
