
package Servlet;

import Logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvEliminarHabitacion", urlPatterns = {"/SvEliminarHabitacion"})
public class SvEliminarHabitacion extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
         
        //Traemos el id de jsp lo guardamos en una variable hacer el parseo
        int id = Integer.parseInt(request.getParameter("idEliminar"));
        
        
        Controladora control = new Controladora ();
        
        //Implemtamos un metodo borrar persona
        control.eliminarHabitacion(id);
        
        //Actualizo mi lista 
        request.getSession().setAttribute("listaHabitaciones", control.traerListaHabitaciones());
        
        response.sendRedirect("registroHabitaciones.jsp");

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
