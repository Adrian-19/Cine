/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;

import cine.logic.Proyeccion;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/principal")
public class Principal {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Proyeccion> search(@DefaultValue("") @QueryParam("nombre") String nombre) {
        
        List<Proyeccion> list = new ArrayList<>();
        cine.logic.Service service = cine.logic.Service.instance();
        if(nombre.equals("")){
            list = service.getListaProyecciones();
        }
        else{
            list = service.getProyeccionesPorNombre(nombre);
            System.out.println(list);
        }
        return list;
    }
}
