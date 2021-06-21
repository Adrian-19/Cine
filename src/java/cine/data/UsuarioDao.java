/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Sala;
import cine.logic.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ESCINF
 */
public class UsuarioDao {
    
    public Usuario read(String cedula) throws Exception{
        String sql="select * from usuario where cedula=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("Usuario no Existe");
        }
    }
    
    public Usuario from(ResultSet rs){
        try{
            Usuario u = new Usuario();
            u.setCedula(rs.getString("cedula"));
            u.setClave(rs.getString("clave"));
            u.setTipo(rs.getInt("tipo"));
            return u;
        }catch(SQLException ex){
            return null;
        }
    }
    
    public void create(Usuario u) throws Exception {
        String sql = "insert into usuario (cedula, clave, tipo)   " + 
                "values(?,?,?)"; 
        PreparedStatement stm = Database.instance().prepareStatement(sql); 
        stm.setString(1, u.getCedula());
        stm.setString(2, u.getClave());
        stm.setInt(3, u.getTipo()); //
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario ya existe");
        }
    }
}
