package org.example.acertarnumeroclienteservidor;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJuego {

    public static void main(String[] args) {

        String host = "localhost";
        int puerto = 7000; // Puerto del servidor

        try (Socket socket = new Socket(host, puerto)) {

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()) // Lee las respuestas del servidor
            );

            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true // Envía los intentos al servidor
            );

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce un número entre 0 y 20: ");
                int numero = sc.nextInt();

                salida.println(numero); // Envía el intento al servidor

                String respuesta = entrada.readLine(); // Lee la respuesta del servidor
                System.out.println("Respuesta del servidor: " + respuesta);

                if (respuesta.equals("ACIERTO")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}