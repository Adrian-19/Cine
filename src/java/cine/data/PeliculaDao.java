/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ESCINF
 */
public class PeliculaDao {
    
        public void create(Pelicula p) throws Exception {

        String sql = "insert into pelicula(id, precio, estado, nombre) "
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, p.getId());
        stm.setInt(2, p.getPrecio());
        stm.setString(3, p.getEstado());
        stm.setString(4, p.getNombre());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Pelicula ya existe");
        }
    }
    
     public List<Pelicula> findAll(){
        List<Pelicula> peliculas = new ArrayList<>(); 
        String sql = "select * from pelicula"; 
        
        try{
            PreparedStatement stm = Database.instance().prepareStatement(sql); 
            ResultSet rs = Database.instance().executeQuery(stm); 
            while(rs.next()){ 
                Pelicula p = from(rs);
                peliculas.add(p); 
            }
        } catch (Exception ex) { }
        
        return peliculas; 
    }
     
       public void update(Pelicula p) throws Exception{
        String sql="update pelicula set precio=?, estado=?, nombre=? where id=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, p.getPrecio());
        stm.setString(2, p.getEstado());
        stm.setString(3, p.getNombre());
        stm.setInt(4, p.getId());
        ResultSet rs =  Database.instance().executeQuery(stm);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Pelicula no existe");
        }

    }
    
    
    public Pelicula read(int id) throws Exception{
        String sql="select * from pelicula where id=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("Pelicula no Existe");
        }
    }
    
    public Pelicula from(ResultSet rs){
        try{
            Pelicula p = new Pelicula();
            p.setId(rs.getInt("id"));
            p.setPrecio(rs.getInt("precio"));
            p.setEstado(rs.getString("estado"));
            p.setNombre(rs.getString("nombre"));
            return p;
            
        }catch(SQLException ex){
            return null;
        }
    }
}
