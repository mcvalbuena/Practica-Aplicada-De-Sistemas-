package edu.poli.proyectooodle.Services;

import edu.poli.proyectooodle.modelo.Usuario;

public class Autenticacion {
    UserDAO _userDAO = new UserDAO();

    public boolean ValidarLogeo(String Nombre, String Password){
        Usuario user = _userDAO.getByUsername(Nombre);
        if (user != null  && Password.equals(user.getPasswordHash())){
            return true;
        }
        return false;
    }

    public void registrar (String nombre, String password){
        Usuario user = new Usuario(nombre,password);
        _userDAO.create(user);
    }

}
