package Lotería;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class servidor_lotería {
	private Socket socket;
	private ServerSocket serverSocket;
	private DataInputStream input;
	private DataOutputStream output;
	private static int numeroLoteria;

	public servidor_lotería(int puerto) throws IOException {
		serverSocket = new ServerSocket();
		InetSocketAddress direccionRedServidor = new InetSocketAddress("localhost", puerto);
		serverSocket.bind(direccionRedServidor);
	}

	public int generarNumeroLoteria() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(89999);
	}

	public void start() throws IOException {
		numeroLoteria = generarNumeroLoteria();
		System.out.println("El número de la lotería es " + numeroLoteria + ".");
		System.out.println("El servidor de loterías y apuestas del Estado está a la espera de conexiones...");

		socket = serverSocket.accept();
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());

		System.out.println("Se ha establecido una conexión con el servidor.");
	}

	public void stop() throws IOException {
		System.out.println("Deteniendo conexiones realizadas con el servidor.");

		output.close();
		input.close();
		socket.close();
		serverSocket.close();

		System.out.println("Deteniendo el servidor de loterías y apuestas del Estado...");
	}

	public static void main(String[] args) {
		try {
			servidor_lotería servidor = new servidor_lotería(34282);
			servidor.start();

			System.out.println("Mensaje del cliente recibido: " + servidor.input.readInt());
			servidor.output.writeInt(numeroLoteria);
			servidor.output.flush();
			servidor.stop();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

}