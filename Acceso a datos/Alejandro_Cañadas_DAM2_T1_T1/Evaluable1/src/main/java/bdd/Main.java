package bdd;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = new DBConnection(); // Crear instancia
        Connection connection = dbConnection.getConnection(); // Obtener conexión

        Metodos metodos = new Metodos();
        //metodos.insertarEmpleados(connection);




    }
}