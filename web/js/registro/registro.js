
 var usuario ={cedula:"", nombre:"",contraseña:""};
 var usuarios = new Array();
 var url="http://localhost:8080/Cine/";

  function renderReg(){
	$("#cedulaReg").val(usuario.cedula);
	$("#nombreReg").val(usuario.nombre);
        $("#contrasenaReg").val(usuario.contraseña);
        $("#cedula" ).prop( "readonly", false );
        $('#registrarse').off('click').on('click', registrar);

        $('#add-modal-registrar-cli').modal('show');        
  }
  
    function loadReg(){
        usuario = Object.fromEntries( (new FormData($("#formulario-registrar-cli").get(0))).entries());       
    }
    
    function resetReg(){
        usuario={cedula:"", nombre:"",contraseña:""}; 
    }    
 
  function registrar(){
    loadReg();
    if(!validarReg()) 
        return;
    
    let request =  new Request(url+'api/registros', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
        (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status,$("#add-modal-registrar-cli #errorDiv"));return;}
//        fetchAndListP();
        resetReg();
        $('#add-modal-registrar-cli').modal('hide');                
    })();     
       
//    usuarios.push(usuario);
//    console.log(listar_usuarios());//solo para probar
//    reset();
//    $('#add-modal').modal('hide');
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

//    function listar_usuarios(){
//        txt = '';
//        usuarios.forEach(function(e){txt += " Usuario : " + e.nombre + "  Contaseña : " + e.contraseña + " \n"; });
//        return txt;
//    };