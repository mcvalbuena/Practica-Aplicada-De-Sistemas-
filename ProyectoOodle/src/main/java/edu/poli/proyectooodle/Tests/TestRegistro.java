package edu.poli.proyectooodle.Tests;

import edu.poli.proyectooodle.Services.UserDAO;
import edu.poli.proyectooodle.modelo.Autenticacion;
import edu.poli.proyectooodle.modelo.Usuario;

public class TestRegistro {
    static Autenticacion _registro = new Autenticacion();
    static  UserDAO _UserDAO = new UserDAO();
    public static void main(String[] args) {

        metodoLogin("dani","oscargay");
       // metodoregistrar("dani","oscargay");

    }



    public static void metodoregistrar (String nombre, String password){
        _registro.registrar(nombre, password);
        Usuario user = _UserDAO.getByUsername(nombre);
        if (user == null) {
            System.out.println("Usuario No existe");
        }

        System.out.println("ID: " + user.getId());
        System.out.println("nombre: " + user.getNombre());
        System.out.println("contraseña: " + user.getPasswordHash());
        System.out.println("puntaje: " + user.getScore());
    }

    public static void metodoLogin (String nombre, String password){

        if (_registro.ValidarLogeo(nombre,password)){
            System.out.println("Usuario " + nombre + " existe y la contraseña es la insertada");
            return;
        }
        System.out.println("Usuario " + nombre + " No existe o la contraseña es incorrecta");
    }

}
