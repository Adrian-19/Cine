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

/**
 *
 * @author ESCINF
 */
public class PeliculaDao {
    
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
