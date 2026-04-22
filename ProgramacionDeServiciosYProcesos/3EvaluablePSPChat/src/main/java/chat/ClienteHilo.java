package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteHilo extends Thread {

    private Socket socketCliente;
    private ServidorChat servidor;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String nombreUsuario;

    public ClienteHilo(Socket socketCliente, ServidorChat servidor) {
        this.socketCliente = socketCliente;
        this.servidor = servidor;

        try {
            entrada = new DataInputStream(socketCliente.getInputStream());
            salida = new DataOutputStream(socketCliente.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error al crear streams: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            nombreUsuario = entrada.readUTF();
            servidor.agregarCliente(nombreUsuario, this);

            String mensaje;

            while (true) {
                mensaje = entrada.readUTF();
                procesarMensaje(mensaje);
            }

        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + nombreUsuario);
        } finally {
            if (nombreUsuario != null) {
                servidor.eliminarCliente(nombreUsuario);
            }

            try {
                socketCliente.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar socket: " + e.getMessage());
            }
        }
    }

    private void procesarMensaje(String mensaje) {
        try {
            if (mensaje.startsWith("/solicitar_chat")) {
                String[] partes = mensaje.split(" ");
                String destinatario = partes[1];
                servidor.solicitarChat(nombreUsuario, destinatario);

            } else if (mensaje.startsWith("/chat_aceptado")) {
                String[] partes = mensaje.split(" ");
                String solicitante = partes[1];
                servidor.aceptarChat(nombreUsuario, solicitante);

            } else if (mensaje.startsWith("/chat_rechazado")) {
                String[] partes = mensaje.split(" ");
                String solicitante = partes[1];
                servidor.rechazarChat(nombreUsuario, solicitante);

            } else if (mensaje.startsWith("/mensaje")) {
                String[] partes = mensaje.split(" ", 3);
                String destinatario = partes[1];
                String contenido = partes[2];
                servidor.enviarMensajePrivado(nombreUsuario, destinatario, contenido);
            }
        } catch (Exception e) {
            System.out.println("Error procesando mensaje: " + e.getMessage());
        }
    }

    public void enviarMensaje(String mensaje) {
        try {
            salida.writeUTF(mensaje);
            salida.flush();
        } catch (IOException e) {
            System.out.println("Error enviando mensaje: " + e.getMessage());
        }
    }
}