module org.example.baloncesto {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.baloncesto to javafx.fxml;
    exports org.example.baloncesto;
}