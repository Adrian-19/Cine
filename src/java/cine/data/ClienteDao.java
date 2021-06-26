/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ESCINF
 */
public class ClienteDao {
    
    public Cliente read(String cedula) throws Exception{
        String sql="select * from cliente where cedula=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("Cliente no Existe");
        }
    }
    
    
    public Cliente from(ResultSet rs){
        try{
            Cliente c = new Cliente();
            c.setCedula(rs.getString("cedula"));
            c.setNombre(rs.getString("nombre"));
            return c;
        }catch(SQLException ex){
            return null;
        }
    }
    
    public void create(Cliente c) throws Exception {
        String sql = "insert into cliente (cedula, nombre, idUsuario)   " + 
                "values(?,?,?)"; 
        PreparedStatement stm = Database.instance().prepareStatement(sql); 
        stm.setString(1, c.getCedula());
        stm.setString(2, c.getNombre());
        stm.setInt(3, Integer.parseInt(c.getCedula())); //
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario ya existe");
        }
    }
}
