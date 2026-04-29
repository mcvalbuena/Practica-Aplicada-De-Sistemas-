package edu.poli.proyectooodle.modelo;



public class Puntaje {

    private int intentosUsados;
    private final int maximoIntentos = 6;
    private final int maximoPuntaje = 600;
    private boolean gano;
    private int puntos;

    public Puntaje() {
        this.puntos = maximoPuntaje;
    }

    public int calcularPuntos() {
        int puntosIntento = maximoPuntaje/maximoIntentos;
        if (intentosUsados == 1)
        {
            puntos = maximoPuntaje;
        }
        else
        {
            puntos = maximoPuntaje - (intentosUsados*puntosIntento);
        }

        if (puntos < 0) puntos = 0;
        return puntos;
    }

    public int getPuntos() { return puntos; }

    public void setIntentosUsados(int intentosUsados) { this.intentosUsados = intentosUsados; }
    public void setGano(boolean gano)                 { this.gano = gano; }

    public boolean  isGano()             { return gano; }
    public int      getIntentosUsados()  { return intentosUsados; }
}
