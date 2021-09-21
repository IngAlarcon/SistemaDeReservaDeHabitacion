
package Servlet;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvEditarHuesped", urlPatterns = {"/SvEditarHuesped"})
public class SvEditarHuesped extends HttpServlet {

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
            Huesped huespedEncontrado = control.buscarHuesped(id);

            HttpSession misession = request.getSession();
            misession.setAttribute("huespedEncontrado", huespedEncontrado);

            //Llivo los datos a un jsp para visualizarlo y editarlo
            response.sendRedirect("editarHuesped.jsp");

        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

            try{
                //Aqui llamamos a la controladora es decir conectando con la logica

                Controladora control = new Controladora();   
                //Traer datos de la interfas grafica del jsp
                //Crear variables para cada cosa que quiero que me traiga
                int idEdit = Integer.parseInt(request.getParameter("idEdit"));
                
                String nombreHuespedEditar = request.getParameter("nombreHuespedEditar");
                String apellidoHuespedEditar = request.getParameter("apellidoHuespedEditar");
                String dniHuespedEditar = request.getParameter("dniHuespedEditar");
                
                Date fechaDateHuespedEditar = control.convertirFechaADate(request.getParameter("fechaNacHuespedEditar"));

                String direccionHuespedEditar = request.getParameter("direccionHuespedEditar");
                String profesionHuespedEditar = request.getParameter("profesionHuespedEditar");

                Huesped editHuesped = control.buscarHuesped(idEdit);
                
                editHuesped.setNombre(nombreHuespedEditar);
                editHuesped.setApellido(apellidoHuespedEditar);
                editHuesped.setDni(dniHuespedEditar);
                editHuesped.setFecha_nac(fechaDateHuespedEditar);
                editHuesped.setDireccion(direccionHuespedEditar);
                editHuesped.setProfesion(profesionHuespedEditar);

                control.actualizarHuesped(editHuesped);

                //Actualizo mi lista d epersonas

                request.getSession().setAttribute("listaHuespedes", control.traerListaHuespedes());
                

            //Parametros de mi alerta de error
            request.setAttribute("stMensaje", "Se actualizaron los datos del Huesped con exito!");
            request.setAttribute("stTipo", "success");
        
             //response.sendRedirect("login.jsp");
             request.getRequestDispatcher("registroHuespedes.jsp").forward(request,response);                
                
                

               // response.sendRedirect("registroHuespedes.jsp");
                
           }catch(NumberFormatException e){
         
             System.err.println(e + " Error fatal.");
         
         }        
        

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
