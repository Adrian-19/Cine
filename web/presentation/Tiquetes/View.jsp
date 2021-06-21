<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="cine.tiquetes.Model"%>
<%@page import="cine.logic.Compra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
    <link rel="stylesheet" href="/Cine/css/style.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css"> 
    <!-- <link rel="stylesheet" href="/Cine/css/bootstrap.min.css" type="text/css"> -->
    <title>Tiquetes</title>
    <script src="/Cine/js/menu.js"></script>
    <script src="/Cine/js/peliculas/peliculas.js"></script>
    <script src="/Cine/js/principal/principal.js"></script>
    <!--        <script src="../js/registro/registro.js"></script>-->
    <script src="/Cine/js/registroSala/registroSala.js"></script>
     <script src="/Cine/js/login.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>


</head>
<body>

    <% Model model = (Model) request.getAttribute("model"); %>
    <%List<Compra> compras = model.getCompras();%>

    <div class="mb-5">
        <form id="formulario-compras-r" method="post" name="Inicio" action="/Cine/presentation/Tiquetes/pdf">
            <div class="modal-body">
                <div class="form-group">
                    <label for="numeroCompra">Numero de Compra</label>
                    <select name="numeroCompra" id="numeroCompra" class="form-control" >
                        <% for (Compra c : compras) {%>
                        <option value=<%=c.getId()%> selected=""><%=c.getId()%></option>      
                        <%}%>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" value="Ver PDF" class="btn btn-secondary">
                </div>
            </div>
        </form>
    </div>

    <!-- Inicio de registro de clientes  -->

    <!-- Option : Registro Cliente -->
    <div class="modal fade" id="add-modal-peliculas" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 400px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle" id="img_logo" src="/Cine/imagenes/addClient.png" style="max-width: 50px; max-height: 50px"> </div>
                    <div > <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true">&times;</span> </button> </div>
                </div>
                <form id="formulario-registro-cliente" >
                    <div class="modal-body">
                        <div id="div-login-msg">
                            <div id="icon-login-msg" ></div>
                            <span id="text-login-msg">Registrarse</span>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="id">ID</label>
                            <input type="number" class="form-control" name="id" id="id" placeholder="ID">
                        </div>                        
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre">
                        </div>  

                    </div>
                </form>

                <div class="modal-footer d-flex justify-content-center">
                    <div>
                        <input type="button" id="registrar-Cliente" class="btn btn-primary btn-lg btn-block" value="Registrar">
                    </div>
                </div>

            </div>         
        </div>
        <div id="ErrorDiv" style="width:30%; margin: auto;"></div>              
    </div>

    <!-- Fin de registro de clientes  -->       


    <!-- Option : Asientos -->
    <div class="modal fade" id="add-modal-asientos" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 400px">
            <div class="modal-content">
                <div class="modal-header" >
                    <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle"  src="/Cine/imagenes/asientos.jpg" style="max-width: 50px; max-height: 50px"> </div>
                    <div > <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true">&times;</span> </button> </div>
                </div>
                <form id="formulario-asientos" >
                    <div class="modal-body">
                        <div id="div-login-msg">
                            <div id="icon-login-msg" ></div>
                            <span id="text-login-msg">Registrar Pelicula</span>
                        </div>
                        <br>
                        <ul class="showcase">
                            <li>
                                <div id="seat" class="seat"></div>
                                <small class="status" style="font-size: 1em;">N/A</small>
                            </li>
                            <li>
                                <div id="seat" class="seat selected"></div>
                                <small class="status" style="font-size: 1em;">Selected</small>
                            </li>
                            <li>
                                <div id="seat" class="seat occupied"></div>
                                <small class="status" style="font-size: 1em;">Occupied</small>
                            </li>
                        </ul>

                        <div class="container-asientos" id="panel-asientos">
                            <div class="screen"></div>

                        </div>

                        <p class="text" style="font-size: 1em;margin:0px 0px 15px 0px">
                            You have selected <span id="count">0</span> seats for a price of $<span
                                id="total"
                                >0</span
                            >
                        </p>
                    </div>
                </form>
                <div class="modal-footer d-flex justify-content-center">
                    <div>
                        <input type="button" id="registrar-asientos" class="btn btn-primary btn-lg btn-block" value="Registrar">
                    </div>
                </div>
            </div>         
        </div>
    </div>

    <!-- Option : Login  -->

    <div class="modal fade" id="add-modal-login" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 400px">
            <div class="modal-content">
                <div class="modal-header" >
                    <div class="d-flex justify-content-center" style="width: 90%">  
                        <img class="img-circle" id="img_logo" src="/Cine/imagenes/userLogin.png" style="max-width: 50px; max-height: 50px">
                    </div>
                    <div > <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true">&times;</span> </button> </div>
                </div> 
                <form id="formularioLogin" >
                    <div class="modal-body">
                        <div id="div-login-msg">
                            <div id="icon-login-msg" ></div>
                            <span id="text-login-msg">Iniciar Sesion</span>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="cedula">Cedula</label>
                            <input type="text" class="form-control" name="cedula" id="cedulaLogin" placeholder="Cedula">
                        </div>
                        <div class="form-group">
                            <label for="contraseña">Contraseña</label>
                            <input type="password" class="form-control" name="contraseña" id="contrasenaLogin" placeholder="Contraseña">
                        </div>                
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <div>
                            <input type="button" id="login" class="btn btn-primary btn-lg btn-block" value="Login">
                        </div>
                    </div>
                </form>                 
            </div>         
        </div>
        <div id="addErrorDivLogin" style="width:30%; margin: auto;"></div>              
    </div>

    <!-- Option : Registrar Salas  -->
    <div class="modal fade" id="add-modal-salas" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 400px">
            <div class="modal-content">
                <div class="modal-header" >
                    <div class="d-flex justify-content-center" style="width: 90%">  
                        <img class="img-circle" id="img_logo" src="/Cine/imagenes/salaIcon.png" style="max-width: 50px; max-height: 50px"> 
                    </div>
                    <div > <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true">&times;</span> </button> </div>
                </div> 
                <form id="formularioSalas" >
                    <div class="modal-body">
                        <div id="div-login-msg">
                            <div id="icon-login-msg" ></div>
                            <span id="text-login-msg">Registrar Salas</span>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="numSala">Numero de Sala</label>
                            <input type="text" class="form-control" name="numSala" id="numSalaReg" placeholder="Numero de Sala">
                        </div>
                        <div class="form-group">
                            <label for="cantFilas">Cantidad de filas de asientos</label>
                            <input type="text" class="form-control" name="cantFilas" id="cantFilasReg" placeholder="Cantidad de filas de asientos">
                        </div>                
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <div>
                            <input type="button" id="registrarSala" class="btn btn-primary btn-lg btn-block" value="Login">
                        </div>
                    </div>
                </form>                 
            </div>         
        </div>
        <div id="addErrorDivRegSala" style="width:30%; margin: auto;"></div>              
    </div>




    <div class="d-block h-25">
        <span class="mh-100"></span>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>


</body>

