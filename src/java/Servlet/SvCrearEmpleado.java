
package Servlet;

import Logica.Controladora;
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


@WebServlet(name = "SvCrearEmpleado", urlPatterns = {"/SvCrearEmpleado"})
public class SvCrearEmpleado extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String nombreEmpleadoAdmin = request.getParameter("nombreAdmin");
        String apellidoEmpleadoAdmin = request.getParameter("apellidoAdmin");
        String dniEmpleadoAdmin = request.getParameter("dniAdmin");
               
        
        //Tranformando fecha que viene del jsp como estrin convotiendolo en date para mandar a la controladora 
        
        Date fechaNAdmin = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd"); 
        
   
        try { 
            
            fechaNAdmin = formato.parse(request.getParameter("fechaNacAdmin"));
      
        } catch (ParseException ex) {
            Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String direccionEmpleadoAdmin = request.getParameter("direccionAdmin");
        String cargoEmpleadoAdmin = request.getParameter("cargoAdmin");   
        
        
        request.getSession().setAttribute("nombreEmpleado", nombreEmpleadoAdmin);
        request.getSession().setAttribute("apellidoEmpleado", apellidoEmpleadoAdmin);
        request.getSession().setAttribute("dniEmpleado", dniEmpleadoAdmin);        
        request.getSession().setAttribute("fechaNAdmin", fechaNAdmin);
        request.getSession().setAttribute("direccionEmpleado", direccionEmpleadoAdmin);
        request.getSession().setAttribute("cargoEmpleado", cargoEmpleadoAdmin);       

 //creo una instancia hacia la controladora es decir estoy haciendo coneccion con la logica
        Controladora control = new Controladora();
        control.crearEmpleado(nombreEmpleadoAdmin, apellidoEmpleadoAdmin, dniEmpleadoAdmin, fechaNAdmin, direccionEmpleadoAdmin, cargoEmpleadoAdmin);            
            
            
         
        // redireccion a otra pagina jsp que muestre los datos que se cargo en la otra pagina jsp         
         response.sendRedirect("crearUsuarioAdmin.jsp");         
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        

            
            
            String nombreEmpleado = request.getParameter("nombre");
            String apellidoEmpleado = request.getParameter("apellido");
            String dniEmpleado = request.getParameter("dni");


            //Tranformando fecha que viene del jsp como estrin convotiendolo en date para mandar a la controladora 

            Date fechaN = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd"); 


            try { 

                fechaN = formato.parse(request.getParameter("fechaNac"));

            } catch (ParseException ex) {
                Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }

            String direccionEmpleado = request.getParameter("direccion");
            String cargoEmpleado = request.getParameter("cargo");   


            request.getSession().setAttribute("nombreEmpleado", nombreEmpleado);
            request.getSession().setAttribute("apellidoEmpleado", apellidoEmpleado);
            request.getSession().setAttribute("dniEmpleado", dniEmpleado);        
            request.getSession().setAttribute("fechaN", fechaN);
            request.getSession().setAttribute("direccionEmpleado", direccionEmpleado);
            request.getSession().setAttribute("cargoEmpleado", cargoEmpleado);       

            //creo una instancia hacia la controladora es decir estoy haciendo coneccion con la logica
            Controladora control = new Controladora();
            control.crearEmpleado(nombreEmpleado, apellidoEmpleado, dniEmpleado, fechaN, direccionEmpleado, cargoEmpleado);
            //Actualizo la vista de mi tabla 
            request.getSession().setAttribute("listaEmpleados", control.traerListaEmpleados());
            
            
            
            // redireccion a otra pagina jsp que muestre los datos que se cargo en la otra pagina jsp         
            response.sendRedirect("registroEmpleados.jsp");  

        
      

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
