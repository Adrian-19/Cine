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
public class Proyeccion {
    int id;
    String fecha;
    String hora;
    Pelicula pelicula;
    Sala sala;

    public Proyeccion(int id, String fecha, String hora, Pelicula pelicula, Sala sala) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pelicula = pelicula;
        this.sala = sala;
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
    
    
}
