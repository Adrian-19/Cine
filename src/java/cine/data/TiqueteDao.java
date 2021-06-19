/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import cine.logic.Compra;
import cine.logic.Tiquete;
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
public class TiqueteDao {
    
 public List<Tiquete> tiquetesCompra(int idCom) {
        List<Tiquete> tiquetes = new ArrayList();
             try {
                 String sql="select * from tiquete where idCompra=?";
                 PreparedStatement stm = Database.instance().prepareStatement(sql);
                 stm.setInt(1,idCom);
                 ResultSet rs =  Database.instance().executeQuery(stm);
                 while(rs.next()) {
                     Tiquete t = from(rs);
                     tiquetes.add(t);
                 }   
             } catch (Exception ex) {
                 
             }
        return tiquetes;
    }
    
     public List<String> asientosOcupados(int idPro) {
        List<String> asientos = new ArrayList();
        CompraDao comDao = new CompraDao();
        List<Compra> compras = comDao.compraProyeccion(idPro);
       
        for(Compra c : compras){
             try {
                 String sql="select * from tiquete where idCompra=?";
                 PreparedStatement stm = Database.instance().prepareStatement(sql);
                 stm.setInt(1,c.getId());
                 ResultSet rs =  Database.instance().executeQuery(stm);
                 while(rs.next()) {
                     Tiquete t = from(rs);
                     asientos.add(String.valueOf(t.getNumeroAsiento()));
                 }    } catch (Exception ex) {
                 
             }
        }
        return asientos;
    }
    
    
    
    
        public Tiquete read(int id) throws Exception{
        String sql="select * from tiquete where numero=?";
        PreparedStatement stm = Database.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  Database.instance().executeQuery(stm);           
        if (rs.next()) {
                Tiquete t = from(rs);
                return t;
        }
        else{
            throw new Exception ("Tiquete no Existe");
        }
    }
    
    public Tiquete from(ResultSet rs){
        try{
            Tiquete t = new Tiquete();
            t.setId(rs.getInt("numero"));
            t.setNumeroAsiento(rs.getInt("numeroButaca"));
            t.setIdCompra(rs.getInt("idCompra"));
            return t;
            
        }catch(SQLException ex){
            return null;
        }
    }
}
