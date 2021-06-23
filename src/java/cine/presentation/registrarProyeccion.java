/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;
import cine.logic.Proyeccion;
import cine.logic.Usuario;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author DS
 */

@Path("/registrarProyeccion")
public class RegistrarProyeccion {

    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public void add(Proyeccion p) {
        System.out.println(p.getId() + p.getPelicula().getId() + p.getFecha() + p.getHora());
         try {
             cine.logic.Service service = cine.logic.Service.instance();
             service.addProyeccion(p);
             
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }      
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Proyeccion> findAll() { 
        try {
            cine.logic.Service service = cine.logic.Service.instance();
            return service.getListaProyecciones();
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }  

    }     


    
}
