package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.modelo.Usuario;
import edu.poli.proyectooodle.vista.GestorEscenas;
import edu.poli.proyectooodle.modelo.Intento;
import edu.poli.proyectooodle.modelo.Juego;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorOodle {

    // ── Celdas de juego ─────────────────────────────────────────────────────────
    @FXML private Button celda1_1, celda1_2, celda1_3, celda1_4, resultado1;
    @FXML private Button celda2_1, celda2_2, celda2_3, celda2_4, resultado2;
    @FXML private Button celda3_1, celda3_2, celda3_3, celda3_4, resultado3;

    // ── Controles de UI ──────────────────────────────────────────────────────────
    @FXML private Button btnRango9, btnRango12, btnReanudar, btnMinutero;
    @FXML private Button btnNuevaPartida;
    @FXML private Label  lblRangoRegla;

    /** Instancia compartida del modelo (Singleton). */
    private Juego model;

    // ── Inicialización ────────────────────────────────────────────────────────────

    @FXML
    public void initialize() {

        // 👇 SIEMPRE hay preview
        model = new Juego(new Usuario("preview", ""));

        model.iniciarJuego(9, model.jugador);

        actualizarVista();
    }

    // ── Navegación ────────────────────────────────────────────────────────────────

    @FXML
    protected void onNuevaPartida() throws IOException {
        GestorEscenas.irA("LoginRegistro.fxml");
    }

    // ── Verificación de filas (pantalla de inicio) ────────────────────────────────

    @FXML
    protected void onVerificarFila1() {

    }

    @FXML
    protected void onVerificarFila2() {

    }

    @FXML
    protected void onVerificarFila3() {

    }

    // ── Reanudar ─────────────────────────────────────────────────────────────────

    @FXML
    protected void onReanudar() {
        // Reanuda visualmente la partida (quitar pausa si se implementa minutero)
        actualizarVista();
    }

    // ── Cambio de modo ────────────────────────────────────────────────────────────

    @FXML
    protected void onCambiarModo() {
        model.toggleModo();
        int rango = model.getModoActual() ? 9 : 12;
        model.iniciarJuego(rango, model.jugador);
        actualizarVista();
    }

    // ── Actualización visual ──────────────────────────────────────────────────────

    private void actualizarVista() {
        if (model == null) return;

        if (model.getModoActual()) {
            btnRango9.setStyle(estiloBotonActivo());
            btnRango12.setStyle(estiloBotonInactivo());
        } else {
            btnRango9.setStyle(estiloBotonInactivo());
            btnRango12.setStyle(estiloBotonActivo());
        }
        lblRangoRegla.setText(model.getModoActual()
                ? "Utilice los números del 1 al 9 solo una vez."
                : "Utilice los números del 1 al 12 solo una vez.");
    }

    // ── Estilos reutilizables ─────────────────────────────────────────────────────

    private String estiloBotonActivo() {
        return "-fx-background-color: #5b4fcf; -fx-text-fill: #ffffff; " +
                "-fx-font-size: 13px; -fx-font-weight: bold; " +
                "-fx-background-radius: 20; -fx-padding: 7 18 7 18; -fx-cursor: hand;";
    }

    private String estiloBotonInactivo() {
        return "-fx-background-color: #ffffff; -fx-text-fill: #030213; " +
                "-fx-font-size: 13px; -fx-border-color: #cbced4; -fx-border-width: 1.5; " +
                "-fx-border-radius: 20; -fx-background-radius: 20; " +
                "-fx-padding: 7 18 7 18; -fx-cursor: hand;";
    }
}