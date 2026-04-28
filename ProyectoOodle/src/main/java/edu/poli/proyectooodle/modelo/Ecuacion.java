package edu.poli.proyectooodle.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Ecuacion {

    private List<Integer> numeros;
    private int resultado;
    private int rango;

    public Ecuacion(int rango) {
        this.rango = rango;
        this.numeros = new ArrayList<>();
        generarAleatorio();
    }


    private void generarAleatorio() {
        Random rand = new Random();

        do {
            numeros.clear();
            for (int i = 0; i < 4; i++) {
                numeros.add(rand.nextInt(rango) + 1);
            }
            resultado = calcular(numeros.get(0), numeros.get(1),
                    numeros.get(2), numeros.get(3));
        } while (resultado <= 0);
    }


    public int calcular(int a, int b, int c, int d) {
        return a + (b * c) - d;
    }


    public boolean validarEcuacion(int objetivo) {
        return this.resultado == objetivo;
    }


    public List<Integer> getNumeros() { return numeros; }
    public int getResultado()         { return resultado; }
    public int getRango()             { return rango; }
}