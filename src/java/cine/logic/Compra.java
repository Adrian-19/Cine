/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

/**
 *
 * @author emanuelle
 */
public class Compra {
    int id;
    String cedula;
    String nombre;
    //Proyeccion proyeccion;
    int idProyeccion;

    public Compra(int id, String cedula, String nombre, int idProyeccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.idProyeccion = idProyeccion;
    }
    
    public Compra() {
        this.id = 0;
        this.cedula = "";
        this.nombre = "";
        this.idProyeccion = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getidProyeccion() {
        return idProyeccion;
    }

    public void setidProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }
    
    
    
}
