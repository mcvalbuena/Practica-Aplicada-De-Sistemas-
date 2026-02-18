module com.example.proyectooodle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectooodle to javafx.fxml;
    exports com.example.proyectooodle;
}