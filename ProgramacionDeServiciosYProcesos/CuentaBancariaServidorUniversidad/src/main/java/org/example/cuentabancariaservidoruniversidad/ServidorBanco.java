package org.example.cuentabancariaservidoruniversidad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ServidorBanco {

    public static void main(String[] args) {

        int puerto = 6000; // Puerto en el que el servidor escuchará
        Cuenta cuenta = new Cuenta();

        try (ServerSocket servidor = new ServerSocket(puerto)) {

            System.out.println("Servidor banco escuchando en puerto " + puerto);

            while (true) {

                Socket cliente = servidor.accept(); // Espera a que un cliente se conecte
                System.out.println("Cliente conectado");

                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream()) // Lee los comandos del cliente
                );

                PrintWriter salida = new PrintWriter(
                        cliente.getOutputStream(), true // Envía respuestas al cliente
                );

                String comando = entrada.readLine(); // Lee el comando enviado por el cliente

                if (comando.startsWith("INGRESAR")) {
                    // Divide el comando y obtiene la cantidad (segunda parte del string). ["INGRESAR", "100"]
                    int cantidad = Integer.parseInt(comando.split(" ")[1]);
                    cuenta.ingresar(cantidad);
                    salida.println("OK");

                } else if (comando.startsWith("RETIRAR")) {
                    // Divide el comando y obtiene la cantidad a retirar (segunda parte del string). ["RETIRAR", "50"]
                    int cantidad = Integer.parseInt(comando.split(" ")[1]);
                    cuenta.retirar(cantidad);
                    salida.println("Transacción procesada");

                } else if (comando.equals("SALDO")) {
                    salida.println(cuenta.getSaldo());
                }

                cliente.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}