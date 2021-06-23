/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

import java.util.List;

/**
 *
 * @author emanuelle
 */
public class Compra {
    int id;
    String cedula;
    String nombre;
<<<<<<< HEAD
=======
    Proyeccion proyeccion;
    Cliente cliente;
>>>>>>> b54e76ca0f31945a88861c96017809db947a6329
    int idProyeccion;
    
    Proyeccion proyeccion;
    List<Tiquete> listaTiq; 

    public Compra(int id, String cedula, String nombre, int idProyeccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.idProyeccion = idProyeccion;
        this.proyeccion = null; 
        this.listaTiq = null; 
    }
    
    public Compra() {
        this.id = 0;
        this.cedula = "";
        this.nombre = "";
        proyeccion = new Proyeccion();
        cliente = new Cliente();
        this.idProyeccion = 0;
        this.proyeccion = null; 
        this.listaTiq = null; 
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

<<<<<<< HEAD
    public List<Tiquete> getListaTiq() {
        return listaTiq;
    }

=======
>>>>>>> b54e76ca0f31945a88861c96017809db947a6329
    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

<<<<<<< HEAD
    public void setListaTiq(List<Tiquete> listaTiq) {
        this.listaTiq = listaTiq;
=======
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
>>>>>>> b54e76ca0f31945a88861c96017809db947a6329
    }
    
    
    
}
