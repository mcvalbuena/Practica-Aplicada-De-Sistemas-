module edu.poli.proyectooodle {
    requires javafx.controls;
    requires javafx.fxml;
    opens edu.poli.proyectooodle.controlador to javafx.fxml;
    exports edu.poli.proyectooodle;
}