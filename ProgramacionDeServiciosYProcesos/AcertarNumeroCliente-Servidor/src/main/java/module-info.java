module org.example.acertarnumeroclienteservidor {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.acertarnumeroclienteservidor to javafx.fxml;
    exports org.example.acertarnumeroclienteservidor;
}