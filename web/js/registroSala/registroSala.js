var url="http://localhost:8080/Cine/";
var sala = {id: 0, numeroSala: "", cantidadFilas: 0};

function renderRegSala(){
    $('#registrarSala').off('click').on('click', regSalaAction);
    $('#add-modal-salas').modal('show');
}

function regSalaAction(){
    sala = {
        id: 0, 
        numeroSala: $("#numSalaReg").val(), 
        cantidadFilas: $("#cantFilasReg").val()
    };
    let request = new Request(url+'api/salas', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(sala)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessageSalas(response.status,$("#add-modal-salas #addErrorDivRegSala"));return;}
        $('#add-modal-salas').modal('hide');
        document.location = url + "presentation/principal.html";  
    })(); 
}

function errorMessageSalas(status,place){  
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

function resetSala(){
    sala = {id: 0, numeroSala: "", cantidadFilas: 0};
}

function makenew_RegSala(){
    resetSala();
    renderRegSala();
}

function loadRegSala(){
    $("#regSala").click(makenew_RegSala); 
}

$(loadRegSala);