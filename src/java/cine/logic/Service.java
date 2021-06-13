/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

import cine.data.ProyeccionDao;
import cine.data.UsuarioDao;
import java.util.List;

/**
 *
 * @author ESCINF
 */
public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }
    
    private ProyeccionDao proyeccionDao;
    private UsuarioDao usuarioDao;
    
    public Service(){
        proyeccionDao = new ProyeccionDao();
        usuarioDao = new UsuarioDao();
    }
    
    // ------------ PROYECCIONES -------------
    public List<Proyeccion> getListaProyecciones(){
        return proyeccionDao.findAll();
    }
    
    // ------------ USUARIO -------------
    
    public Usuario getUsuario(String cedula) throws Exception{
        return usuarioDao.read(cedula);
    }
}
