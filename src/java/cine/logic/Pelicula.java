/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

/**
 *
 * @author ESCINF
 */
public class Pelicula {
    int id;
    int precio;
    String nombre;
    String estado;
    
    public Pelicula(int id, int precio, String nombre, String estado) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    public Pelicula() {
        this.id = 0;
        this.precio = 0;
        this.nombre = "";
        this.estado = "";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
