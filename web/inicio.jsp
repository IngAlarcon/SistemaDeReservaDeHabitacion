

<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vertical Navbar - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/iconly/bold.css">
    <link rel="stylesheet" href="assets/vendors/iconly/broken.css">
    <link rel="stylesheet" href="assets/vendors/iconly/bulk.css">
    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    
</head>

<body>

<!--Verifico si exite una session-->
<%
  //Aqui compuebo que exista un usuario si no me redirije a un jsp acceso sin usuario
        HttpSession misession = request.getSession();
             
        String usu = (String) misession.getAttribute("usuario");
              //si el usuario exite puede entrar sino no

              if (usu == null){

                  response.sendRedirect("accesoNoPermitido.jsp");

              }else{
    //Si el acceso es permitido seteo mi controladora para acceder a los metodos
            Controladora control = new Controladora();
            misession.setAttribute("control",control);

%>

    <div id="app">

<!--Barra de menu vertical-->
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            
                            <a href="inicio.jsp"><span><h3>Hotel</h3></span></a>
                        
                        </div>
                     
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                        
                    </div>
                </div>
                <div class="sidebar-menu">
                    <ul class="menu">
                        <li class="sidebar-title">Menu</li>
                        
                        <li class="sidebar-item active ">
                            <a href="inicio.jsp" class="sidebar-link">
                                <i class="bi bi-grid-fill"></i>
                                <span>Inicio</span>
                            </a>
                        </li>


                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-person-plus-fill"></i>
                                <span>Administradores</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="crearEmpleado.jsp">Registrar Empleado</a>
                                </li>
                               <form action="SvCrearUsuario" method="GET"> 
                                   
                                    <li class="submenu-item ">
                                        <a href="SvCrearUsuario" class="submit" >Crear Usuario</a>
                                    </li>
                               
                                 </form>
                                <form action="SvRegistroEmpleados" method="GET">    
                                   <li class="submenu-item ">
                                       <a  href="SvRegistroEmpleados" class="submit">Registro Empleados</a>
                                   </li>
                                </form>
                                
                                 <form action="SvRegistroUsuarios" method="GET">    
                                   <li class="submenu-item ">
                                       <a  href="SvRegistroUsuarios" class="submit">Registro Usuarios</a>
                                   </li>
                                </form>                               
                                
                                
                                
                               </ul>

                        </li>
                        
                         <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                                <i class="bi bi-door-open-fill"></i>
                                <span>Habitaciones</span>
                            </a>
                            <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href="crearHabitacion.jsp">Crear habitacion</a>
                                </li>

                                <form action="SvRegistroHabitaciones" method="GET"> 

                                    <li class="submenu-item">

                                        <a href="SvRegistroHabitaciones" class="submit">Registro de habitaciones</a>

                                    </li>

                                 </form>   
                                
                               </ul>

                        </li>                       



                        <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                               <i class="bi bi-calendar-week-fill"></i>
                                <span>Reservas</span>
                            </a>
                            <ul class="submenu ">
                                
                               <form action="SvCrearReserva" method="GET">  
                                
                                   <li class="submenu-item ">
                                    <a href="SvCrearReserva" class="submit">Crear Reserva</a>
                                    </li>
                                    
                                </form>
                                
                               <form action="SvRegistroReservas" method="GET">   
                               
                                   <li class="submenu-item ">
                                 
                                    <a href="SvRegistroReservas" class="submit">Registro total</a>
                               
                                    </li>
                              
                               </form>

                            </ul>
                        </li>


                        
                         <li class="sidebar-item  has-sub">
                            <a href="#" class='sidebar-link'>
                               <i class="bi bi-person-check-fill"></i>
                                <span>Huespedes</span>
                            </a>
                            <ul class="submenu ">
                                
                                <li class="submenu-item ">
                                    <a href="crearHuesped.jsp">Registar Huesped</a>
                                </li>

                                <form action="SvRegistroHuespedes" method="GET">    
                                   <li class="submenu-item ">
                                       <a  href="SvRegistroHuespedes" class="submit">Registro Huespedes</a>
                                   </li>
                                </form>                               
                                
                                
                            </ul>
                        </li>                       


                    </ul>
                </div>
                
                <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
            
            </div>
            
        </div>
<!--Fin Barra de menu vertical-->
        
        
      
        
        <div id="main" class='layout-navbar'>
            
       <!--Barra de menu Horizontal-->       
            <header class='mb-3'>
                <nav class="navbar navbar-expand navbar-light ">
                    <div class="container-fluid">
                        <a href="#" class="burger-btn d-block">
                            <i class="bi bi-justify fs-3"></i>
                        </a>

                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

                            </ul>
                            <div class="dropdown">
                                <a href="#" data-bs-toggle="dropdown" aria-expanded="false">
                                    <div class="user-menu d-flex">
                                        <div class="user-name text-end me-3">
                                            <h6 class="mb-0 text-gray-600"><%=request.getSession().getAttribute("usuario")%></h6>
                                            <p class="mb-0 text-sm text-gray-600">Bienvenido</p>
                                        </div>
                                        <div class="user-img d-flex align-items-center">
                                            <div class="avatar avatar-md">
                                                <img src="assets/images/faces/1.jpg">
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" style="right: -95px;" aria-labelledby="dropdownMenuButton">
                                  
                                    <!--Boton salir-->
                                    <form action="SvLogin" method="get">
                                    <li>
                                        <a class="dropdown-item" href="SvLogin" class="submit">
                                                <i class="icon-mid bi bi-box-arrow-left me-2"></i> 
                                                Cerrar sesion
                                        </a>
                                    </li>
                                    </form>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
            </header>
        <!--Fin Barra de menu horizontal--> 
         
            
         <!-- CONTENIDO + FOOTER-->
            <div id="main-content" >
               
                <!-- CONTENIDO PRINCIPAL -->
                <div class="page-heading" style="min-height: 600px;">
                    <div class="page-title">
                        <div class="row">
                            <div class="col-12 col-md-6 order-md-1 order-last">
                                <h3>Inicio</h3>
                                
                            </div>
                            <div class="col-12 col-md-6 order-md-2 order-first">
                                <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="index.html">Inicio</a>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>

                    
                    <section class="section">
                       <div class="row ">
                            <div class="col-6 col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon purple">
                                                    <i class="iconly-boldUser"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Huespedes registrados</h6>
                                                <h6 class="font-extrabold mb-0">10</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon blue">
                                                    <i class="iconly-boldCalendar"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Reservas</h6>
                                                <h6 class="font-extrabold mb-0">183.000</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon green">
                                                    <i class="iconly-boldNotification"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Habitaciones</h6>
                                                <h6 class="font-extrabold mb-0">80.000</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </section>
                </div>
               <!-- FIN CONTENIDO PRINCIPAL -->
                
                
           <!-- FOOTER -->
                <footer>
                    <div class="footer clearfix mb-0 text-muted">
                        <div class="float-start">
                            <p> Copyright &copy; Sistema Hotelero 2021 </p>
                        </div>
                        <div class="float-end">
                            <p>Creado por Alarcon Walter</p>
                        </div>
                    </div>
                </footer>
          <!--FIn FOOTER -->
            </div>

        </div>
    </div>


    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/main.js"></script>
    
    
<% } %>
    
</body>

</html>