/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

import cine.data.ProyeccionDao;
import cine.data.UsuarioDao;
import cine.data.PeliculaDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private PeliculaDao peliculaDao;
    private UsuarioDao usuarioDao;
    
    public Service(){
        proyeccionDao = new ProyeccionDao();
        usuarioDao = new UsuarioDao();
        peliculaDao = new PeliculaDao();
    }

    // ------------ PROYECCIONES -------------
    public List<Proyeccion> getListaProyecciones() {
        return proyeccionDao.findAll();
    }
    
    public List<Proyeccion> getProyeccionesPorNombre(String nombre){
        return proyeccionDao.findByName(nombre);
    }
    
    // ------------ USUARIO -------------
    
    public Usuario getUsuario(String cedula) throws Exception{
        return usuarioDao.read(cedula);
    }

    // ------------ Peliculas -------------
    public List<Pelicula> getListaPeliculas() {
        return peliculaDao.findAll();
        
    }
    
    public List<Pelicula> getListaPeliculas(String nombre) {
        List<Pelicula> arr =  peliculaDao.findAll();
        List<Pelicula> result = new ArrayList();
        for(Pelicula p:arr){
            if(p.getNombre().toLowerCase().contains(nombre.toLowerCase())) result.add(p);
        }
        return result;
    }

    public void añadirPelicula(Pelicula p) {
        try {
            peliculaDao.create(p);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void peliculaUpdate(Pelicula pel)throws Exception {
             peliculaDao.update(pel);
    }
    
    public Pelicula peliculaEdit(int id)throws Exception {
        return peliculaDao.read(id);
    }
    
    
}
