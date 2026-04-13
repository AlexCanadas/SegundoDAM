module org.example.leerfichero {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.leerfichero to javafx.fxml;
    exports org.example.leerfichero;
}