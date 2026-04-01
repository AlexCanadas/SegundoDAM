module org.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.example.test to javafx.fxml;
    exports org.example.test;
}