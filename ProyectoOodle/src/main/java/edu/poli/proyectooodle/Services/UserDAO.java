package edu.poli.proyectooodle.Services;

import edu.poli.proyectooodle.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ✅ CREATE
    public String create(Usuario user) {
        String sql = "INSERT INTO Users (username, password, score) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getNombre());
            ps.setString(2, user.getPasswordHash());
            ps.setInt(3, user.getScore());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

            return "✔ Usuario creado correctamente";

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // ✅ READ ONE
    public Usuario getByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection con = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("score")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ✅ UPDATE SCORE
    public boolean updateScore(String username, int newScore) {
        String sql = "UPDATE Users SET score = ? WHERE username = ?";

        try (Connection con = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newScore);
            ps.setString(2, username);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ READ ALL
    public List<Usuario> getAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection con = ConexionBD.getInstancia().getConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("score")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
