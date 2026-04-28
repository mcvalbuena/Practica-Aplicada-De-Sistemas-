package edu.poli.proyectooodle.modelo;


import java.time.LocalDate;


public class Puntaje {

    private int id;
    private Usuario usuario;
    private int intentosUsados;
    private boolean gano;
    private String fecha;
    private int puntos;

    public Puntaje(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = LocalDate.now().toString();
        this.puntos = 0;
    }

    public int calcularPuntos() {
        if (!gano) {
            puntos = 0;
            return 0;
        }
        puntos = Math.max(100, 1000 - (intentosUsados - 1) * 150);
        return puntos;
    }

    public int getPuntos() { return puntos; }



    public void setIntentosUsados(int intentosUsados) { this.intentosUsados = intentosUsados; }
    public void setGano(boolean gano)                 { this.gano = gano; }


    public int      getId()              { return id; }
    public Usuario  getUsuario()         { return usuario; }
    public String   getFecha()           { return fecha; }
    public boolean  isGano()             { return gano; }
    public int      getIntentosUsados()  { return intentosUsados; }
}
