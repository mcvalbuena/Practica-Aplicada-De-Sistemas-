package edu.poli.proyectooodle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class AplicacionOodle extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GestorEscenas.setStage(stage);
        GestorEscenas.irA("hello-view.fxml");
        stage.setTitle("Oodle Game");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}