/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.tiquetes;

import cine.logic.Compra;
import cine.logic.Proyeccion;
import cine.logic.Tiquete;

import java.util.ArrayList;
import java.util.List;

public class Model {
    Compra seleccion;
    Proyeccion proyeccion;
    List<Compra> compras;
    List<Tiquete> tiquetes;

    public Model() {
        this.reset();
    }

    public void reset() {
        List<Compra> lcompras = new ArrayList<>();
        List<Tiquete> ltiquete= new ArrayList<>();
        proyeccion =new Proyeccion();
        seleccion = new Compra();
        setCompras(lcompras);
        setTiquetes(ltiquete);
  
    }

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(List<Tiquete> tiquetes) {
        this.tiquetes = tiquetes;
    }

    public Compra getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Compra seleccion) {
        this.seleccion = seleccion;
    }

    

}
