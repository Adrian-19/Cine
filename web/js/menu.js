var menu = `
<nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
              <a class="navbar-brand" href="#">Home</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link active" data-toggle='modal' data-target='#add-modal' aria-current="page" href="#">Login</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Register</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#" id="crearPelicula">Register Pelicula</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#" id="ver-asientos">Ver asientos</a>
                  </li>

                </ul>
                <form class="d-flex">
                  <input class="form-control me-2" id="busqueda" type="search" placeholder="Search Movie" aria-label="Search">
                  <button class="btn btn-outline-success" type="button" id="buscar">Search</button>
                </form>
              </div>
            </div>
        </nav>
`;

function loadMenu(){
    $('body').prepend(menu); 
  }
  
$(loadMenu);  