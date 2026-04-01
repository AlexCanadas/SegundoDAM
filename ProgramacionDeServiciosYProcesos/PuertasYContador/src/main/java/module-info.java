module org.example.puertasycontador {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.puertasycontador to javafx.fxml;
    exports org.example.puertasycontador;
}