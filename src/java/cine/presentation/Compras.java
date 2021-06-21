/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;

import cine.logic.Cliente;
import cine.logic.Compra;
import cine.logic.Tiquete;
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
import java.util.Arrays;
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
 * @author ESCINF
 */

@Path("/compras")
public class Compras {
    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int registrarCompra(Compra compra){
        int id = 0;
        try {
             cine.logic.Service service = cine.logic.Service.instance();
             id = service.registrarCompra(compra);
             return id;
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void registrarTiquetes(Integer[] asientos){
        int idCompra = asientos[0];
        cine.logic.Service service = cine.logic.Service.instance();
        Tiquete t = new Tiquete();
        for(int i = 1; i<asientos.length; i++){
            t.setNumeroAsiento(asientos[i]);
            t.setIdCompra(idCompra);
            service.addTiquete(t);
        }
    }
}
