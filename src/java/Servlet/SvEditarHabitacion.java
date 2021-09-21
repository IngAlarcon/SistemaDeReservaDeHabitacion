
package Servlet;

import Logica.Controladora;
import Logica.Habitacion;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvEditarHabitacion", urlPatterns = {"/SvEditarHabitacion"})
public class SvEditarHabitacion extends HttpServlet {

    
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

            //Implemtamos un metodo 
            Habitacion habitacionEncontrada = control.buscarHabitacion(id);

            HttpSession misession = request.getSession();
            misession.setAttribute("habitacionEncontrada", habitacionEncontrada);

            //Llivo los datos a un jsp para visualizarlo y editarlo
            response.sendRedirect("editarHabitacion.jsp");

        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
            try{
                //Traer datos de la interfas grafica del jsp
                //Crear variables para cada cosa que quiero que me traiga
                int idEdit = Integer.parseInt(request.getParameter("idEdit"));
                int numeroPisoEdit = Integer.parseInt(request.getParameter("numeroPisoEdit"));
                int numeroHabitacionEdit = Integer.parseInt(request.getParameter("numeroHabitacionEdit"));
                String tematicaHabitacionEdit = request.getParameter("tematicaHabitacionEdit");
                int tipoHabitacionEdit = Integer.parseInt(request.getParameter("tipoHabitacionEdit"));        
                double precioHabitacionEdit = Double.parseDouble(request.getParameter("precioHabitacionEdit"));
                
                

                //Aqui llamamos a la controladora es decir conectando con la logica

                Controladora control = new Controladora();

                Habitacion editHabitacion = control.buscarHabitacion(idEdit);

                editHabitacion.setPiso(numeroPisoEdit);
                editHabitacion.setNum_habitacion(numeroHabitacionEdit);
                editHabitacion.setTematica(tematicaHabitacionEdit);
                editHabitacion.setTipo_habitacion(tipoHabitacionEdit);
                editHabitacion.setPrecio_noche(precioHabitacionEdit);

                control.actualizarHabitacion(editHabitacion);

                 //Actualizo mi lista d epersonas

                request.getSession().setAttribute("listaHabitaciones", control.traerListaHabitaciones());
                response.sendRedirect("registroHabitaciones.jsp");
                
           }catch(NumberFormatException e){
         
             System.err.println(e + " Error fatal.");
         
         }


    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
