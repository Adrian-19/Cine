var url="http://localhost:8080/Cine/";

var proyecciones = new Array();

// ---------------------------------------------------------------------------------------------------------

// estamos recibiendo las proyecciones... cada proyeccion esta asociada a una pelicula.
// debemos de quedarnos sobre la pelicula de la proyeccion y buscar las proyecciones
// que tienen esa misma pelicula en la que estamos.
function list(){
    $("#listado").html("");
    proyecciones.forEach( (p)=>{row($("#listado"),p);});	
}
function row(listado, proyeccion){
    if(!$('#pel' + proyeccion.pelicula.id).length){
        var card = $("<div class = 'card'> </div>");
        var html = 
        "<div class = 'imgBx'>" + 
            "<img src = '" + "/Cine/imagenes/3.quiet_place.jpg" + "'>" + // Debemos de adjuntar la img de la peli
        "</div>" +
        "<div class = 'content'>" +
          "<h2> " + proyeccion.pelicula.nombre + "</h2>" +
        "<ul class = 'list-unstyled'>";
        for(i = 0; i<proyecciones.length; i++){
            if(proyecciones[i].pelicula.id == proyeccion.pelicula.id && proyecciones[i].id != proyeccion.id){
                html+= "<li><a href = '#'> " + proyeccion.fecha +  " " + proyeccion.hora + " / " + proyeccion.sala.numeroSala +
                "</a></li>";
            }
        }
        html += "</ul> </div> </div>";
        card.html(html);
        listado.append(card);
    }
}

function fetchAndList(){
    let request = new Request(url+'api/principal', {method: 'GET', headers:{}});
    (async ()=>{
        const response = await fetch(request);
        //if (!response.ok) {errorMessage(response.status,$("#buscarDiv #errorDiv"));return;}
        proyecciones = await response.json();
        console.log(proyecciones);
        list();              
    })(); 
}

function loaded(){
    fetchAndList();
    // $("#buscar").on("click", search);
}

$(loaded);