package edu.poli.proyectooodle.vista;

import edu.poli.proyectooodle.Tests.TemporizadorSesion;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private final TemporizadorSesion temporizador = new TemporizadorSesion();

    @Override
    public void start(Stage stage) throws IOException {
        GestorEscenas.setStage(stage);
        GestorEscenas.irA("Oodle.fxml");
        stage.setTitle("Oodle Game");
        stage.show();
        temporizador.iniciar();
    }

    @Override
    public void stop() throws Exception {
        temporizador.detener();
    }

    public static void main(String[] args) {
        launch();
    }
}