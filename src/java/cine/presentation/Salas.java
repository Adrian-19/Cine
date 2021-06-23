/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;

import cine.logic.Service;
import cine.logic.Sala;
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

@Path("/salas")
public class Salas {
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public void add(Sala s) {  
        try {
             cine.logic.Service service = cine.logic.Service.instance();
             service.addSala(s);
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sala> findAll( @DefaultValue("") @QueryParam("nombre") String nombre) {   
        try {
             cine.logic.Service service = cine.logic.Service.instance();
             return service.findAllSalas(); 
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    } 
}
