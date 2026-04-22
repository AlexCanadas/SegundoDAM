package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServidorChat {

    private ServerSocket serverSocket;
    private Map<String, ClienteHilo> clientes;

    public ServidorChat(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        clientes = new HashMap<>();
        System.out.println("Servidor iniciado en el puerto " + puerto);
    }

    public void iniciar() {
        while (true) {
            try {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");
                ClienteHilo clienteHilo = new ClienteHilo(socketCliente, this);
                clienteHilo.start();
            } catch (IOException e) {
                System.out.println("Error al aceptar cliente: " + e.getMessage());
            }
        }
    }

    public synchronized void agregarCliente(String nombre, ClienteHilo cliente) {
        clientes.put(nombre, cliente);
        System.out.println("Usuario conectado: " + nombre);
        enviarListaUsuarios();
    }

    public synchronized void eliminarCliente(String nombre) {
        clientes.remove(nombre);
        System.out.println("Usuario desconectado: " + nombre);
        enviarListaUsuarios();
    }

    public synchronized void enviarListaUsuarios() {
        StringBuilder lista = new StringBuilder("/usuarios");
        for (String nombre : clientes.keySet()) {
            lista.append(",").append(nombre);
        }

        for (ClienteHilo cliente : clientes.values()) {
            cliente.enviarMensaje(lista.toString());
        }
    }

    public synchronized void solicitarChat(String solicitante, String destinatario) {
        ClienteHilo clienteDestino = clientes.get(destinatario);
        if (clienteDestino != null) {
            clienteDestino.enviarMensaje("/chat_solicitud " + solicitante);
        }
    }

    public synchronized void aceptarChat(String aceptador, String solicitante) {
        ClienteHilo clienteSolicitante = clientes.get(solicitante);
        ClienteHilo clienteAceptador = clientes.get(aceptador);

        if (clienteSolicitante != null) {
            clienteSolicitante.enviarMensaje("/chat_aceptado " + aceptador);
        }

        if (clienteAceptador != null) {
            clienteAceptador.enviarMensaje("/chat_aceptado " + solicitante);
        }
    }

    public synchronized void rechazarChat(String rechazador, String solicitante) {
        ClienteHilo clienteSolicitante = clientes.get(solicitante);

        if (clienteSolicitante != null) {
            clienteSolicitante.enviarMensaje("/chat_rechazado " + rechazador);
        }
    }

    public synchronized void enviarMensajePrivado(String remitente, String destinatario, String mensaje) {
        ClienteHilo clienteDestino = clientes.get(destinatario);

        if (clienteDestino != null) {
            clienteDestino.enviarMensaje("/mensaje " + remitente + " " + mensaje);
        }
    }

    public static void main(String[] args) {
        try {
            ServidorChat servidor = new ServidorChat(12345);
            servidor.iniciar();
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}