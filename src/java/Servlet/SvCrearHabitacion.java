
package Servlet;

import Logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvCrearHabitacion", urlPatterns = {"/SvCrearHabitacion"})
public class SvCrearHabitacion extends HttpServlet {

  
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
        
        //Traigo los datos de la interfas grafica del jsp,
        //Crovariables para cada dato que quiero guardar
   try{     
        int numeroPiso = Integer.parseInt(request.getParameter("numeroPiso"));
        int numeroHabitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));
        String tematicaHabitacion = request.getParameter("tematicaHabitacion");
        int tipoHabitacion = Integer.parseInt(request.getParameter("tipoHabitacion")); 
        
        
        
        double precioHabitacion = Double.parseDouble(request.getParameter("precioHabitacion"));
        

        //Sesion cada ves que entramos aun sitio web como un cliente creamos una sesion 
        //Mientra el usiario este logueado o en sesion los datos se púedan usar en cualquier jsp
        //Traemnos la seccion y le asignamos los atributos
        
        request.getSession().setAttribute("numeroPiso", numeroPiso);
        request.getSession().setAttribute("numeroHabitacion", numeroHabitacion);
        request.getSession().setAttribute("tematicaHabitacion", tematicaHabitacion);        
        request.getSession().setAttribute("tipoHabitacion", tipoHabitacion);
        request.getSession().setAttribute("precioHabitacion", precioHabitacion);
        
   
        
        //creo una instancia hacia la controladora es decir estoy haciendo coneccion con la logica
        Controladora control = new Controladora();
        control.crearHabitacion(numeroPiso, numeroHabitacion, tematicaHabitacion, tipoHabitacion, precioHabitacion);
       
        //Actualizo la vista de mi tabla 
        request.getSession().setAttribute("listaHabitaciones", control.traerListaHabitaciones());

        // redireccion a otra pagina jsp que muestre los datos que se cargo en la otra pagina jsp         
         response.sendRedirect("registroHabitaciones.jsp");
         
         }catch(NumberFormatException e){
         
             System.err.println(e + " is not a number.");
         
         }
              
        
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
