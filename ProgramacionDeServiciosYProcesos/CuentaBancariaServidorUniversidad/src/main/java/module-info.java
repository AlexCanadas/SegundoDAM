module org.example.cuentabancariaservidoruniversidad {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cuentabancariaservidoruniversidad to javafx.fxml;
    exports org.example.cuentabancariaservidoruniversidad;
}