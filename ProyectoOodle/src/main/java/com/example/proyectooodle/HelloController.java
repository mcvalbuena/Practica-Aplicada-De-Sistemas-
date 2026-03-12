package com.example.proyectooodle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("REGLAS" +
                " \n Utilice los números del 1 al 9 sólo una vez, o del 1 al 12 \n" +
                "Haga click en el cuadro de respuestas rojo para verificar la solución \n" +
                "Verde = ¡El número está en la posición correcta!"+
                "\nAmarillo = Número de posición incorrecta"+
                 "\nGris = Número no presente e la ecuación"+
                "\nTienes un máximo de seis intentos");

        welcomeText.setTextAlignment(TextAlignment.CENTER);


    }
}