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
    
    public Sala from(ResultSet rs){
        try{
            Sala s = new Sala();
            s.setId(rs.getInt("id"));
            s.setNumeroSala(rs.getString("numeroSala"));
            return s;
        }catch(SQLException ex){
            return null;
        }
    }
}
