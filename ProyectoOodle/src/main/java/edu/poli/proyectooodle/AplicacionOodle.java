package edu.poli.proyectooodle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class AplicacionOodle extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                AplicacionOodle.class.getResource("/edu.poli.proyectooodle/vista/hello-view.fxml"));

        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setTitle("Oodle Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}