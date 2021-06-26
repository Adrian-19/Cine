var url="http://localhost:8080/Cine/";
var compra;
var clienteLogged = {cliente: "", cedula: ""};
var asientosLogged = new Array(); // creo que es un array...preguntar
var proyeccionLogged = null;
function renderCompra(asientos, proyeccion){
    $('#add-modal-asientos').modal('hide');
    proyeccionLogged = proyeccion;
    asientosLogged = asientos;
    
    let clienteSession = sessionStorage.getItem('persona');
    
    if (clienteSession!=null){ 
        let clienteReal = JSON.parse(clienteSession);
        clienteLogged = clienteReal;
        $("#cedulaCompra").val(clienteReal.cedula);
        $("#cedulaCompra").prop("readonly", true);
        
        $("#nombreCompra").val(clienteReal.nombre);
        $("#nombreCompra").prop("readonly", true);
        console.log(clienteLogged);
    }
    
    $('#add-modal-compra').modal('show');
    
    $('#ejecutarCompra').off('click').on('click', compraAction);
}

function compraAction (){
    // validacion 
    compra = {
        cedula: $("#cedulaCompra").val(),
        nombre: $("#nombreCompra").val(),
        cliente: clienteLogged,
        proyeccion: proyeccionLogged
    };
    console.log(clienteLogged);
    let request = new Request(url+'api/compras', {method: 'POST', headers:{'Content-Type' : 'application/json'}, body: JSON.stringify(compra)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessageCompras(response.status,$("#add-modal-compra #addErrorDivCompra"));return;}
        var idCompra = await response.json();
        console.log("ID: " + idCompra);
        asientosLogged.unshift(idCompra);
        let request2 = new Request(url+'api/compras', {method: 'PUT', headers:{'Content-Type' : 'application/json'}, body: JSON.stringify(asientosLogged)});
        (async ()=>{
            const response2 = await fetch(request2);
            $("#add-modal-compra #addErrorDivCompra").html('<div class="alert alert-success d-flex align-items-center" role="alert">'+
                    '<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>'+
                    '<div>Compra realizada exitosamente!  .</div>'+
                    '<div><button type="button" id="continuarCompraButton" class="btn btn-success">Continuar</button></div></div>');
            $("#continuarCompraButton").click(finalizarCompra);
        })();
    })(); 
}

function finalizarCompra(){
        $('#add-modal-compra').modal('hide');
        document.location = url + "presentation/principal.html";
}

function errorMessageCompras(status,place){  
    switch(status){
        case 404: error= "Registro no encontrado"; break;
        case 403: case 405: error="Usuario no autorizado"; break;
        case 406: case 405: error="Usuario ya existe"; break;
    };            
    place.html('<div class="alert alert-danger fade show">' +
    '<button type="button" class="close" data-dismiss="alert">' +
    '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
    return;        
}

function loadCompra(){
    $("#compraClick").click(renderCompra);
}

$(loadCompra)