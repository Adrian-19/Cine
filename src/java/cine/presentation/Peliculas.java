/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;

import cine.logic.Service;
import cine.logic.Model;
import cine.logic.Pelicula;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author emanuelle
 */
@Path("/peliculas")
public class Peliculas {

    String location="C:/Users/ESCINF/Desktop/pruebas/";
//    String location="C:/Users/emanuelle/Desktop/pruebas/";


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pelicula> search(@DefaultValue("") @QueryParam("nombre") String nombre) { 
        cine.logic.Service service = cine.logic.Service.instance();    
        return service.getListaPeliculas(nombre);
    } 
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Pelicula get(@PathParam("id") String id) {
                cine.logic.Service service = cine.logic.Service.instance();    
        
        try {
            return service.peliculaEdit(Integer.parseInt(id));
   
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    
    @GET
    @Path("{id}/imagen")
    @Produces("image/png")
    public Response getImge(@PathParam("id") String id) throws IOException {
        File file = new File(location+id);
        Response.ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }    

    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public void add(Pelicula p) {  
        try {
             cine.logic.Service service = cine.logic.Service.instance();
             service.añadirPelicula(p);
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
    @Path("{id}/imagen")
    public void addImage(@PathParam("id") String id, @FormDataParam("imagen") InputStream imagenStream) {  
        try{
                int read = 0;
                byte[] bytes = new byte[1024];

                OutputStream out = new FileOutputStream(new File(location + id));
                while ((read = imagenStream.read(bytes)) != -1){out.write(bytes, 0, read);}
                out.flush();
                out.close();
            } catch (Exception ex) {
                throw new NotAcceptableException(); 
            }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Pelicula p) {  
         cine.logic.Service service = cine.logic.Service.instance(); 
        try {
             service.peliculaUpdate(p);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
     
}
