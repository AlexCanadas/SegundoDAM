package org.example.leerjson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // Leer el JSON desde la URL
            URL url = new URL("https://dummyjson.com/products");
            // Abrir una conexión y leer el contenido
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            // Guardar el JSON en un StringBuilder
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }

            br.close();

            // Convertir el texto a JSON
            JSONObject json = new JSONObject(contenido.toString());
            // Sacar el array products
            JSONArray productos = json.getJSONArray("products");

            // Recorrer el array y mostrar el nombre y precio de cada producto
            for (int i = 0; i < productos.length(); i++) {
                JSONObject producto = productos.getJSONObject(i);

                String nombre = producto.getString("title");
                double precio = producto.getDouble("price");

                System.out.println("Nombre: " + nombre + " - Precio: " + precio);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}