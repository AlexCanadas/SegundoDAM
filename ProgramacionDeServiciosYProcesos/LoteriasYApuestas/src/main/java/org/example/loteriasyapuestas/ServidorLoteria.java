package org.example.loteriasyapuestas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLoteria {
    public static void main(String[] args) {
        int puerto = 5000; // Puerto en el que el servidor escuchará las conexiones

        try (ServerSocket servidor = new ServerSocket(puerto)) { // Crea un servidor que escucha en el puerto especificado
            System.out.println("Servidor de Lotería iniciado en el puerto " + puerto);

            Socket cliente = servidor.accept(); // Espera a que un cliente se conecte
            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            BufferedReader entradaRed = new BufferedReader( // Para leer los datos que llegan del cliente
                    new InputStreamReader(cliente.getInputStream()) // Lee los datos que llegan del cliente
            );

            PrintWriter salidaRed = new PrintWriter(cliente.getOutputStream(), true); // Para enviar datos al cliente

            String numeroCliente = entradaRed.readLine(); // Lee el número enviado por el cliente

            BufferedReader lectorFichero = new BufferedReader(new FileReader("loteria.txt"));
            String numeroPremiado = lectorFichero.readLine(); // Lee el número premiado del fichero
            lectorFichero.close();

            if (numeroCliente.equals(numeroPremiado)) {
                salidaRed.println("PREMIADO");
            } else {
                salidaRed.println("NO PREMIADO");
            }

            cliente.close(); // Cierra la conexión con el cliente


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
