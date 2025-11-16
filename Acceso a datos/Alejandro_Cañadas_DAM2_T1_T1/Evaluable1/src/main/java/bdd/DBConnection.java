package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            createConnection ();
        }
        return connection;
    }
    private void createConnection () {
        String url = "jdbc:mysql://127.0.0.1/northwind";
        try{
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Conexion realizada con exito");
        } catch (SQLException e) {
            System.out.println("Error en la conexion al servidor");
        }
    }
}
