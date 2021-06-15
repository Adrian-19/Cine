/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.presentation;

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

@Path("/login")
public class Login {
    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Usuario login(Usuario usuario) {  
            Usuario logged=null;
            
            try {
                logged = cine.logic.Service.instance().getUsuario(usuario.getCedula());
                if(!logged.getClave().equals(usuario.getClave())) throw new Exception("Clave incorrecta");
                request.getSession(true).setAttribute("user", logged);
                return logged;
            } catch (Exception ex) {
                throw new NotFoundException();
            }  
    }
    
    @DELETE 
    public void logout() {  
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");           
        session.invalidate();
    }
}
