package bdd;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Metodos {

    public void insertarEmpleados(Connection connection) throws SQLException {
        // Consulta SQL parametrizada
        String sql = "INSERT INTO Empleados (id, nombre, apellidos, correo) VALUES (?, ?, ?, ?)";

        // Utilizamos PreparedStatement para insertar los datos de forma parametrizada
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            // Insertar primer empleado
            statement.setInt(1, 1);  // Asignamos el ID
            statement.setString(2, "Verona"); // Asignamos el nombre
            statement.setString(3, "Florio"); // Asignamos los apellidos
            statement.setString(4, "veronaflorio@hotmail.com"); // Asignamos el correo
            statement.executeUpdate(); // Ejecutar la inserción
            System.out.println("Empleado 1 insertado con éxito.");

            // Insertar segundo empleado
            statement.setInt(1, 2);
            statement.setString(2, "María");
            statement.setString(3, "Cordovez");
            statement.setString(4, "mariacordovez@hotmail.com");
            statement.executeUpdate();
            System.out.println("Empleado 2 insertado con éxito.");

            // Insertar tercer empleado
            statement.setInt(1, 3);
            statement.setString(2, "Biaggio");
            statement.setString(3, "Florio");
            statement.setString(4, "floriobiaggio@hotmail.com");
            statement.executeUpdate();
            System.out.println("Empleado 3 insertado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al insertar empleados: " + e.getMessage());
        }
    }

    public void mostrarEmpleados(Connection connection) {
        String sql = "SELECT * FROM Empleados"; // Consulta para obtener todos los empleados
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("Empleados:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                String correo = resultSet.getString("correo");

                System.out.println("ID: " + id + ", Nombre: " + nombre + " " + apellidos + ", Correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar empleados: " + e.getMessage());
        }
    }

    public void agregarProductos(Connection connection) {
        ProductService productService = new ProductService();
        try {
            // Obtener productos del JSON
            List<Productos> products = productService.fetchProducts();

            // Preparar consulta SQL para insertar los productos
            String query = "INSERT INTO Productos (nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Iterar sobre los productos y agregar al lote de inserciones
            for (Productos product : products) {
                statement.setString(1, product.getTitulo());
                statement.setString(2, product.getDescripcion());
                statement.setInt(3, product.getCantidad());
                statement.setDouble(4, product.getPrecio());
                statement.addBatch(); // Agregar al lote
            }

            // Ejecutar el lote de inserciones
            statement.executeBatch();
            System.out.println("Productos insertados correctamente en la base de datos.");

        } catch (IOException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al insertar los productos en la base de datos: " + e.getMessage());
        }
    }

    public void mostrarProductos(Connection connection) {
        String sql = "SELECT * FROM Productos"; // Consulta para obtener todos los productos
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("Productos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion +
                        ", Cantidad: " + cantidad + ", Precio: " + precio);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
    }

    public void mostrarProductosPorPrecio(Connection connection) {
        String sql = "SELECT * FROM Productos WHERE precio < 600"; // Consulta para obtener productos con precio inferior a 600€

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("Productos con precio inferior a 600€:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion +
                        ", Cantidad: " + cantidad + ", Precio: " + precio + "€");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
    }

    public void mostrarPedidos(Connection connection) {
        String sql = "SELECT * FROM Pedidos"; // Consulta para obtener todos los pedidos
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("Pedidos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProducto = resultSet.getInt("id_producto");
                String descripcion = resultSet.getString("descripcion");
                double precioTotal = resultSet.getDouble("precio_total");

                System.out.println("ID: " + id + ", Producto ID: " + idProducto + ", Descripción: " + descripcion +
                        ", Precio Total: " + precioTotal);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar pedidos: " + e.getMessage());
        }
    }

    public void agregarPedidos(Connection connection) {
        // Suponiendo que estos son los productos que se están pidiendo
        // Un pedido de producto con ID y cantidad
        int[] idProductos = {1, 2, 3};  // IDs de productos seleccionados
        int[] cantidades = {2, 1, 3};   // Cantidades de cada producto

        // Consulta para obtener el precio de cada producto y luego hacer el pedido
        String selectPriceSql = "SELECT precio FROM Productos WHERE id = ?";
        String insertOrderSql = "INSERT INTO Pedidos (id_producto, descripcion, precio_total) VALUES (?, ?, ?)";
        String updateProductSql = "UPDATE Productos SET cantidad = cantidad - ? WHERE id = ?";

        // Variables para acumular el total de artículos y el monto total
        int totalArticulos = 0;
        double montoTotal = 0;

        try {
            // Iterar sobre los productos seleccionados para los pedidos
            for (int i = 0; i < idProductos.length; i++) {
                int productId = idProductos[i];
                int quantity = cantidades[i];

                // Obtener el precio del producto
                PreparedStatement priceStatement = connection.prepareStatement(selectPriceSql);
                priceStatement.setInt(1, productId);
                ResultSet resultSet = priceStatement.executeQuery();

                if (resultSet.next()) {
                    double price = resultSet.getDouble("precio");

                    // Calcular el precio total para este pedido
                    double totalPrice = price * quantity;

                    // Insertar el pedido
                    PreparedStatement orderStatement = connection.prepareStatement(insertOrderSql);
                    orderStatement.setInt(1, productId);
                    orderStatement.setString(2, "Pedido de producto ID " + productId); // Descripción
                    orderStatement.setDouble(3, totalPrice);
                    orderStatement.executeUpdate();

                    System.out.println("Pedido de producto ID " + productId + " insertado con éxito. Precio total: " + totalPrice);

                    // Actualizar la cantidad del producto en la base de datos
                    PreparedStatement updateStatement = connection.prepareStatement(updateProductSql);
                    updateStatement.setInt(1, quantity);
                    updateStatement.setInt(2, productId);
                    updateStatement.executeUpdate();

                    System.out.println("Cantidad de producto ID " + productId + " actualizada con éxito.");

                    // Acumular la cantidad total de artículos y el monto total
                    totalArticulos += quantity;
                    montoTotal += totalPrice;
                } else {
                    System.out.println("Producto con ID " + productId + " no encontrado.");
                }
            }

            // Mostrar el total de artículos y el monto total al final de la operación
            System.out.println("Cantidad total de artículos: " + totalArticulos);
            System.out.println("Monto total de la operación: " + montoTotal);

        } catch (SQLException e) {
            System.out.println("Error al agregar los pedidos: " + e.getMessage());
        }
    }

    public void insertarProductosFav(Connection connection) {
        String selectSql = "SELECT id FROM Productos WHERE precio > 1000"; // Consulta para obtener productos con precio superior a 1000€
        String insertSql = "INSERT INTO Productos_Fav (id_producto) VALUES (?)"; // Consulta para insertar en la tabla Productos_Fav

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);

            // Crear un PreparedStatement para insertar los productos en Productos_Fav
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

            // Iterar sobre los productos seleccionados y agregarlos a la tabla Productos_Fav
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id");

                // Establecer el parámetro para la inserción
                preparedStatement.setInt(1, idProducto);
                preparedStatement.executeUpdate();
                System.out.println("Producto con ID " + idProducto + " insertado en Productos_Fav.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar productos en Productos_Fav: " + e.getMessage());
        }
    }
}




