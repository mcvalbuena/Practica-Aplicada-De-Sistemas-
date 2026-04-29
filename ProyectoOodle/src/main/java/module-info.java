module edu.poli.proyectooodle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.dotenv;
    opens edu.poli.proyectooodle.controlador to javafx.fxml;
    exports edu.poli.proyectooodle;
    exports edu.poli.proyectooodle.modelo;
}