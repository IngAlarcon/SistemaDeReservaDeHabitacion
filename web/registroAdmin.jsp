<%-- 
    Document   : registroAdmin
    Created on : 04/08/2021, 12:31:46
    Author     : Chango
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Mazer Admin Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="stylesheet" href="assets/css/pages/auth.css">
</head>

<body>
    <div id="auth">
        
      <seccion class="container">
            <div class="row content d-flex justify-content-center">
                <div class="col-md-5">
                    <div class="box shadow bg-white p-4">
                        <h1 class="auth-title mb-4 text-center fs-1">Registro Admin</h1>
 
                                              <form class="mb-3 py-4" action="SvCrearEmpleado" method="GET">  
                                                      
                                                    <div class="col-12">
                                                        <div class="form-group has-icon-left">
                                                            <label for="first-name-icon">Nombre</label>
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" name="nombreAdmin" placeholder="Nombre..." id="first-name-icon">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-person"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-12">
                                                        <div class="form-group has-icon-left">
                                                            <label for="first-name-icon">Apellido</label>
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" name="apellidoAdmin" placeholder="Apellido...." id="first-name-icon">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-pen"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                                                   

                                                    
                                                    <div class="col-12">
                                                        <div class="form-group has-icon-left">
                                                            <label for="first-name-icon">Dni</label>
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" name="dniAdmin" placeholder="Ingrese su Dni..." id="first-name-icon">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-pen"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                  
                                                    <div class="col-12">
                                                        <div class="form-group has-icon-left">
                                                            <label for="first-name-icon">Fecha de Nacimiento</label>
                                                            <div class="position-relative">
                                                                <input type="date" class="form-control" name="fechaNacAdmin" placeholder="" id="first-name-icon">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-pen"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>    
                                                  
                                                    <div class="col-12">
                                                        <div class="form-group has-icon-left">
                                                            <label for="first-name-icon">Direccion</label>
                                                            <div class="position-relative">
                                                                <input type="text" class="form-control" name="direccionAdmin" placeholder="Direccion..." id="first-name-icon">
                                                                <div class="form-control-icon">
                                                                    <i class="bi bi-pen"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>   
                                               
                                                     <div class="col-12">

                                                        <div class="form-group has-icon-left">
                                                            <label for="email-id-icon">Cargo</label>
                                                            <div class="input-group mb-3">
                                                                <label class="input-group-text"  for="inputGroupSelect01">Opciones</label>
                                                                <select class="form-select" name="cargoAdmin" id="inputGroupSelect01">
                                                                
                                                                    <option value="Administrador">Administrador</option>
                                                                  
                                                                </select>
                                                            </div>
                                                        </div>

                                                    </div>                                                 
                                                  
                                                  
                                                    <div class="col-12 d-flex justify-content-end">
                                                        
                                                        <button type="submit" class="btn btn-primary me-1 mb-1">Guardar</button>
                                                   
                                                    </div>
                                                 
                                              </form>  
                               

                    </div>
                    
                </div>
                
            </div>
            
            
            
        </seccion>       
        
        

       

    </div>
</body>

</html>