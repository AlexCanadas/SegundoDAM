module org.example.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxtest to javafx.fxml;
    exports org.example.javafxtest;
}