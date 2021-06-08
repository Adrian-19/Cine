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
}
