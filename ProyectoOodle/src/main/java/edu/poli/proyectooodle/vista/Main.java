package edu.poli.proyectooodle.vista;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GestorEscenas.setStage(stage);
        GestorEscenas.irA("Oodle.fxml");
        stage.setTitle("Oodle Game");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}