package org.example.acertarnumeroclienteservidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorJuego {

    public static void main(String[] args) {

        int puerto = 7000; // Puerto para el servidor
        int numeroSecreto = new Random().nextInt(21); // 0 a 20

        // Guardar el número en juego.txt (crea o sobrescribe el archivo)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("juego.txt"))) {
            bw.write(String.valueOf(numeroSecreto));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Servidor escuchando en puerto " + puerto);

        try (ServerSocket servidor = new ServerSocket(puerto)) {

            Socket cliente = servidor.accept(); // Espera a que un cliente se conecte
            System.out.println("Cliente conectado");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream()) // Lee los intentos del cliente
            );

            PrintWriter salida = new PrintWriter(
                    cliente.getOutputStream(), true // Envía respuestas al cliente
            );

            while (true) {
                int intento = Integer.parseInt(entrada.readLine()); // Lee el intento del cliente

                if (intento == numeroSecreto) {
                    salida.println("ACIERTO");
                    break;
                } else if (intento < numeroSecreto) {
                    salida.println("MAYOR");
                } else {
                    salida.println("MENOR");
                }
            }

            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}