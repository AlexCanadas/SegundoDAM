import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBanco {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println(in.readLine());

            while (true) {
                System.out.println("\n1) Ingresar  2) Retirar  3) Ver saldo  0) Salir");
                System.out.print("Opcion: ");
                String op = sc.nextLine().trim();

                if (op.equals("0")) {
                    out.println("SALIR");
                    System.out.println(in.readLine());
                    break;
                } else if (op.equals("1")) {
                    System.out.print("Cantidad a ingresar: ");
                    String cant = sc.nextLine().trim();
                    out.println("INGRESAR " + cant);
                    System.out.println(in.readLine());
                } else if (op.equals("2")) {
                    System.out.print("Cantidad a retirar: ");
                    String cant = sc.nextLine().trim();
                    out.println("RETIRAR " + cant);
                    System.out.println(in.readLine());
                } else if (op.equals("3")) {
                    out.println("SALDO");
                    System.out.println(in.readLine());
                } else {
                    System.out.println("Opcion no valida.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}
