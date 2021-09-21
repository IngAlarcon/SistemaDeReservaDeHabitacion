
package Servlet;

import Logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
            //Cerrar Sesion 
             request.getSession().invalidate();
             response.sendRedirect("login.jsp");
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Iniciando sesion        
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("pass");
        
        //llamndo a la controladora para conectar con la logica
        Controladora control = new Controladora ();
        //verificacion de autorizacion
        boolean autorizado = control.buscarVerificarUsuario(usuario,contrasenia);
        
        if (autorizado == true){
            //Obtengo la secion y le asigno el usuario y contraseña para validar
            HttpSession misession = request.getSession(true);
            misession.setAttribute("usuario", usuario);
            misession.setAttribute("contrasenia", contrasenia);
        
            //Redireccionado mi pagina al inicio
            response.sendRedirect("inicio.jsp");
          
        
        }else{
            
            //Parametros de mi alerta de error
            request.setAttribute("stMensaje", "Usuario o contraseña incorrecta, intente nuevamente!");
            request.setAttribute("stTipo", "error");
        
             //response.sendRedirect("login.jsp");
             request.getRequestDispatcher("login.jsp").forward(request,response);
        
        }

        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
