package Lotería;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class cliente {
	private String direccionIP;
	private int puertoServidor;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private JFrame frame;
	private JTextField textField;
	private JButton sendButton;
	private JTextArea responseArea;

	// constructor para conectarse
	public cliente(String direccionIP, int puertoServidor) {
		this.direccionIP = direccionIP;
		this.puertoServidor = puertoServidor;
	}

	private void enviarNumero() {
		try {
			int numero = Integer.parseInt(textField.getText()); // pasamos respuesta a int
			System.out.println("Escrito: " + numero);
			if (numero < 10000 || numero > 99999) {
				responseArea.setText("Número inválido. Debe tener 5 dígitos.");
				return;
			}

			socket = new Socket();
			InetSocketAddress direccionServidor = new InetSocketAddress(direccionIP, puertoServidor);
			socket.connect(direccionServidor); // conexión TCP
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());

			output.writeInt(numero); // se envía número
			output.flush(); // fuerza para que pase al momento
			int numeroPremiado = input.readInt();
			responseArea
					.setText("Respuesta del servidor: " + (numeroPremiado == numero ? "¡Premiado!" : "No premiado"));

			this.stop(); // cerramos todo
		} catch (NumberFormatException ex) {
			responseArea.setText("Por favor, ingrese un número válido.");
		} catch (IOException ex) {
			responseArea.setText("Error de conexión con el servidor.");
		}
	}

	private void start() throws IOException {
		frame = new JFrame("Cliente de Lotería");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("Ingrese su número de lotería (5 dígitos):");
		label.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		label.setBounds(20, 20, 300, 25);
		frame.getContentPane().add(label);

		textField = new JTextField();
		textField.setBounds(30, 55, 150, 25);
		frame.getContentPane().add(textField);

		sendButton = new JButton("Enviar");
		sendButton.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		sendButton.setBounds(220, 55, 100, 25);
		frame.getContentPane().add(sendButton);

		responseArea = new JTextArea();
		responseArea.setBounds(20, 100, 350, 100);
		responseArea.setEditable(false);
		frame.getContentPane().add(responseArea);

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviarNumero();
			}
		});

		frame.setVisible(true);
	}

	private void stop() throws IOException {
		input.close();
		output.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			cliente cliente = new cliente("localhost", 34282);
			cliente.start();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}