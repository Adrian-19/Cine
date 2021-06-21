
var compra = {id:0, nombrePeli:"", sala: "", fecha: "", hora: "", cantTiquetes: "" }; 
var compras = new Array(); 
var url="http://localhost:8080/Cine/"; 

function renderVerCompra(){
    //fetch peliculas
    // fetchPeliculas(); 
    // 
    //listar en la tabla de películas 
    // list(); 
    //
    $('#tabla-verCompras').prop("readonly", false); 
    $('#add-modal-verCompras').modal('show'); 
    
}


function resetVerCompra(){
    compra = {id:0, nombrePeli:"", sala: "", fecha: "", hora: "", cantTiquetes: "" };  
}

function makenewverCompra () {
    resetVerCompra(); 
    renderVerCompra(); 
}

function loadedVerCompras() {
    $('#verCompras').click(makenewverCompra); 
    console.log(loadedVerCompras); 
}

$(loadedVerCompras); 
