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
    Proyeccion proyeccion;
    Cliente cliente;
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
        proyeccion = new Proyeccion();
        cliente = new Cliente();
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

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
