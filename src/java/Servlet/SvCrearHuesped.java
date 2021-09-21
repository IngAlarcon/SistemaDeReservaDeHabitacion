
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


@WebServlet(name = "SvCrearHuesped", urlPatterns = {"/SvCrearHuesped"})
public class SvCrearHuesped extends HttpServlet {

    
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
        
   
            
        
        try {
            
            String nombreHuesped = request.getParameter("nombreHuesped");
            String apellidoHuesped = request.getParameter("apellidoHuesped");
            String dniHuesped = request.getParameter("dniHuesped");


            //Tranformando fecha que viene del jsp como estrin convotiendolo en date para mandar a la controladora 

            Date fechaNHuesped = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd"); 


            try { 

                fechaNHuesped = formato.parse(request.getParameter("fechaNacHuesped"));

            } catch (ParseException ex) {
                Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }

            String direccionHuesped = request.getParameter("direccionHuesped");
            String profesionHuesped = request.getParameter("profesionHuesped");   


            request.getSession().setAttribute("nombreHuesped", nombreHuesped);
            request.getSession().setAttribute("apellidoHuesped", apellidoHuesped);
            request.getSession().setAttribute("dniHuesped", dniHuesped);        
            request.getSession().setAttribute("fechaNHuesped", fechaNHuesped);
            request.getSession().setAttribute("direccionHuesped", direccionHuesped);
            request.getSession().setAttribute("profesionEmpleado", profesionHuesped);       

            //creo una instancia hacia la controladora es decir estoy haciendo coneccion con la logica
            Controladora control = new Controladora();
            control.crearHuesped(nombreHuesped, apellidoHuesped, dniHuesped, fechaNHuesped, direccionHuesped, profesionHuesped);
            //Actualizo la vista de mi tabla 
           
            request.getSession().setAttribute("listaHuespedes", control.traerListaHuespedes());
            
            //Parametros de mi alerta de error
            request.setAttribute("stMensaje", "El Huesped se registro con exito!");
            request.setAttribute("stTipo", "success");
        
        
             request.getRequestDispatcher("crearHuesped.jsp").forward(request,response);           
            
            
            
            // redireccion a otra pagina jsp que muestre los datos que se cargo en la otra pagina jsp         
            //response.sendRedirect("registroHuespedes.jsp");  
            
            
        } catch (Exception e) {
        
            request.setAttribute("stMensaje", "Ooop hubo un error, revise los datos que ingreso!");
            request.setAttribute("stTipo", "error");
        
        
            request.getRequestDispatcher("crearHuesped.jsp").forward(request,response);  
        
        
        }
        
          
    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
