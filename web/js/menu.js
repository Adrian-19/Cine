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
                      <a class="nav-link active" aria-current="page" href="#" id="crearPelicula">Registrar Pelicula</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="programarProyeccion">Programar Proyeccion</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="registrarSala">Registrar Sala</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#" id="logoutP">Cerrar sesion</a>
                    </li>
                    `;
                  }
                  if([1].includes(usuario.tipo)){
                    menu+=`
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
<<<<<<< HEAD
                    <a class="nav-link active" aria-current="page" href="/Cine/presentation/RegistroPeliculas/registroPeliculas.html" >Register Pelicula</a>
=======
                    <a class="nav-link active" aria-current="page" href="#">Registrarme</a>
>>>>>>> 94f9ce2fd8d4e2da42e43a60578ff6dcc79299b1
                  </li>
                  
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#" id="ver-asientos">Ver asientos</a>
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