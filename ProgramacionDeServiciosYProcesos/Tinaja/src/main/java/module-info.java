module org.example.tinaja {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tinaja to javafx.fxml;
    exports org.example.tinaja;
}