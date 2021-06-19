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
public class Tiquete {
    int id;
    int numeroAsiento;
    //Compra compra;
    int idCompra;
    
    public Tiquete(int id, int numeroAsiento, int idCompra) {
        this.id = id;
        this.numeroAsiento = numeroAsiento;
        this.idCompra = idCompra;
    }

    public Tiquete() {
        this.id = 0;
        this.numeroAsiento = 1;
        this.idCompra = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
    
}
