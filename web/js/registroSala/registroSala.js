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
        if (!response.ok) {errorMessage(response.status,$("#add-modal-salas #addErrorDivRegSala"));return;}
        $('#add-modal-salas').modal('hide');
        document.location = url + "presentation/principal.html";  
    })(); 
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