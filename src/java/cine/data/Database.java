/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class Database {
    private static Database theInstance;
    public static Database instance(){
        if (theInstance==null){ 
            theInstance=new Database();
        }
        return theInstance;
    }
    public static final String PROPERTIES_FILE_NAME="/db.properties";        
    Connection cnx;
    public Database(){
        cnx=this.getConnection();            
    }
    public Connection getConnection(){
        try {
            String driver= "com.mysql.cj.jdbc.Driver";
            String server= "localhost";
            String port="3306";
            String user="root";

            String password= "Root";
            //String password= "admin1907";   
            String database = "cine";
            String URL_conexion="jdbc:mysql://"+ server+":"+port+"/"+
            database+"?user="+user+"&password="+password+"&serverTimezone=UTC&useSSL=False ";            

            Class.forName(driver).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.out.println("excepcion en getConnection");
            System.err.println(e.getMessage());
            System.exit(-1);
        } 
        return null;
    }
    
    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return cnx.prepareStatement(statement);
    }
    public int executeUpdate(PreparedStatement statement) {
        try {
            statement.executeUpdate();
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            System.out.println("Error en Execute update");
            return 0;
        }
    }
    public ResultSet executeQuery(PreparedStatement statement){
        try {
            return statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error en executequery");
        }
        return null;
    }    
}
