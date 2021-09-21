
package Servlet;

import Logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarHuesped", urlPatterns = {"/SvEliminarHuesped"})
public class SvEliminarHuesped extends HttpServlet {

    
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
        control.eliminarHuesped(id);
        
        //Actualizo mi lista 
        request.getSession().setAttribute("listaHuespedes", control.traerListaHuespedes());
        
                    //Parametros de mi alerta de error
            request.setAttribute("stMensaje", "El Huespe se elimino correctamente!");
            request.setAttribute("stTipo", "success");
        
        
       request.getRequestDispatcher("registroHuespedes.jsp").forward(request,response);
        
        
        
        
        //response.sendRedirect("registroHuespedes.jsp");
        
        
        
        

    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
