package edu.poli.proyectooodle.modelo;

import java.util.ArrayList;
import java.util.List;


public class Intento {

    private List<Integer> valores;

    public Intento(List<Integer> valores) {
        this.valores = new ArrayList<>(valores);
    }

    public boolean correcto(Ecuacion ecuacion)
    {
        if (ecuacion.calcular(valores.get(0), valores.get(1), valores.get(2), valores.get(3)) == ecuacion.getResultado() && ecuacion.verificarReglas(valores))
        {
            return true;
        }
        return false;
    }


    public List<Integer> getValores() { return valores; }
}