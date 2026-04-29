package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.GestorEscenas;
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

    // ── Estado ───────────────────────────────────────────────────────────────────
    private boolean minuteroActivo = false;

    /** Instancia compartida del modelo (Singleton). */
    private final Juego model = Juego.getInstancia();

    // ── Inicialización ────────────────────────────────────────────────────────────

    @FXML
    public void initialize() {
        actualizarVista();
    }

    // ── Navegación ────────────────────────────────────────────────────────────────

    @FXML
    protected void onNuevaPartida() throws IOException {
        GestorEscenas.irA("NuevaPartida.fxml");
    }

    // ── Minutero ──────────────────────────────────────────────────────────────────

    @FXML
    protected void onToggleMinutero() {
        minuteroActivo = !minuteroActivo;
        if (minuteroActivo) {
            btnMinutero.setStyle("-fx-background-color: #030213; -fx-background-radius: 20; " +
                    "-fx-pref-width: 44; -fx-pref-height: 24; -fx-cursor: hand;");
        } else {
            btnMinutero.setStyle("-fx-background-color: #cbced4; -fx-background-radius: 20; " +
                    "-fx-pref-width: 44; -fx-pref-height: 24; -fx-cursor: hand;");
        }
    }

    // ── Verificación de filas (pantalla de inicio) ────────────────────────────────

    @FXML
    protected void onVerificarFila1() {
        procesarFilaHome(
                new Button[]{celda1_1, celda1_2, celda1_3, celda1_4},
                resultado1
        );
    }

    @FXML
    protected void onVerificarFila2() {
        procesarFilaHome(
                new Button[]{celda2_1, celda2_2, celda2_3, celda2_4},
                resultado2
        );
    }

    @FXML
    protected void onVerificarFila3() {
        procesarFilaHome(
                new Button[]{celda3_1, celda3_2, celda3_3, celda3_4},
                resultado3
        );
    }

    /**
     * Lógica compartida para verificar cualquier fila de esta vista.
     * Recoge los valores, los envía al modelo y colorea las celdas.
     */
    private void procesarFilaHome(Button[] celdas, Button btnResultado) {
        if (model.getNumeroObjetivo() == null || model.isPartidaFinalizada()) return;

        List<Integer> valores = new ArrayList<>();
        for (Button celda : celdas) {
            String texto = celda.getText().trim();
            if (texto.isEmpty()) return;                       // fila incompleta
            try {
                valores.add(Integer.parseInt(texto));
            } catch (NumberFormatException e) {
                return;
            }
        }

        Intento intento = model.registrarIntento(valores);
        if (intento == null) {
            // Aritmética incorrecta: feedback visual en el botón resultado
            btnResultado.setStyle(estiloError());
            btnResultado.setText("✗");
            return;
        }




        if (model.isPartidaGanada()) {
            btnResultado.setStyle(estiloVerde());
            btnResultado.setText("✓");
        }
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
        actualizarVista();
    }

    // ── Actualización visual ──────────────────────────────────────────────────────

    private void actualizarVista() {
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

    private String estiloError() {
        return "-fx-background-color: #e74c3c; -fx-text-fill: #ffffff; " +
                "-fx-background-radius: 8; -fx-border-radius: 8; " +
                "-fx-pref-width: 60; -fx-pref-height: 60; -fx-font-weight: bold;";
    }

    private String estiloVerde() {
        return "-fx-background-color: #6aaa64; -fx-text-fill: #ffffff; " +
                "-fx-background-radius: 8; -fx-pref-width: 60; -fx-pref-height: 60; " +
                "-fx-font-weight: bold;";
    }
}