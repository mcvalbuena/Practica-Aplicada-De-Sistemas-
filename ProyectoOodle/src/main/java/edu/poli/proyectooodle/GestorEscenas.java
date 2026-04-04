package edu.poli.proyectooodle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GestorEscenas{

    private static Stage stage;

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void irA(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                GestorEscenas.class.getResource("/edu.poli.proyectooodle/vista/" + fxml)
        );
        Scene scene = new Scene(loader.load(), 900, 700);
        stage.setScene(scene);
    }
}