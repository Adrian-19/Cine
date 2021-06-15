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
public class Usuario {
    String cedula;
    String clave;
    int tipo;

    public Usuario(String cedula, String clave, int tipo) {
        this.cedula = cedula;
        this.clave = clave;
        this.tipo = tipo;
    }
    
    public Usuario() {
        this.cedula = "";
        this.clave = "";
        this.tipo = 0;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
