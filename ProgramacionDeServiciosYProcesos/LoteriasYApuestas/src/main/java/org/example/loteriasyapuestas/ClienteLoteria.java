package org.example.loteriasyapuestas;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteLoteria {

    public static void main(String[] args) {

        String host = "localhost"; // Ordenador local
        int puerto = 5000; // Puerto en el que el servidor está escuchando

        try (Socket socket = new Socket(host, puerto)) { // Establece la conexión con el servidor

            BufferedReader entrada = new BufferedReader( // Para leer la respuesta del servidor
                    new InputStreamReader(socket.getInputStream()) // Lee los datos que llegan del servidor
            );

            PrintWriter salida = new PrintWriter( // Para enviar datos al servidor
                    socket.getOutputStream(), true // Escribe los datos que se envían al servidor
            );

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce tu número: ");
            String numero = sc.nextLine();

            salida.println(numero); // Envía el número al servidor

            String respuesta = entrada.readLine();
            System.out.println("Resultado: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}