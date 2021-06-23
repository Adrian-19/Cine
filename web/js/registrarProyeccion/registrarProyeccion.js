var url="http://localhost:8080/Cine/";
var peliculas = new Array(); 
var salas = new Array(); 
var proyeccion = { asientos:[], fecha:"", hora:"", id:0, pelicula:{estado:"",id:0,nombre:"",precio:0},sala:{cantidadFilas:0,id:0,numeroSala:"0"}};

var proyecciones = new Array(); 

//render: 
function renderRegistrarProyeccion(){
    fetchAndListPeliculas(); 
    fetchAndListSalas(); 
    $("#idProyeccion").val(proyeccion.id);
    $("#idProyeccion").prop("readonly", true);
    $("#fechaProyeccion").val(proyeccion.fecha);
    $("#horaProyeccion").val(proyeccion.hora);
    $('#registrarProyeccion').off('click').on('click', registrarProyeccionAction);
    $('#add-modal-registrar-proy').modal('show'); 
}

//fetch and list películas: Se hace la petición con fetch para mostrar en la proyección
function fetchAndListPeliculas(){
    let request = new Request(url+'api/peliculas', {method: 'GET', headers: { }}); 
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#addErrorDivRegistrarProyeccion"));return;}
        peliculas = await response.json(); 
        console.log("PELICULAS"); 
        console.log(peliculas); 
        listarPeliculas();
    })();
}

//Fetch and list salas: Se hace la petición con fetch
function fetchAndListSalas(){
    let requestSalaP = new Request(url+'api/salas', {method: 'GET', headers: { }}); 
    (async ()=>{
        const responseSalaP = await fetch(requestSalaP);
        if (!responseSalaP.ok) {errorMessage(responseSalaP.status,$("#addErrorDivRegistrarProyeccion"));return;}
        salas = await responseSalaP.json(); 
        console.log("SALAS"); 
        console.log(salas); 
        listarSalas();
    })();
}

// Listar películas: Se crea html desde JS
function listarPeliculas(){
    $("#peliProyeccion").html(""); 
    peliculas.forEach((p)=>{optionsPeli($("#peliProyeccion"),p);}); 
}

function optionsPeli(listado, pelicula){
    var selectPelic = $("<option />"); 
    selectPelic.html("<option value=" + pelicula.id  +  " >" + pelicula.id +  "</option>"); /////
    listado.append(selectPelic); 
}

//Listar salas: Se crea html desde JS
function listarSalas(){
    $("#salaProyeccion").html(""); 
    salas.forEach((s)=>{optionsSala($("#salaProyeccion"),s);}); 
}

function optionsSala(listado, sala){
    var selectSala = $("<option />"); 
    selectSala.html("<option value=" + sala.id + " >" + sala.id + "</option>"); ///////
    listado.append(selectSala); 
}
//Validar Registro:
function varlidarRegProyecciones(){
    var error=false;
    $("#formulario-registrar-proy input").removeClass("invalid");
    error |= $("#formulario-registrar-proy input[type='date']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid"); 
    error |= $("#formulario-registrar-proy input[type='time']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
    error |= $("#formulario-registrar-proy select").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");       
    error |=  $("#formulario-registrar-proy select").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
    
    return !error;    
}
// Registrar proyecciones: Acción del botón registrar
function loadProyeccion(){

    proyeccion = {
        id: $("#idProyeccion").val(),
        fecha: $("#fechaProyeccion").val().toString(),
        hora: $("#horaProyeccion").val().toString(),
        pelicula: {id: $("#peliProyeccion option:selected").val()},
        sala: {id: $("#salaProyeccion option:selected").val() }
        
    }
        
    console.log("PROYECCION DEL FORMULARIO: "); //borrar **
    console.log(proyeccion);//borrar **
}
function registrarProyeccionAction(){
    if(!varlidarRegProyecciones()) 
        return;
    loadProyeccion(); 
     let requestRegProyeccion = new Request(url+'api/registrarProyeccion', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(proyeccion)});
    (async ()=>{
        const responseRegProyeccion = await fetch(requestRegProyeccion);
        if (!responseRegProyeccion.ok) {errorMessage(responseRegProyeccion.status,$("#add-modal-registrar-proy #addErrorDivRegistrarProyeccion"));return;}
        console.log(proyeccion);//borrar **
         fetchAllProy(); 
        $('#add-modal-salas').modal('hide');
        document.location = url + "presentation/principal.html";  
    })(); 
    
}

// Fetch de todas las proyecciones. 
function fetchAllProy(){
    let requestAllP = new Request(url+'api/registrarProyeccion', {method: 'GET', headers: { }});
    (async ()=>{
        const responseAllP = await fetch(requestAllP);
        if (!responseAllP.ok) {errorMessage(responseAllP.status,$("#addErrorDivRegistrarProyeccion"));return;}
        proyecciones = await responseAllP.json(); 

    })();  
}

// Reset & Load: Base

function resetRegistrarProyeccion(){
    proyeccion = {id:proyecciones.length+1, fecha: "", hora: "",  idPelicula:0, idSala:0 }; 
}

function makenewRegistrarProyeccion(){
    fetchAllProy(); 
    resetRegistrarProyeccion(); 
    renderRegistrarProyeccion(); 
}

function  LoadedRegistrarProyeccion(){
    $('#programarProyeccion').click(makenewRegistrarProyeccion); 
    console.log(LoadedRegistrarProyeccion); 
}

$(LoadedRegistrarProyeccion); 