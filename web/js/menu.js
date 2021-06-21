var menu = `
<nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
              <a class="navbar-brand" href="/Cine/presentation/principal.html">CNG</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">`;
                let usuarioJson = sessionStorage.getItem('user');
                
                if (usuarioJson!=null){ 
                  let usuario= JSON.parse(usuarioJson);
                  
                  if ([0].includes(usuario.tipo)){
                    menu+=`
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/Cine/presentation/RegistroPeliculas/registroPeliculas.html">Registrar Pelicula</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="programarProyeccion">Programar Proyeccion</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="regSala">Registrar Sala</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="logoutP">Cerrar sesion</a>
                    </li>
                    <li class="nav-item">
                     <a class="nav-link active" href="/Cine/presentation/Tiquetes/showRecord">Tiquetes</a>
                    </li> 
                    `;
                  }
                  if([1].includes(usuario.tipo)){
                    let personaJson = sessionStorage.getItem('persona');
                    let persona= JSON.parse(personaJson);  
                    menu+=`
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">${persona.nombre}</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="verCompras">Mis compras</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="logoutP">Cerrar sesion</a>
                    </li>
                    `;
                  }
                }
                else{
                  menu+=`
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#" id="loginP">Iniciar sesion</a>
                  </li>
 
                   <li class="nav-item">
                    <a class="nav-link active" aria-current="page"  href="#" id="registerClient">Registrarse </a>
                  </li>
                  `;
                }
                  
                menu+=`
                </ul>
                <form class="d-flex">
                  <input class="form-control me-2" type="search" id="busqueda" placeholder="Search Movie" aria-label="Search">
                  <button class="btn btn-outline-success" type="button" id="buscarPrincipal">Search</button>
                </form>
              </div>
            </div>
        </nav>
`;

function loadMenu(){
    $('body').prepend(menu); 
  }
  
$(loadMenu);  