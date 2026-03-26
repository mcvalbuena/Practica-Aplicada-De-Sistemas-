package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.modelo.modeloOodle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class ControladorOodle {

    @FXML private Label welcomeText;
    @FXML private Button btnCambiarModo;
    @FXML private Button celda1_1, celda1_2, celda1_3, celda1_4, resultado1;
    @FXML private Button celda2_1, celda2_2, celda2_3, celda2_4, resultado2;
    @FXML private Button celda3_1, celda3_2, celda3_3, celda3_4, resultado3;
    @FXML private Button btnRango9, btnRango12, btnReanudar;
    @FXML private Label lblRangoRegla;

    private final modeloOodle model = new modeloOodle();

    @FXML
    public void initialize() {
        actualizarVista();
    }
    @FXML private Button btnMinutero;
    private boolean minuteroActivo = false;

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
    protected void onHelloButtonClick() {
        actualizarVista();
    }

    @FXML
    protected void onCambiarModo() {
        model.toggleModo();
        actualizarVista();
    }

    private void actualizarVista() {
        welcomeText.setText(model.getTextoReglas());
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        btnCambiarModo.setText(model.getTextoCambiarModo());
    }
}