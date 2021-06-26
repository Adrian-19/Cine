
 var usuario ={cedula:"", clave:"",tipo:"1"};
 var cliente = {cedula:"", nombre:"",idUsuario:""}; 
var url="http://localhost:8080/Cine/";

  function renderReg(){
	$("#cedulaReg").val(usuario.cedula);
        $("#contrasenaReg").val(usuario.clave);
        
        $("#cedulaReg").val(cliente.cedula);
	$("#nombreReg").val(cliente.nombre);
        $("#cedulaReg").val(cliente.idUsuario);
       
        $('#registrarse').off('click').on('click', registrar);
        $('#add-modal-registrar-cli').modal('show');        
  }
  
    function resetReg(){
        var usuario ={cedula:"", clave:"",tipo:"1"};
        var cliente = {cedula:"", nombre:"",idUsuario:""};  
    }    
 
  function registrar(){
    
    if(!validarReg()) 
        return;
    usuario = {
        cedula: $("#cedulaReg").val(),
        clave: $("#contrasenaReg").val(), 
        tipo: "1"
    }; 
    cliente = {
        cedula: $("#cedulaReg").val(),
        nombre: $("#nombreReg").val(), 
        idUsuario: $("#cedulaReg").val()  
    }; 
    
    // Registrar usuario: 
    let request =  new Request(url+'api/registroUsuario', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
      (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-registrar-cli #addErrorDivRegCli"));return;}     
    })();     
    
    //Registrar cliente: 
    let requestCli =  new Request(url+'api/registroCliente', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(cliente)});
      (async ()=>{
        const responseCli = await fetch(requestCli);
        if (!responseCli.ok) {errorMessage(responseCli.status,$("#add-modal-registrar-cli #addErrorDivRegCli"));return;}

        resetReg();
        $('#add-modal-registrar-cli').modal('hide');                
    })();
  } 
  
  function validarReg(){
    var error=false;
    $("#formulario-registrar-cli input").removeClass("invalid");
    error |= $("#formulario-registrar-cli input[type='text']").filter( (i,e)=>{ return e.value=='';}).length>0;        
    $("#formulario-registrar-cli input[type='text']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
    error |= $("#formulario-registrar-cli input[type='password']").filter( (i,e)=>{ return e.value=='';}).length>0;        
    $("#formulario-registrar-cli input[type='password']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
    
    return !error;
  }
  
  
  function makenewReg(){
      resetReg();
      renderReg();
    }
    
  
  function loadedReg(){	
    $("#registerClient").click(makenewReg);   
    console.log(loadedReg);
  }
  
  $(loadedReg);  

