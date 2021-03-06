/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Proyeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cine.data.PeliculaDao;

/**
 *
 * @author ESCINF
 */
public class ProyeccionDao {
    
    public List<Proyeccion> findAll(){
        List<Proyeccion> proyecciones = new ArrayList<>(); 
        String sql = "select * from proyeccion"; 
        PeliculaDao peliDao = new PeliculaDao();
        SalaDao salaDao = new SalaDao();
        TiqueteDao tiqDao = new TiqueteDao();
        CompraDao comDao = new CompraDao();
        try{
            PreparedStatement stm = Database.instance().prepareStatement(sql); 
            ResultSet rs = Database.instance().executeQuery(stm); 
            while(rs.next()){ 
                Proyeccion p = from(rs);
                int idPeli = p.getPelicula().getId();
                p.setPelicula(peliDao.read(idPeli));
                int idSala = p.getSala().getId();
                p.setSala(salaDao.read(idSala));
                p.setAsientos(tiqDao.asientosOcupados(p.getId()));
                p.setCompras(comDao.compraProyeccion(p.getId()));
                
                proyecciones.add(p); 
            }
        } catch (Exception ex) { }
        
        return proyecciones; 
    }
    
    public List<Proyeccion> findByName(String nombre){
        List<Proyeccion> proyecciones = new ArrayList<>();
        String sql = "select p.id, p.fecha, p.hora, p.idPelicula, p.idSala from proyeccion p inner join pelicula e on p.idPelicula = e.id where e.nombre like '%" + nombre + "%'";
        System.out.println("sql: " + sql);
        PeliculaDao peliDao = new PeliculaDao();
        SalaDao salaDao = new SalaDao();
        try{
            PreparedStatement stm = Database.instance().prepareStatement(sql); 
            ResultSet rs = Database.instance().executeQuery(stm); 
            while(rs.next()){ 
                Proyeccion p = from(rs);
                int idPeli = p.getPelicula().getId();
                p.setPelicula(peliDao.read(idPeli));
                int idSala = p.getSala().getId();
                p.setSala(salaDao.read(idSala));
                
                proyecciones.add(p); 
            }
        } catch (Exception ex) { }
        
        return proyecciones; 
    }
    
    public Proyeccion from(ResultSet rs){
        try{
            Proyeccion pro = new Proyeccion();
            pro.setId(rs.getInt("id"));
            pro.setFecha(rs.getString("fecha"));
            pro.setHora(rs.getString("hora"));
            pro.getPelicula().setId(rs.getInt("idPelicula"));
            pro.getSala().setId(rs.getInt("idSala"));
            return pro;
            
        }catch(SQLException ex){
            return null;
        }
    }
    
    public Proyeccion read(int id) throws Exception{
        String sql="select * from proyeccion where id=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        PeliculaDao peliDao = new PeliculaDao();
        SalaDao salaDao = new SalaDao();
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            Proyeccion p = from(rs);
                int idPeli = p.getPelicula().getId();
                p.setPelicula(peliDao.read(idPeli));
                int idSala = p.getSala().getId();
                p.setSala(salaDao.read(idSala));
                return p;
        }
        else{
            throw new Exception ("Pelicula no Existe");
        }
    }

    public void create(Proyeccion p) throws Exception {
        String sql = "insert into proyeccion (fecha, hora, idPelicula, idSala)   " + 
        "values(?,?,?, ?)"; 
        PreparedStatement stm = Database.instance().prepareStatement(sql); 
        stm.setString(1, p.getFecha());
        stm.setString(2, p.getHora());
        stm.setInt(3, p.getPelicula().getId()); 
        stm.setInt(4,p.getSala().getId()); 
        
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario ya existe");
        }
        
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
