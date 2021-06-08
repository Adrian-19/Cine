/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {

    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    HashMap<Integer, Pelicula> peliculas;

    private Model() {
        peliculas = new HashMap<Integer, Pelicula>();
        peliculas.put(111, new Pelicula(111, 2000, "En_cartelera", "Shrek"));
        peliculas.put(222, new Pelicula(222, 3000, "Proximamente", "Rango"));
    }

    public List<Pelicula> peliculaListAll() {
        return new ArrayList(peliculas.values());
    }

    public Pelicula peliculaAdd(Pelicula pel) throws Exception {
        if (peliculas.get(pel.getId()) != null) {
            throw new Exception("406-pelicula ya existe");
        } else {
            peliculas.put(pel.getId(), pel);
            return pel;
        }
    }

    public List<Pelicula> peliculaSearch(String nombre) {
        List<Pelicula> result = new ArrayList<>();
        for (Pelicula p : peliculas.values()) {
            if (p.getNombre().contains(nombre)) {
                result.add(p);
            }
        }
        return result;
    }
        public void peliculaUpdate(Pelicula pel)throws Exception {
        if (peliculas.get(pel.getId())==null){
            throw new Exception ("404-pelicula no existe");
        }
        else{
            peliculas.put(pel.getId(), pel);
        }
    }
    
    public Pelicula peliculaEdit(int id)throws Exception {
        if (peliculas.get(id)!=null){
            return peliculas.get(id);
            
        }
        else{
            throw new Exception ("404-pelicula no existe");
        }
    }

}
