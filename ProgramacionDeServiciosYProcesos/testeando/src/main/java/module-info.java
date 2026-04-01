module org.example.testeando {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.testeando to javafx.fxml;
    exports org.example.testeando;
}