package org.example.leerfichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // ruta del fichero
        String ruta = "ejercicio_uno.txt";

        try {
            // FileReader abre el fichero y BufferedReader lo lee línea a linea
            BufferedReader br = new BufferedReader(new FileReader(ruta));

            String linea;
            // readline() devuelve null cuando se llega al final del fichero
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // cerramos el BufferedReader
            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}