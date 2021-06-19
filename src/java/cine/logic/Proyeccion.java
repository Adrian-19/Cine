/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ESCINF
 */
public class Proyeccion {
    int id;
    String fecha;
    String hora;
    Pelicula pelicula;
    Sala sala;
    List<String> asientos;
    List<Compra> compras;
    
    public Proyeccion(int id, String fecha, String hora, Pelicula pelicula, Sala sala,List<String> asientos,List<Compra> compras) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pelicula = pelicula;
        this.sala = sala;
        this.asientos=asientos;
        this.compras=compras;
    }
    
    public Proyeccion() {
        this.id = 0;
        this.fecha = "";
        this.hora = "";
        this.pelicula = new Pelicula();
        this.sala = new Sala();
        this.asientos=new ArrayList();
        this.asientos=new ArrayList();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<String> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<String> asientos) {
        this.asientos = asientos;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
    
    
    
}
