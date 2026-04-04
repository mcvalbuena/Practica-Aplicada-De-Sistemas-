package edu.poli.proyectooodle.modelo;


public class modeloOodle {

    private boolean modoActual = true;

    public boolean getModoActual() {
        return modoActual;
    }

    public void toggleModo() {
        modoActual = !modoActual;
    }
    
}