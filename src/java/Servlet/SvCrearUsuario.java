
package Servlet;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvCrearUsuario", urlPatterns = {"/SvCrearUsuario"})
public class SvCrearUsuario extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
            Controladora control = new Controladora();

            List <Empleado> listaEmpleados = control.traerListaEmpleados();

            HttpSession misession = request.getSession();

            misession.setAttribute("listaEmpleados", listaEmpleados);
            
            response.sendRedirect("crearUsuario.jsp");        
              
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
            Controladora control = new Controladora();
        
        if(request.getParameter("usuarioAdmin") != null && request.getParameter("passAdmin") != null ){

            String usuarioAdmin = request.getParameter("usuarioAdmin");
            String passAdmin = request.getParameter("passAdmin");

            request.getSession().setAttribute("usuarioAdmin", usuarioAdmin);
            request.getSession().setAttribute("passAdmin", passAdmin);


            //creo una instancia hacia la controladora es decir estoy haciendo coneccion con la logica

            control.crearUsuario(usuarioAdmin, passAdmin);            

            // redireccion a otra pagina jsp que muestre los datos que se cargo en la otra pagina jsp         
             response.sendRedirect("login.jsp");   
                       
            
        
        
        }else{
            

            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));


           //verificacion de autorizacion
           boolean autorizado = control.empleadoConUSuario(idEmpleado);

           if (autorizado == true){

               String usuarioEmpleado = request.getParameter("usuarioEmpleado");
               String passEmpleado = request.getParameter("passEmpleado");
               //int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));

               request.getSession().setAttribute("usuarioEmpleado", usuarioEmpleado);
               request.getSession().setAttribute("passEmpleado", passEmpleado);
               request.getSession().setAttribute("idEmpleado", idEmpleado);

               control.crearUsuarioEmplaedo(usuarioEmpleado, passEmpleado,idEmpleado);            

               request.getSession().setAttribute("listaUsuarios", control.traerListaUsuarios());
               // redireccion a otra pagina jsp que muestre los datos que se cargo en la otra pagina jsp         
                response.sendRedirect("registroUsuarios.jsp");              


            }else{

                //Parametros de mi alerta de error

                request.setAttribute("stMensaje", "Este empleado ya tiene una cuenta creada en el sistema!");
                request.setAttribute("stTipo", "error");


                 request.getRequestDispatcher("crearUsuario.jsp").forward(request,response);


                   // response.sendRedirect("crearUsuario.jsp");

            }           


        }

        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
