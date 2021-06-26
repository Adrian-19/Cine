/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Sala;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ESCINF
 */
public class SalaDao {
    
    public Sala read(int id) throws Exception{
        String sql="select * from sala where id=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("Sala no Existe");
        }
    }
    
    public void create(Sala s) throws Exception{
        String sql = "insert into sala(numeroSala, cantidadFilas)"
                + "values(?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, s.getNumeroSala());
        stm.setInt(2, s.getCantidadFilas());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Sala ya existe");
        }
    }
    
    public Sala from(ResultSet rs){
        try{
            Sala s = new Sala();
            s.setId(rs.getInt("id"));
            s.setNumeroSala(rs.getString("numeroSala"));
            s.setCantidadFilas(rs.getInt("cantidadFilas"));
            return s;
        }catch(SQLException ex){
            return null;
        }
    }
    
    public List<Sala> findAll() throws Exception{
       List<Sala> salas = new ArrayList(); 
       String sql="select * from sala";
       PreparedStatement stm = Database.instance().prepareStatement(sql);
       ResultSet rs =  Database.instance().executeQuery(stm);
       while(rs.next()) {
                Sala c = from(rs);
                salas.add(c);
        }
       return salas; 
    }
}
