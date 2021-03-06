/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

import cine.data.ClienteDao;
import cine.data.CompraDao;
import cine.data.ProyeccionDao;


import cine.data.TiqueteDao;
import cine.data.UsuarioDao;
import cine.data.PeliculaDao;
import cine.data.SalaDao;
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
    
    private ClienteDao clienteDao;
    
    private CompraDao compraDao;
    
    private TiqueteDao tiqueteDao;
    
    private SalaDao salaDao;
  
       
    public Service(){
        proyeccionDao = new ProyeccionDao();
        usuarioDao = new UsuarioDao();
        peliculaDao = new PeliculaDao();
        compraDao = new CompraDao();
        tiqueteDao = new TiqueteDao();
        salaDao = new SalaDao();
        clienteDao = new ClienteDao();
    }

    // ------------ PROYECCIONES -------------
    public List<Proyeccion> getListaProyecciones() {
        return proyeccionDao.findAll();
    }

    
    public List<Proyeccion> getProyeccionesPorNombre(String nombre){
        return proyeccionDao.findByName(nombre);
    }
    
    public Proyeccion getProyeccionesPorId(int idPro) throws Exception{
        return proyeccionDao.read(idPro);
    }
    
    public void addProyeccion(Proyeccion p){
        try {
            proyeccionDao.create(p); 
            
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // ------------ USUARIO -------------
    
    public Usuario getUsuario(String cedula) throws Exception{
        return usuarioDao.read(cedula);
    }
    
     public void addUsuario(Usuario u){
        try {
            usuarioDao.create(u);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ------------ CLIENTE -------------
    
    public Cliente getCliente(String cedula) throws Exception{
        return clienteDao.read(cedula);
    }
    
    public void addCliente(Cliente c){
        try {
            clienteDao.create(c);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void a??adirPelicula(Pelicula p) {
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
    // ------------ Compras -------------
    public List<Compra> getListaCompras() {
        List<Compra> arr =  compraDao.listAll();
        return arr;
    }

    
    public List<Compra> compraPorCliente(int id) {
        return compraDao.compraPorCliente(id); 

    }
    

        
    public int registrarCompra(Compra c){
       try {
            return compraDao.add(c);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;
    }

    //------------ Tiquetes -------------
    public List<Tiquete> getListaTiquetesCompra(int idCom) {
        List<Tiquete> arr =  tiqueteDao.tiquetesCompra(idCom);
        return arr;
    }
    
    public void addTiquete(Tiquete t){
        try {
            tiqueteDao.add(t);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ------------ SALAS -------------
    
    public void addSala(Sala s){
        try {
            salaDao.create(s);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Sala> findAllSalas() throws Exception{
        return salaDao.findAll(); 
    }
}
