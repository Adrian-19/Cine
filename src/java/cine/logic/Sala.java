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
public class Sala {
    int id;
    String numeroSala;
    int cantidadFilas;

    public Sala(int id, String numeroSala, int cantidadFilas) {
        this.id = id;
        this.numeroSala = numeroSala;
        this.cantidadFilas = cantidadFilas;
    }
    
    public Sala() {
        this.id = 0;
        this.numeroSala = "";
        this.cantidadFilas = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }
    
    
}
