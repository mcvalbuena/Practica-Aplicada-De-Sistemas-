package edu.poli.proyectooodle.modelo;


public class Usuario {

    private int id;
    private String nombre;
    private String passwordHash;
    private int score;

    //testeo
    public Usuario(int id, String nombre, String passwordHash, int score) {
        this.id = id;
        this.nombre = nombre;
        this.passwordHash = passwordHash;
        this.score = score;
    }

    public Usuario(String nombre, String passwordHash) {
        this.nombre = nombre;
        this.passwordHash = passwordHash;
        this.score = 0;
    }


    public int getId(){ return id; }
    public void setId(int newId){ id = newId; }
    public String getNombre() { return nombre; }
    public void setNombre(String newNombre) { nombre = newNombre;}
    public String getPasswordHash() {return passwordHash;}
    public void setPasswordHash(String newPasswordHash) {passwordHash = newPasswordHash;}
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
