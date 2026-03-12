package com.example.proyectooodle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnCambiarModo;

    private boolean modoActual = false;

    @FXML
    protected void onHelloButtonClick() {
        mostrarReglas();
    }

    @FXML
    protected void onCambiarModo() {

        modoActual = !modoActual;

        if (modoActual) {
            btnCambiarModo.setText("Cambiar a modo 1-9");
        } else {
            btnCambiarModo.setText("Cambiar a modo 1-12");
        }

        mostrarReglas();
    }

    private void mostrarReglas() {
        String numeros = modoActual ? "1 al 9" : "1 al 12";

        welcomeText.setText("REGLAS" +
                "\nUtilice los números del " + numeros + " sólo una vez\n" +
                "Haga click en el cuadro de respuestas rojo para verificar la solución\n" +
                "Verde = ¡El número está en la posición correcta!" +
                "\nAmarillo = Número de posición incorrecta" +
                "\nGris = Número no presente en la ecuación" +
                "\nTienes un máximo de seis intentos");

        welcomeText.setTextAlignment(TextAlignment.CENTER);
    }
}