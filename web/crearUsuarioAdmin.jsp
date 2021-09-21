

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
                        <h1 class="auth-title mb-4 text-center fs-1"> Crear usuario Admin</h1>
                        
                        
                        <form class="mb-3 py-4" action="SvCrearUsuario" method="POST">
                            
                            <div class="from-floating mb-3">

                                <div class="form-group  position-relative has-icon-left mb-4">
                                    
                                <input type="text" class="form-control form-control-xl" name="usuarioAdmin" placeholder="Usuario">
                                
                                <div class="form-control-icon">
                                    
                                    <i class="bi bi-person"></i>
                                    
                                </div>
                                
                                </div>
                                
                                <div class="form-group position-relative has-icon-left mb-4">
                                    
                                    <input type="password" class="form-control form-control-xl" name="passAdmin" placeholder="Contraseña">
                                    
                                    <div class="form-control-icon">
                                        
                                        <i class="bi bi-shield-lock"></i>
                                        
                                    </div>
                                    
                                </div>

                                <button type="submit" class="btn btn-primary btn-block btn-lg shadow-lg mt-3">Aceptar</button>
                                
                            </div>
                            
                        </form>

                    </div>
                    
                </div>
                
            </div>
       
            
        </seccion>

    </div>
    
    
</body>

</html>