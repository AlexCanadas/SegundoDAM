import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteLoteria {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 7000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println(in.readLine());

            System.out.print("Tu numero para hoy: ");
            String num = sc.nextLine().trim();

            out.println(num);

            String resp = in.readLine();
            System.out.println("Respuesta servidor: " + resp);

        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}
