package edu.poli.proyectooodle.modelo;

import java.util.ArrayList;
import java.util.List;


public class Intento {

    private List<Integer>      valores;
    private List<EstadoCasilla> estados;

    public Intento(List<Integer> valores) {
        this.valores = new ArrayList<>(valores);
        this.estados = new ArrayList<>();
    }


    public List<EstadoCasilla> compararConSolucion(Ecuacion ec) {
        estados.clear();
        List<Integer> solucion = ec.getNumeros();

        boolean[]      usadoSolucion = new boolean[solucion.size()];
        boolean[]      asignado      = new boolean[valores.size()];
        EstadoCasilla[] resultado    = new EstadoCasilla[valores.size()];

        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i).equals(solucion.get(i))) {
                resultado[i]       = EstadoCasilla.VERDE;
                usadoSolucion[i]   = true;
                asignado[i]        = true;
            }
        }

        for (int i = 0; i < valores.size(); i++) {
            if (asignado[i]) continue;

            boolean encontrado = false;
            for (int j = 0; j < solucion.size(); j++) {
                if (!usadoSolucion[j] && valores.get(i).equals(solucion.get(j))) {
                    resultado[i]     = EstadoCasilla.AMARILLO;
                    usadoSolucion[j] = true;
                    encontrado       = true;
                    break;
                }
            }
            if (!encontrado) resultado[i] = EstadoCasilla.GRIS;
        }

        for (EstadoCasilla e : resultado) estados.add(e);
        return new ArrayList<>(estados);
    }


    public boolean correcto() {
        if (estados.isEmpty()) return false;
        return estados.stream().allMatch(e -> e == EstadoCasilla.VERDE);
    }


    public List<Integer>       getValores() { return valores; }
    public List<EstadoCasilla> getEstados() { return estados; }
}