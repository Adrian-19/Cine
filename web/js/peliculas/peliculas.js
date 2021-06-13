var pelicula = {id: 0, precio: 0, estado: "", nombre: ""};
var peliculas = new Array();
var url="http://localhost:8080/Cine/";
var mode='A';

function render() {

    $("#precio").val(pelicula.precio);
    $("#nombre").val(pelicula.nombre);
        switch(mode){
            case 'A':
                $("#id").val(peliculas.length.toString());
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

function load() {
    pelicula = Object.fromEntries((new FormData($("#formulario-peliculas").get(0))).entries());
}

function reset() {
    pelicula = {id: "", precio: 0, estado: "", nombre: ""};
}

function validar() {
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
    load();
    if(!validar()) return;
    let request = new Request(url+'api/peliculas', {method: 'PUT', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-peliculas #errorDiv"));return;}
        fetchAndList();
        reset();
        $('#add-modal-peliculas').modal('hide');                
    })();     
}

function edit(id){
    let request = new Request(url+'api/peliculas/'+id, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#errorDiv"));return;}
        pelicula = await response.json();
        mode='E';
        render();        
    })();         
}

function registrarPelicula() {
    load();
    if (!validar())
        return;
    let request = new Request(url+'api/peliculas', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-peliculas #errorDiv"));return;}
        addImagen();
        fetchAndList();
        reset();
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




function makenew() {
    reset();
    mode='A'; 
    render();
}


function loaded() {
    fetchAndList();
    $("#crear-Pelicula").click(makenew);
   
}

$(loaded);

function errorMessage(status,place){  
        switch(status){
            case 404: error= "Registro no encontrado"; break;
            case 403: case 405: error="Usuario no autorizado"; break;
            case 406: case 405: error="Pelicula ya existe"; break;
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
}  

function fetchAndList(){
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
                "<td id='edit'><img src='images/edit.png'></td>");
        tr.find("#edit").on("click",()=>{edit(pelicula.id);});
	listado.append(tr);           
}

//----------------------------------------------------------------------------------

//var fila_asientos = 6;
//var colum_asientos = 8;
//var num_asi = 1;
//var proyeccion = {id: "", precio: 1000, estado: "", nombre: "", asientos: ["1", "2"]};
//var selec = new Array();
//var cargado = false;
//
//
//function render_a() {
//    var cont = 1;
//    var panel = $('#panel-asientos');
//    cargarFilas(panel, cont);
//    $('#add-modal-asientos').modal('show');
//    cargado = true;
//
//}
//
//
//function cargarAsientos(fila) {
//
//    for (var j = 0; j < colum_asientos; j++) {
//        var asiento = $("<div />");
//        asiento.addClass("seat");
//        asiento.attr("id", "seat");
//        asiento.attr("data-value", num_asi.toString());
//        if (proyeccion.asientos.includes(num_asi.toString())) {
//            asiento.addClass("occupied");
//        } else {
//            if (selec.includes(num_asi.toString())) {
//                asiento.addClass("selected");
//
//            }
//            asiento.on('click', function () {
//                if ($(this).hasClass("selected") && cargado) {
//                    var i = selec.indexOf($(this).attr("data-value"));
//                    if (i !== -1) {
//                        selec.splice(i, 1);
//                    }
//                    $(this).removeClass("selected");
//
//                } else {
//                    if (!$(this).hasClass("occupied") && cargado) {
//
//                        $(this).addClass("selected");
//                        selec.push($(this).attr("data-value"));
//                    }
//
//                }
//                var total = selec.length * proyeccion.precio;
//                $('#count').text(selec.length);
//                $('#total').text(total);
//                console.log(JSON.stringify(selec));
//            });
//        }
//        fila.append(asiento);
//        num_asi++;
//        console.log("Se añadio un asiento");
//    }
//
//
//}
//
//function cargarFilas(panel) {
//    num_asi = 1;
//    for (var i = 0; i < fila_asientos; i++) {
//        var div = $("<div />");
//        div.addClass("fila");
//        cargarAsientos(div);
//        panel.append(div);
//        console.log("Se añadio una fila");
//
//    }
//
//
//}
//
//
//
//function reset_a() {
//    $(".fila").remove();
//}
//
//
//function makenew_a() {
//    reset_a();
//    render_a();
//}