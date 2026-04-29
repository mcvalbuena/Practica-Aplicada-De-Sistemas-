package edu.poli.proyectooodle.Tests;

import edu.poli.proyectooodle.Services.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConexionBD {
    public static void main(String[] args) {
        // 🔹 1. Probar conexión
        try {
            Connection conn = ConexionBD.getInstancia().getConexion();

            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexión exitosa a la base de datos");
            } else {
                System.out.println("❌ No se pudo conectar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 2. Consultar los primeros 10 usuarios
        try {
            Connection conn = ConexionBD.getInstancia().getConexion();

            String sql = "SELECT * FROM Users LIMIT 10";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n📋 Primeros usuarios:");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int score = rs.getInt("score");

                System.out.println(
                        "ID: " + id +
                                " | User: " + username +
                                " | PasswordHash: " + password +
                                " | Score: " + score
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
