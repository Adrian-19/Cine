var fila_asientos = 6;
var colum_asientos = 8;
var num_asi = 1;
var proyeccion = {id: "", precio: 1000, estado: "", nombre: "", asientos: ["1", "2"]};
var selec = new Array();
var cargado = false;


function render_asientos() {
    var cont = 1;
    var panel = $('#panel-asientos');
    cargarFilas(panel, cont);
    $('#add-modal-asientos').modal('show');
    cargado = true;

}


function cargarAsientos(fila) {

    for (var j = 0; j < colum_asientos; j++) {
        var asiento = $("<div />");
        asiento.addClass("seat");
        asiento.attr("id", "seat");
        asiento.attr("data-value", num_asi.toString());
        if (proyeccion.asientos.includes(num_asi.toString())) {
            asiento.addClass("occupied");
        } else {
            if (selec.includes(num_asi.toString())) {
                asiento.addClass("selected");

            }
            asiento.on('click', function () {
                if ($(this).hasClass("selected") && cargado) {
                    var i = selec.indexOf($(this).attr("data-value"));
                    if (i !== -1) {
                        selec.splice(i, 1);
                    }
                    $(this).removeClass("selected");

                } else {
                    if (!$(this).hasClass("occupied") && cargado) {

                        $(this).addClass("selected");
                        selec.push($(this).attr("data-value"));
                    }

                }
                var total = selec.length * proyeccion.precio;
                $('#count').text(selec.length);
                $('#total').text(total);
                console.log(JSON.stringify(selec));
            });
        }
        fila.append(asiento);
        num_asi++;
        console.log("Se añadio un asiento");
    }


}

function cargarFilas(panel) {
    num_asi = 1;
    for (var i = 0; i < fila_asientos; i++) {
        var div = $("<div />");
        div.addClass("fila");
        cargarAsientos(div);
        panel.append(div);
        console.log("Se añadio una fila");

    }


}





//function seleccionar() {
//    if ($(this).hasClass("selected") && cargado) {
//        var i = selec.indexOf($(this).attr("data-value"));
//        if (i !== -1) {
//            selec.splice(i, 1);
//        }
//        $(this).removeClass("selected");
//
//    } else {
//        if (!$(this).hasClass("occupied") && cargado) {
//
//            $(this).addClass("selected");
//            selec.push($(this).attr("data-value"));
//        }
//
//    }
//    var total = selec.length * proyeccion.precio;
//    $('#count').text(selec.length);
//    $('#total').text(total);
//    console.log(JSON.stringify(selec));
//
//}

//function load() {
//
//}

function reset_asientos() {
    $(".fila").remove();
}


function makenew_asientos() {
    reset_asientos();
    render_asientos();
}


function loaded_asientos() {
    $("#ver-asientos").click(makenew_asientos);
}

$(loaded_asientos);





