
package Servlet;

import Logica.Controladora;
import Logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvDarAltaSistema", urlPatterns = {"/SvDarAltaSistema"})
public class SvDarAltaSistema extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Controladora control = new Controladora();
        
        List<Usuario> listaUsuarios = control.traerListaUsuarios();

        if (listaUsuarios.size() != 0 ){

            //response.sendRedirect("sistemaHabilitado.jsp");
            
            //Parametros de mi alerta de error
            request.setAttribute("stMensaje", "El sistema ya se dio de alta, hay un administrador registrado.");
            request.setAttribute("stTipo", "info");
        
             //response.sendRedirect("login.jsp");
             request.getRequestDispatcher("login.jsp").forward(request,response);

        
         }else{

                response.sendRedirect("registroAdmin.jsp");
        
        }
        
        
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
