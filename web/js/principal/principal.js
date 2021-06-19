var url="http://localhost:8080/Cine/";

var pelicula = {id: 0, precio: 0, nombre: "" ,estado: ""};
var proyecciones = new Array();

// ---------------------------------------------------------------------------------------------------------

function listProyecciones(){
    
    $("#listado").html("");
    proyecciones.forEach( (p)=>{rowProyeccion($("#listado"),p);});	
}
function rowProyeccion(listado, proyeccion){
    if(!$('#pel' + proyeccion.pelicula.id).length){
        if(proyeccion.pelicula.estado == "En Cartelera"){
            var card = $("<div class = 'card'> </div>");
            var html = 
            "<div class = 'imgBx'>" + 
                "<img src = '" + "/Cine/imagenes/3.quiet_place.jpg" + "'>" + // Debemos de adjuntar la img de la peli
            "</div>" +
            "<div class = 'content'>" +
              "<h2> " + proyeccion.pelicula.nombre + "</h2>" +
            "<ul class = 'list-unstyled' id = 'pel"+proyeccion.pelicula.id+"'>";
//    agregar id 
            html+= "<li ><a id = 'pro"+proyeccion.id +"' href = '#'> " + proyeccion.fecha +  " " + proyeccion.hora + " / " + proyeccion.sala.numeroSala +
                    "</a></li>" + 
                    "</ul> </div> </div>";
            
            // función registrar compra ** 
            
            
            card.html(html);
            listado.append(card);
            $("#pro"+proyeccion.id).on("click",()=>{makenew_asientos(proyeccion);});
            
        }
    }
    else{
        $('#pel' + proyeccion.pelicula.id).append("<li><a href = '#'> " + proyeccion.fecha +  " " + proyeccion.hora + " / " + proyeccion.sala.numeroSala +
                "</a></li>");
        
    }
}

function fetchAndListPrincipal(){
    let request = new Request(url+'api/principal', {method: 'GET', headers:{}});
    (async ()=>{
        const response = await fetch(request);
        //if (!response.ok) {errorMessage(response.status,$("#buscarDiv #errorDiv"));return;}
        proyecciones = await response.json(); 
        listProyecciones();              
    })(); 
}

function searchPelicula(){
    var busqueda = $("#busqueda").val();
    let request = new Request(url+'api/principal?nombre='+busqueda, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#errorDiv"));return;}
        proyecciones = await response.json(); 
        listProyecciones();
    })();
}

function loadedPrincipal(){
    fetchAndListPrincipal();
    $("#buscarPrincipal").click(searchPelicula);
}

$(loadedPrincipal);