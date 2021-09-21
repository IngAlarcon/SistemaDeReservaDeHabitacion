
package Servlet;

import Logica.Controladora;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvEditarReserva", urlPatterns = {"/SvEditarReserva"})
public class SvEditarReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
             //Traemos el id de jsp lo guardamos en una variable hacer el parseo
            int id = Integer.parseInt(request.getParameter("id"));

            Controladora control = new Controladora ();
           
            List <Habitacion> listaHabitaciones = control.traerListaHabitaciones();
            List<Huesped> listaHuespedes = control.traerListaHuespedes();

            //Implemtamos un metodo 
            Reserva reservaEncontrada = control.buscarReserva(id);
            
            HttpSession misession = request.getSession();

            misession.setAttribute("listaHabitaciones", listaHabitaciones);
            misession.setAttribute("listaHuespedes", listaHuespedes);

            misession.setAttribute("reservaEncontrada", reservaEncontrada);
            
            //Llivo los datos a un jsp para visualizarlo y editarlo
            response.sendRedirect("editarReserva.jsp");       
        
 
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
