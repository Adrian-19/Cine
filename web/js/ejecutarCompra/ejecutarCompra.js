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
        //if (!response.ok) {errorMessage(response.status,$("#add-modal-salas #addErrorDivRegSala"));return;}
        var idCompra = await response.json();
        console.log("ID: " + idCompra);
        asientosLogged.unshift(idCompra);
        let request2 = new Request(url+'api/compras', {method: 'PUT', headers:{'Content-Type' : 'application/json'}, body: JSON.stringify(asientosLogged)});
        (async ()=>{
            const response2 = await fetch(request2);
            $('#add-modal-compra').modal('hide');
            document.location = url + "presentation/principal.html";
        })();
    })(); 
}

function loadCompra(){
    $("#compraClick").click(renderCompra);
}

$(loadCompra)