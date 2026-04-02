package org.example.cuentabancariaservidoruniversidad;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBanco {

//    El servidor de la universidad tiene un archivo (simula una cuenta bancaria) con la cantidad de
//    dinero ahorrada por los alumnos para irse de viaje de fin de curso. En esa cuenta todos pueden
//    ingresar y retirar dinero. Se pide una gestión de la cuenta para que controle la sincronización
//    de ambas tareas por parte de todos los alumnos/as. Cuando se inicie en el cliente se podrá hacer
//    varias tareas:
//    a.	Ingresar sobre la cuenta.
//    b.	Retirar de la cuenta.
//    c.	Ver el saldo actual.

    public static void main(String[] args) {

        String host = "localhost";
        int puerto = 6000; // Puerto en el que el servidor está escuchando

        Scanner sc = new Scanner(System.in);

        while (true) {

            try (Socket socket = new Socket(host, puerto)) { // Conecta al servidor

                BufferedReader entrada = new BufferedReader( // Lee las respuestas del servidor
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter salida = new PrintWriter( // Envía comandos al servidor
                        socket.getOutputStream(), true
                );

                System.out.println("\n1. Ingresar");
                System.out.println("2. Retirar");
                System.out.println("3. Ver saldo");
                System.out.println("4. Salir");
                System.out.print("Elige una opción: ");

                int opcion = sc.nextInt();

                if (opcion == 1) {
                    System.out.print("Cantidad a ingresar: ");
                    int cantidad = sc.nextInt();
                    salida.println("INGRESAR " + cantidad);
                    System.out.println("Respuesta: " + entrada.readLine());

                } else if (opcion == 2) {
                    System.out.print("Cantidad a retirar: ");
                    int cantidad = sc.nextInt();
                    salida.println("RETIRAR " + cantidad);
                    System.out.println("Respuesta: " + entrada.readLine());

                } else if (opcion == 3) {
                    salida.println("SALDO");
                    System.out.println("Saldo: " + entrada.readLine());

                } else if (opcion == 4) {
                    System.out.println("Saliendo...");
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}