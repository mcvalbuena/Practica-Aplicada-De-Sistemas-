package edu.poli.proyectooodle.modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {


    private static Juego instancia;

    public Usuario jugador;
    private Ecuacion numeroObjetivo;
    private List<Integer> solucion;
    private List<Intento> intentosMaximos;
    private boolean partidaGanada;
    private boolean partidaFinalizada;
    private Puntaje score;

    private boolean modoRango9  = true;
    private static final int MAX_INTENTOS = 6;


    public Juego(Usuario player) {
        intentosMaximos = new ArrayList<>();
        jugador = player;
        instancia = this;
    }

    public static Juego getInstancia() {
        return instancia;
    }

    public void iniciarJuego(int rango, Usuario usuario) {
        jugador = usuario;
        modoRango9      = (rango == 9);
        numeroObjetivo  = new Ecuacion(rango);
        solucion        = new ArrayList<>(numeroObjetivo.getNumeros());
        intentosMaximos = new ArrayList<>();
        partidaGanada      = false;
        partidaFinalizada  = false;
        score              = new Puntaje();
        score.setIntentosUsados(0);
        score.calcularPuntos();
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

        // ❌ validar reglas primero
        if (!numeroObjetivo.verificarReglas(valores)) {

            return null; // inválido
        }

        Intento intento = new Intento(valores);
        intentosMaximos.add(intento);

        // ACTUALIZAR SCORE EN CADA INTENTO
        score.setIntentosUsados(intentosMaximos.size());
        score.setGano(false); // aún no ha ganado
        score.calcularPuntos();

        // ✅ ahora sí evaluar resultado
        if (intento.correcto(numeroObjetivo)) {
            partidaGanada = true;
            juegoTerminado();
        } else if (intentosMaximos.size() >= MAX_INTENTOS) {
            juegoTerminado();
        }

        return intento; // ⚠️ SIEMPRE retorna intento válido
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