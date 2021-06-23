

var rowMisCompras = { idCompra: 0, noButaca:0, fecha: "", hora: "", noSala:"", nomPeli:"", Precio: 0}; 
var compras = new Array();  //Lista de las compras para mostrar en el modal
var url="http://localhost:8080/Cine/"; 


function renderVerCompra(){
    console.log("Render"); 
    $('#tabla-verCompras').prop("readonly", false); 
    
    $('#add-modal-verCompras').modal('show'); 

}

function resetFilaVerCompras(){
    $(".verFilaCompra").remove();  
}

function makenewverCompra () {
    resetFilaVerCompras(); 
    fetchAndListCompras(); 
    renderVerCompra(); 
}

// listar en la tabla de compras: 
function listVerCompras(){
     $("tabla-verCompras").html("");
    compras.forEach( (p)=>{rowVerCompras($("#tabla-verCompras"),p);});	  
}

function rowVerCompras(listadoVerCompra, compra){
    var trCompra =$("<tr class = 'verFilaCompra'  />");
    trCompra.html ("<td>"+ compra.id +"</td>"+
             "<td>"+ compra.proyeccion.fecha +"</td>" +
             "<td>"+ compra.proyeccion.hora+"</td>" +
             "<td>"+ compra.proyeccion.sala.numeroSala +"</td>" +
             "<td>"+ compra.proyeccion.pelicula.nombre +"</td>" +
             "<td>"+ (compra.listaTiq.length * compra.proyeccion.pelicula.precio )+"</td>" 
            ); 
    listadoVerCompra.append(trCompra); 
}

// Consultas fetch: 

function fetchAndListCompras(){
    console.log("fetch..."); 
    let usuarioInJson = sessionStorage.getItem('user');
    if(usuarioInJson!=null){
        let usuarioToApi = JSON.parse(usuarioInJson);
        console.log("Usario to api ");
        console.log(usuarioToApi);
        
        let requestMisCompras = new Request(url+'api/verCompras/' + usuarioToApi.cedula  , {method: 'GET', headers: {}}); 
        (async ()=>{
        const responseMisCompras = await fetch(requestMisCompras);
        if (!responseMisCompras.ok) {errorMessageVerCompras(responseMisCompras.status,$("#addErrorDivRVerCompras"));return;}
        compras = await responseMisCompras.json(); 
        console.log("ESTAS SON LAS COMPRAS: "); 
        console.log(compras); 
        listVerCompras();
    })();
    }
     
}

function errorMessageVerCompras(status,place){  
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

// Load  

function loadedVerCompras() {
    $('#verCompras').click(makenewverCompra); 

}

$(loadedVerCompras); 
