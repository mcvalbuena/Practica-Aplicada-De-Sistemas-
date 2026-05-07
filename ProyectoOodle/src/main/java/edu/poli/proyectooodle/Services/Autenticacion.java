package edu.poli.proyectooodle.Services;

import edu.poli.proyectooodle.modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class Autenticacion {
    UserDAO _userDAO = new UserDAO();

    public boolean ValidarLogeo(String Nombre, String Password) {
        Usuario user = _userDAO.getByUsername(Nombre);
        if (user != null && BCrypt.checkpw(Password, user.getPasswordHash())) {
            return true;
        }
        return false;
    }

    public void registrar(String nombre, String password) {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        Usuario user = new Usuario(nombre, hash);
        _userDAO.create(user);
    }
}