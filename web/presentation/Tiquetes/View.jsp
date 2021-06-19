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
    <title>Registro Peliculas</title>
    <script src="/Cine/js/menu.js"></script>
    <script src="/Cine/js/peliculas/peliculas.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

    <title>Tiquetes</title>
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

    <div class="d-block h-25">
        <span class="mh-100"></span>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>


</body>

