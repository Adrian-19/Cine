var url="http://localhost:8080/Cine/";
var mode= 'A';

function renderLogin(){
    $('#login').off('click').on('click', loginAction);
    $('#add-modal-login').modal('show');
}

function loginAction(){
    if (!loginValidar()) return;
    usuario = {
        cedula: $("#cedulaLogin").val(),
        clave: $("#contrasenaLogin").val()
    };
    let request = new Request(url+'api/login', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-login #addErrorDivLogin"));return;}
        usuario = await response.json();
        sessionStorage.setItem('user', JSON.stringify(usuario));    
        if(usuario.tipo == 0){
            $('#add-modal-login').modal('hide');
            document.location = url + "presentation/principal.html";     
        }
        if(usuario.tipo == 1){
            let request = new Request(url+'api/login', {method: 'GET', headers: {}});
            (async ()=>{
                const response = await fetch(request);
                cliente = await response.json();
                sessionStorage.setItem('persona', JSON.stringify(cliente));
                $('#add-modal-login').modal('hide');
                document.location = url + "presentation/principal.html";                         
            })();
        } 
    })();
    
    
    
}

function logoutAction(){
    let request = new Request(url+'api/login', {method: 'DELETE', headers: { }});
        (async ()=>{
            const response = await fetch(request);
            //if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            sessionStorage.removeItem('user');
            document.location = url+"presentation/principal.html";                         
        })();
}

function loginValidar(){
    $("#formularioLogin").addClass("was-validated");
    return $("#formularioLogin").get(0).checkValidity(); 
}

function errorMessage(status,place){  
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

function resetLogin(){
    usuario = {cedula: "", clave: "", tipo: 0};
}

function makenew_Login(){
    resetLogin();
    renderLogin();
}

function loadLogin(){
    $("#loginP").click(makenew_Login);
    $("#logoutP").click(logoutAction); 
}

$(loadLogin);