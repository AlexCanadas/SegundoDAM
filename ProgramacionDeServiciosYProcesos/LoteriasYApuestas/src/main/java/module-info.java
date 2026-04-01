module org.example.loteriasyapuestas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.loteriasyapuestas to javafx.fxml;
    exports org.example.loteriasyapuestas;
}