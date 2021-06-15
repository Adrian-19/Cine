var url="http://localhost:8080/Cine/";
var usuario = {cedula: "", clave: "", tipo: 0};
var mode= 'A';

function renderLogin(){
    console.log("render");
    $('#login').off('click').on('click', loginAction);
    $('#add-modal-login').modal('show');
}

function loginAction(){
    usuario = {
        cedula: $("#cedulaLogin").val(),
        clave: $("#contraseñaLogin").val()
    };
    let request = new Request(url+'api/login', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
    (async ()=>{
        const response = await fetch(request);
        //if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
        usuario = await response.json();
        sessionStorage.setItem('user', JSON.stringify(usuario));
        $('#loginP').modal('hide');                         
    })(); 
}

function resetLogin(){
    usuario = {cedula: "", clave: "", tipo: 0};
}

function makenew_Login(){
    console.log("make");
    resetLogin();
    renderLogin();
}

function loadLogin(){
    console.log("LOADED");
    $("#loginP").click(makenew_Login);
}

$(loadLogin);