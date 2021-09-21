
package Servlet;

import Logica.Controladora;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvReservaRegistrada", urlPatterns = {"/SvReservaRegistrada"})
public class SvReservaRegistrada extends HttpServlet {

   
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
        processRequest(request, response);

             int idUsuario = Integer.parseInt(request.getParameter("idUsuarioConfirmado"));
             int idHuespede = Integer.parseInt(request.getParameter("idHuespedConfirmado"));
          
             //Tranformando fecha que viene del jsp par calcular los dias
            Date fechaCheck_in = new Date();
            Date fechaCheck_out = new Date();
            Date fechaReserva = new Date();

            SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd"); 

            try { 
                fechaCheck_in = formato.parse(request.getParameter("fechaCheck_inConfirmado"));
                fechaCheck_out = formato.parse(request.getParameter("fechaCheck_outConfirmado"));
                fechaReserva = formato.parse(request.getParameter("fechaReservaConfirmado"));

            } catch (ParseException ex) {
                Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }      
            
            int cantidadPersonas = Integer.parseInt(request.getParameter("cantidadConfirmado"));
            int idHabitacion = Integer.parseInt(request.getParameter("idHabitacionConfirmado"));
            double precioTotal = Double.parseDouble(request.getParameter("precioTotalConfirmado"));

             Controladora control = new Controladora();
            
             //Bosco mis objetos usuario huesped y habitacion 
             Usuario usuarioReserva = control.buscarUsuario(idUsuario);
             Huesped huespedReserva = control.buscarHuesped(idHuespede);
             Habitacion habitacionReserva = control.buscarHabitacionParaReserva(idHabitacion);
             
            request.getSession().setAttribute("idUsuario", idUsuario);
            request.getSession().setAttribute("idHuespede", idHuespede);
            request.getSession().setAttribute("fechaCheck_in", fechaCheck_in);        
            request.getSession().setAttribute("fechaCheck_out", fechaCheck_out);
            request.getSession().setAttribute("fechaReserva", fechaReserva);
            request.getSession().setAttribute("cantidadPersonas", cantidadPersonas);       
            request.getSession().setAttribute("idHabitacion", idHabitacion);
            request.getSession().setAttribute("precioTotal", precioTotal);
 
            request.getSession().setAttribute("usuarioReserva", usuarioReserva);       
            request.getSession().setAttribute("huespedReserva", huespedReserva);
            request.getSession().setAttribute("habitacionReserva", habitacionReserva);
            

            control.crearReserva(usuarioReserva, huespedReserva,fechaCheck_in,fechaCheck_out,fechaReserva, cantidadPersonas,habitacionReserva, precioTotal);            
      
            request.getSession().setAttribute("listaReservas", control.traerListaReservas());
            
            response.sendRedirect("registroReservas.jsp");              

        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
