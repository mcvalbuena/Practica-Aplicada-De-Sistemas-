package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.GestorEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class ControladorNuevaPartida {

    // ── Celdas fila 1 ──
    @FXML private Button f1c1, f1c2, f1c3, f1c4, f1resultado;
    // ── Celdas fila 2 ──
    @FXML private Button f2c1, f2c2, f2c3, f2c4, f2resultado;
    // ── Celdas fila 3 ──
    @FXML private Button f3c1, f3c2, f3c3, f3c4, f3resultado;
    // ── Celdas fila 4 ──
    @FXML private Button f4c1, f4c2, f4c3, f4c4, f4resultado;
    // ── Celdas fila 5 ──
    @FXML private Button f5c1, f5c2, f5c3, f5c4, f5resultado;
    // ── Celdas fila 6 ──
    @FXML private Button f6c1, f6c2, f6c3, f6c4, f6resultado;

    // Celda actualmente seleccionada
    private Button celdaSeleccionada = null;

    @FXML
    public void initialize() {
        // inicialización futura (cargar ecuación, etc.)
    }

    // ── Navbar ──────────────────────────────

    @FXML
    protected void onHome() throws IOException {
        GestorEscenas.irA("hello-view.fxml");
    }

    @FXML
    protected void onRestart() {
        limpiarTablero();
    }

    // ── Selección de celda ──────────────────

    @FXML
    protected void onCeldaClick(ActionEvent e) {
        // Quitar selección visual de la celda anterior
        if (celdaSeleccionada != null) {
            celdaSeleccionada.setStyle(celdaSeleccionada.getStyle()
                    .replace("-fx-border-color: #5b4fcf;", "-fx-border-color: #d0d0d0;"));
        }

        // Marcar la nueva celda seleccionada
        celdaSeleccionada = (Button) e.getSource();
        celdaSeleccionada.setStyle(celdaSeleccionada.getStyle()
                .replace("-fx-border-color: #d0d0d0;", "-fx-border-color: #5b4fcf;"));
    }

    // ── Teclado numérico ────────────────────

    @FXML
    protected void onNumero(ActionEvent e) {
        if (celdaSeleccionada == null) return;

        Button btnNumero = (Button) e.getSource();
        celdaSeleccionada.setText(btnNumero.getText());
    }

    @FXML
    protected void onDel() {
        if (celdaSeleccionada == null) return;
        celdaSeleccionada.setText("");
    }

    @FXML
    protected void onCheck() {
        // lógica de verificación futura
    }

    // ── Verificar fila ──────────────────────

    @FXML
    protected void onVerificarFila(ActionEvent e) {
        Button btnResultado = (Button) e.getSource();
        // lógica de verificación futura
        // ejemplo: cambiar color según resultado
    }

    // ── Utilidades ──────────────────────────

    private void limpiarTablero() {
        Button[][] celdas = {
                {f1c1, f1c2, f1c3, f1c4},
                {f2c1, f2c2, f2c3, f2c4},
                {f3c1, f3c2, f3c3, f3c4},
                {f4c1, f4c2, f4c3, f4c4},
                {f5c1, f5c2, f5c3, f5c4},
                {f6c1, f6c2, f6c3, f6c4}
        };

        for (Button[] fila : celdas) {
            for (Button celda : fila) {
                celda.setText("");
                celda.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d0d0d0; " +
                        "-fx-border-width: 1.5; -fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-pref-width: 60; -fx-pref-height: 60; -fx-font-size: 18px; " +
                        "-fx-font-weight: bold; -fx-cursor: hand;");
            }
        }
        celdaSeleccionada = null;
    }
}