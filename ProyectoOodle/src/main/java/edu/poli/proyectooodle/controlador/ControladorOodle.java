package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.modelo.modeloOodle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorOodle {

    @FXML private Button celda1_1, celda1_2, celda1_3, celda1_4, resultado1;
    @FXML private Button celda2_1, celda2_2, celda2_3, celda2_4, resultado2;
    @FXML private Button celda3_1, celda3_2, celda3_3, celda3_4, resultado3;
    @FXML private Button btnRango9, btnRango12, btnReanudar, btnMinutero;
    @FXML private Label lblRangoRegla;

    private final modeloOodle model = new modeloOodle();
    private boolean minuteroActivo = false;

    @FXML
    public void initialize() {
        actualizarVista();
    }

    @FXML
    protected void onToggleMinutero() {
        minuteroActivo = !minuteroActivo;
        if (minuteroActivo) {
            btnMinutero.setStyle("-fx-background-color: #030213; -fx-background-radius: 20; -fx-pref-width: 44; -fx-pref-height: 24; -fx-cursor: hand;");
        } else {
            btnMinutero.setStyle("-fx-background-color: #cbced4; -fx-background-radius: 20; -fx-pref-width: 44; -fx-pref-height: 24; -fx-cursor: hand;");
        }
    }

    @FXML protected void onVerificarFila1() { }
    @FXML protected void onVerificarFila2() { }
    @FXML protected void onVerificarFila3() { }
    @FXML protected void onReanudar() { }

    @FXML
    protected void onCambiarModo() {
        model.toggleModo();
        actualizarVista();
    }

    private void actualizarVista() {
        if (model.getModoActual()) {
            btnRango9.setStyle("-fx-background-color: #5b4fcf; -fx-text-fill: #ffffff; -fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 7 18 7 18; -fx-cursor: hand;");
            btnRango12.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #030213; -fx-font-size: 13px; -fx-border-color: #cbced4; -fx-border-width: 1.5; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 7 18 7 18; -fx-cursor: hand;");
        } else {
            btnRango9.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #030213; -fx-font-size: 13px; -fx-border-color: #cbced4; -fx-border-width: 1.5; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 7 18 7 18; -fx-cursor: hand;");
            btnRango12.setStyle("-fx-background-color: #5b4fcf; -fx-text-fill: #ffffff; -fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 7 18 7 18; -fx-cursor: hand;");
        }

        lblRangoRegla.setText(model.getModoActual()
                ? "Utilice los números del 1 al 9 solo una vez."
                : "Utilice los números del 1 al 12 solo una vez.");
    }
}