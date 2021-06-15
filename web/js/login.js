var url="http://localhost:8080/Cine/";

function login(){
    if (!loginValidar()) return;
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
        $('#loginDialog').modal('hide');            
       switch(usuario.tipo){
           case '0': document.location = url+"principal.html"; break;
       }                           
    })(); 
    }

function loginValidar(){
    $("#loginForm").addClass("was-validated");
    return $("#loginForm").get(0).checkValidity(); 
}

function loadLogin(){
    let request = new Request(url+'login.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            //if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $("#login").click(login);
            //$("#logout").click(logout);                          
        })();  
}

$(loadLogin);