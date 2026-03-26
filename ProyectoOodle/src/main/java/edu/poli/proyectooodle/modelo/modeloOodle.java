package edu.poli.proyectooodle.modelo;


public class modeloOodle {

    private boolean modoActual = true; // false = 1-12, true = 1-9

    public boolean getModoActual() {
        return modoActual;
    }

    public void toggleModo() {
        modoActual = !modoActual;
    }
    
}