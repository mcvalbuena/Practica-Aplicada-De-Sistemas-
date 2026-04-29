package edu.poli.proyectooodle.modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {


    private static Juego instancia;

    public static Juego getInstancia() {
        if (instancia == null) instancia = new Juego();
        return instancia;
    }


    private Ecuacion      numeroObjetivo;
    private List<Integer> solucion;
    private List<Intento> intentosMaximos;
    private boolean       partidaGanada;
    private boolean       partidaFinalizada;
    private Puntaje       score;


    private boolean modoRango9  = true;
    private static final int MAX_INTENTOS = 6;


    private Juego() {
        intentosMaximos = new ArrayList<>();
    }



    public void iniciarJuego(int rango) {
        modoRango9      = (rango == 9);
        numeroObjetivo  = new Ecuacion(rango);
        solucion        = new ArrayList<>(numeroObjetivo.getNumeros());
        intentosMaximos = new ArrayList<>();
        partidaGanada      = false;
        partidaFinalizada  = false;
        score              = null;
    }


    public boolean verificarResultado() {
        if (intentosMaximos.isEmpty()) return false;
        Intento ultimo = ultimoIntento();
        List<Integer> vals = ultimo.getValores();

        //debug
        for (Integer integer : vals)
        {
            System.out.println("valor indice:"+vals.indexOf(integer)+" es = "+integer);
        }
        //fin debug

        System.out.println();
        int calculado = numeroObjetivo.calcular(
                vals.get(0), vals.get(1), vals.get(2), vals.get(3));
        return calculado == numeroObjetivo.getResultado();
    }


    public boolean verificarPosiciones() {
        if (intentosMaximos.isEmpty()) return false;
        Intento ultimo = ultimoIntento();
        ultimo.compararConSolucion(numeroObjetivo);
        return ultimo.correcto();
    }


    public void juegoTerminado() {
        partidaFinalizada = true;
        if (score != null) {
            score.setIntentosUsados(intentosMaximos.size());
            score.setGano(partidaGanada);
            score.calcularPuntos();
        }
    }


    public Intento registrarIntento(List<Integer> valores) {
        if (partidaFinalizada || intentosMaximos.size() >= MAX_INTENTOS) return null;

        Intento intento = new Intento(valores);
        intentosMaximos.add(intento);


        if (!verificarResultado()) {
            intentosMaximos.remove(intento);
            return null;
        }


        intento.compararConSolucion(numeroObjetivo);

        if (intento.correcto()) {
            partidaGanada = true;
            juegoTerminado();
        } else if (intentosMaximos.size() >= MAX_INTENTOS) {
            juegoTerminado();
        }

        return intento;
    }




    public void toggleModo() {
        modoRango9 = !modoRango9;
    }


    public boolean getModoActual() { return modoRango9; }


    public int getRangoActual() { return modoRango9 ? 9 : 12; }


    public Ecuacion      getNumeroObjetivo()       { return numeroObjetivo; }
    public List<Integer> getSolucion()             { return solucion; }
    public List<Intento> getIntentos()             { return intentosMaximos; }
    public boolean       isPartidaGanada()         { return partidaGanada; }
    public boolean       isPartidaFinalizada()     { return partidaFinalizada; }
    public int           getNumeroIntentosUsados() { return intentosMaximos.size(); }
    public Puntaje       getScore()                { return score; }
    public void          setScore(Puntaje score)   { this.score = score; }


    private Intento ultimoIntento() {
        return intentosMaximos.get(intentosMaximos.size() - 1);
    }
}