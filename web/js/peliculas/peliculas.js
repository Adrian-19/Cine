var pelicula = {id: 0, precio: 0, nombre: "" ,estado: ""};
var peliculas = new Array();
var url="http://localhost:8080/Cine/";
var mode='A';

function renderPelicula() {

    $("#precio").val(pelicula.precio);
    $("#nombre").val(pelicula.nombre);
        switch(mode){
            case 'A':
                $("#id").val(pelicula.id);
                $("#id").prop("readonly", true);
                $('#registrar-Pelicula').off('click').on('click', registrarPelicula);
                break;
            case 'E':
                $("#id").val(pelicula.id);
                $("#id").prop("readonly", true);
                $('#registrar-Pelicula').off('click').on('click', modificarPelicula);
                var op =$("<option value='Retirada'>Retirada</option>");
                $("#estado").append(op);
                break;             
        }
    $("#add-modal #imagen").val(""); 
    $('#add-modal-peliculas').modal('show');
}

function loadPelicula() {
    pelicula = Object.fromEntries((new FormData($("#formulario-peliculas").get(0))).entries());
    console.log("Esta es la pelicula cargada : "+pelicula.id+" "+pelicula.precio+" "+pelicula.nombre+" "+pelicula.estado); //solo para probar
    
}

function resetPelicula() {
    pelicula = {id:peliculas.length+1, precio: 0, estado: "", nombre: ""};
}

function validarPelicula() {
    var error = false;
    $("#formulario-peliculas input").removeClass("invalid");
    error |= $("#formulario-peliculas input[type='text']").filter((i, e) => {
        return e.value == '';
    }).length > 0;
    $("#formulario-peliculas input[type='text']").filter((i, e) => {
        return e.value == '';
    }).addClass("invalid");
    error |= $("#formulario-peliculas input[type='number']").filter((i, e) => {
        return e.value == 0;
    }).length > 0;
    $("#formulario-peliculas input[type='number']").filter((i, e) => {
        return e.value == 0;
    }).addClass("invalid");
    error |= $("#formulario-peliculas select").filter((i, e) => {
        return e.value == '';
    }).length > 0;
    $("#formulario-peliculas select").filter((i, e) => {
        return e.value == '';
    }).addClass("invalid");

    return !error;
}

function modificarPelicula(){
    loadPelicula();
    if(!validarPelicula()) return;
    let request = new Request(url+'api/peliculas', {method: 'PUT', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-peliculas #errorDiv"));return;}
        fetchAndListP();
        resetPelicula();
        $('#add-modal-peliculas').modal('hide');                
    })();     
}

function editPelicula(id){
    let request = new Request(url+'api/peliculas/'+id, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#errorDiv"));return;}
        pelicula = await response.json();
        mode='E';
        renderPelicula();        
    })();         
}

function registrarPelicula() {
    loadPelicula();
    if (!validarPelicula())
        return;
    let request = new Request(url+'api/peliculas', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-peliculas #errorDiv"));return;}
        addImagen();
        fetchAndListP();
        resetPelicula();
        $('#add-modal-peliculas').modal('hide');                
    })();     
    console.log(listar_usuarios());//solo para probar

}

function addImagen(){
    var imagenData = new FormData();
    imagenData.append("id", pelicula.id);
    imagenData.append("imagen", $("#imagen").get(0).files[0]); 
    let request = new Request(url+'api/peliculas/'+pelicula.id+"/imagen", {method: 'POST',body: imagenData});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-peliculas #errorDiv"));return;}              
    })();    
}




function makenew_P() {
    resetPelicula();
    mode='A'; 
    renderPelicula();
}


function loaded_peliculas() {
    fetchAndListP();
    $("#crearPelicula").click(makenew_P);
    $("#buscar").click(buscarPelicula);
   
}

$(loaded_peliculas);

function errorMessage(status,place){  
        switch(status){
            case 404: error= "Registro no encontrado"; break;
            case 406: case 405: error="Pelicula ya existe"; break;
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
}  

function fetchAndListP(){
    let request = new Request(url+'api/peliculas', {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#errorDiv"));return;}
        peliculas = await response.json(); 
        //listAllP();
        list();
        console.log(listar_usuarios());//solo para probar
    })();    
} 

function buscarPelicula(){
    var busqueda = $("#busqueda").val();
    console.log("Se realizo la siguiente busqueda: "+busqueda);
    let request = new Request(url+'api/peliculas?nombre='+busqueda, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#errorDiv"));return;}
        peliculas = await response.json(); 
        listAllP();
        list();
        console.log(listar_usuarios());//solo para probar
    })();    
}


function listar_usuarios() {
    txt = '';
    peliculas.forEach(function (e) {
        txt += " ID : " + e.id + "  Nombre : " + e.nombre + "  Precio : " + e.precio + "  Estado : " + e.estado + " \n";
    });
    return txt;
}

//function listP(listado,pelicula){
//	var div =$("<div />");
//	div.html("<div class='card'><div class ='imgBx'><img src = '"+url+"api/peliculas/"+pelicula.id+"/imagen'></div><div class = 'content'><h2>"+pelicula.nombre+"</h2><div class = 'list-group'><a href='#' class = 'list-group-item' id='ver-asientos'>Item One</a><a href='#' class = 'list-group-item' id='ver-asientos'>Item Two</a><a href='#' class = 'list-group-item' id='ver-asientos'>Item Three</a></div></div></div>");
//	//div.find("#ver-asientos").on("click",makenew_a);
//        listado.append(div);           
//}
//
//function listAllP(){
//    $("#panel-pelis").html("");
//    peliculas.forEach( (p)=>{listP($("#panel-pelis"),p);});	
//}  

function list(){
    $("#tabla-pelis").html("");
    peliculas.forEach( (p)=>{row($("#tabla-pelis"),p);});	
}  
  
function row(listado,pelicula){
	var tr =$("<tr />");
	tr.html("<td>"+pelicula.id+"</td>"+
                "<td>"+pelicula.nombre+"</td>"+
                "<td>"+pelicula.precio+"</td>"+
                "<td>"+pelicula.estado+"</td>"+
                "<td><img src='"+url+"api/peliculas/"+pelicula.id+"/imagen' class='icon_large' ></td>"+                
                "<td id='edit'><img src='/Cine/imagenes/edit.png'></td>");
        tr.find("#edit").on("click",()=>{editPelicula(pelicula.id);});
	listado.append(tr);           
}

