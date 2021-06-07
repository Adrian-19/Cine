/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 var usuario ={cedula:"", nombre:"",contraseña:""};
 var usuarios = new Array();

  function render(){
	$("#cedula").val(usuario.cedula);
	$("#nombre").val(usuario.nombre);
        $("#contraseña").val(usuario.contraseña);
        $("#cedula" ).prop( "readonly", false );
        $('#registrarse').off('click').on('click', registrar);

        $('#add-modal').modal('show');        
  }
  
    function load(){
        usuario = Object.fromEntries( (new FormData($("#formulario").get(0))).entries());       
    }
    
    function reset(){
        usuario={cedula:"", nombre:"",contraseña:""}; 
    }    
 
  function registrar(){
    load();
    if(!validar()) return;
    usuarios.push(usuario);
    console.log(listar_usuarios());//solo para probar
    reset();
    $('#add-modal').modal('hide');
  } 
  
  
  function validar(){
    var error=false;
    $("#formulario input").removeClass("invalid");
    error |= $("#formulario input[type='text']").filter( (i,e)=>{ return e.value=='';}).length>0;        
    $("#formulario input[type='text']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
    error |= $("#formulario input[type='password']").filter( (i,e)=>{ return e.value=='';}).length>0;        
    $("#formulario input[type='password']").filter( (i,e)=>{ return e.value=='';}).addClass("invalid");
    
    return !error;
  }
  
  
  function makenew(){
      reset();
      render();
    }
    
  
  function loaded(){	
    $("#crear").click(makenew);        
  }
  
  $(loaded);  

    function listar_usuarios(){
        txt = '';
        usuarios.forEach(function(e){txt += " Usuario : " + e.nombre + "  Contaseña : " + e.contraseña + " \n"; });
        return txt;
    };