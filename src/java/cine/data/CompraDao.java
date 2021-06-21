/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Compra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanuelle
 */
public class CompraDao {
        public List<Compra> listAll() {
        List<Compra> compras = new ArrayList();
        try {
            
            String sql="select * from compra";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            ResultSet rs =  Database.instance().executeQuery(stm);
            while(rs.next()) {
                Compra c = from(rs);
                compras.add(c);
            }
            
        } catch (Exception ex) {
            
        }
        return compras;
    }
    
    public int add(Compra compra) throws Exception{
        int cont = 1;
        
        String sql = "insert into compra(cedula, nombre, idCliente, idProyeccion)"
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setString(1, compra.getCedula());
        stm.setString(2, compra.getNombre());
        
        if(compra.getCliente()!=null){
            if(!compra.getCliente().getCedula().equals("")){
                stm.setString(3, compra.getCliente().getCedula());
            }
            else{
                stm.setString(3, null);
            }
        }
        stm.setInt(4, compra.getProyeccion().getId());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Compra ya existe");
        }
        
        sql = "select * from compra";
        stm = Database.instance().prepareStatement(sql);
        ResultSet rs =  Database.instance().executeQuery(stm);
        while(rs.next()) {
            Compra c = from(rs);
            cont = c.getId();
        }
        return cont;
    }   
    
    public List<Compra> compraProyeccion(int idPro) {
        List<Compra> compras = new ArrayList();
        try {
            
            String sql="select * from compra where idProyeccion=?";
            PreparedStatement stm = Database.instance().prepareStatement(sql);
            stm.setInt(1, idPro);
            ResultSet rs =  Database.instance().executeQuery(stm);
            while(rs.next()) {
                Compra c = from(rs);
                compras.add(c);
            }
            
        } catch (Exception ex) {
            
        }
        return compras;
    }
    
    public Compra read(int id) throws Exception{
        String sql="select * from compra where id=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
                Compra c = from(rs);
                return c;
        }
        else{
            throw new Exception ("Compra no Existe");
        }
    }
    
    public Compra from(ResultSet rs){
        try{
            Compra c = new Compra();
            c.setId(rs.getInt("id"));
            c.setCedula(rs.getString("cedula"));
            c.setNombre(rs.getString("nombre"));
            c.setidProyeccion(rs.getInt("idProyeccion"));
            return c;
            
        }catch(SQLException ex){
            return null;
        }
    }
    
}
