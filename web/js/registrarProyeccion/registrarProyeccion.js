var url="http://localhost:8080/Cine/";

function renderRegistrarProyeccion(){
    //hacer fetch de las peliculas
    //hacer fetch de las salas
    $('#add-modal-registrar-proy').modal('show'); 
    
}

function resetRegistrarProyeccion(){
    //programar ...
}

function makenewRegistrarProyeccion(){
    resetRegistrarProyeccion(); 
    renderRegistrarProyeccion(); 
}

function  LoadedRegistrarProyeccion(){
    $('#programarProyeccion').click(makenewRegistrarProyeccion); 
    console.log(LoadedRegistrarProyeccion); 
}

$(LoadedRegistrarProyeccion); 