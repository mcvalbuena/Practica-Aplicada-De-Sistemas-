package edu.poli.proyectooodle.modelo;


public class Usuario {

    private int id;
    private String nombre;
    private String passwordHash;


    public Usuario(int id, String nombre, String passwordHash) {
        this.id = id;
        this.nombre = nombre;
        this.passwordHash = passwordHash;
    }


    public int getId(){ return id; }
    public void setId(int newId){ id = newId; }
    public String getNombre() { return nombre; }
    public void getNombre(String newNombre) { nombre = newNombre;}
    public String getPasswordHash() {return passwordHash;}
    public void setPasswordHash(String newPasswordHash) {passwordHash = newPasswordHash;}
}
