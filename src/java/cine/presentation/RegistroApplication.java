/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class RegistroApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(MultiPartFeature.class);
        classes.add(Principal.class); 
        classes.add(Peliculas.class);
        classes.add(Login.class);
        classes.add(Salas.class);
        classes.add(RegistroCliente.class); 
        classes.add(RegistroUsuario.class); 
        classes.add(RegistrarProyeccion.class);
        classes.add(VerCompras.class);
        classes.add(Compras.class);
<<<<<<< HEAD
        classes.add(registroCliente.class);
=======

>>>>>>> 7d256ed65a6f74275e8fb15048ae444055514cdc
        return classes;
    }   
}
