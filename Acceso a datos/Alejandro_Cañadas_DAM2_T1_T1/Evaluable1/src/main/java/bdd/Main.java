package bdd;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        NorthwindController metodos = new NorthwindController();

        //metodos.insertarEmpleados(connection);
        //metodos.mostrarEmpleados(connection);

        // Insertar productos desde la API:
        // metodos.agregarProductos(connection);

        // metodos.mostrarProductos(connection);

         metodos.agregarPedidos(connection);

        // metodos.insertarProductosFav(connection);
    }
}
