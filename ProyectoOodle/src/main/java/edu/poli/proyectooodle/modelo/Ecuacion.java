package edu.poli.proyectooodle.modelo;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


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
        int RangoMinimo = 1;


        Random random = new Random();
        int numero = ThreadLocalRandom.current().nextInt(1, rango+1);
        numeros.clear();
        for (int i = 0; i < 4 ; i++) {
            if (numeros.isEmpty()) {
                    numeros.add(ThreadLocalRandom.current().nextInt(1, rango + 1));
            }
            else {
                int num = 0;
                while (num == 0){
                    int x = ThreadLocalRandom.current().nextInt(1, rango+1);
                    if (numeros.contains(x)){
                    }
                    else {
                     num = x;
                    }
                }

                numeros.add(num);
            }
        }

        //debug
        for (Integer integer : numeros)
        {
            System.out.println("ECUACION CALCULO: valor indice:"+numeros.indexOf(integer)+" es = "+integer);
        }
        //fin debug

        resultado = calcular(numeros.get(0), numeros.get(1), numeros.get(2), numeros.get(3));

    }


    public int calcular(int a, int b, int c, int d) {
        return a + (b * c) - d;
    }

    public boolean verificarReglas(List<Integer> numbers)
    {
        Set<Integer> set = new HashSet<>(numbers);

        if (set.size() != numbers.size()) {
            //hay numeros repetidos
            return false;
        } else {

            //no hay numero repetidos
            return true;
        }
    }


    public List<Integer> getNumeros() { return numeros; }
    public int getResultado()         { return resultado; }
    public int getRango()             { return rango; }
}