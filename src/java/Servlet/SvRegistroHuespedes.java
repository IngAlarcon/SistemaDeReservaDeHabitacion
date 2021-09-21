
package Servlet;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvRegistroHuespedes", urlPatterns = {"/SvRegistroHuespedes"})
public class SvRegistroHuespedes extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
            Controladora control = new Controladora();

            List <Huesped> listaHuespedes = control.traerListaHuespedes();

            HttpSession misession = request.getSession();

            misession.setAttribute("listaHuespedes", listaHuespedes);
            
            response.sendRedirect("registroHuespedes.jsp");        
        
        

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
