package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteChat extends JFrame {

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    private String nombreUsuario;
    private String usuarioDestino;

    private DefaultListModel<String> modeloListaUsuarios;
    private JList<String> listaUsuarios;
    private JTextArea areaChat;
    private JTextField campoMensaje;
    private JButton botonEnviar;

    public ClienteChat() {
        nombreUsuario = JOptionPane.showInputDialog(this, "Introduce tu nombre:");

        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir un nombre");
            System.exit(0);
        }

        setTitle("Chat - " + nombreUsuario);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
        conectarAlServidor();

        setVisible(true);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        modeloListaUsuarios = new DefaultListModel<>();
        listaUsuarios = new JList<>(modeloListaUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(listaUsuarios);
        scrollUsuarios.setPreferredSize(new Dimension(150, 0));

        areaChat = new JTextArea();
        areaChat.setEditable(false);
        JScrollPane scrollChat = new JScrollPane(areaChat);

        campoMensaje = new JTextField();
        botonEnviar = new JButton("Enviar");
        botonEnviar.setEnabled(false);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(campoMensaje, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);

        add(scrollUsuarios, BorderLayout.WEST);
        add(scrollChat, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        listaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    usuarioDestino = listaUsuarios.getSelectedValue();

                    if (usuarioDestino != null) {
                        solicitarChat();
                    }
                }
            }
        });

        botonEnviar.addActionListener(e -> enviarMensaje());

        campoMensaje.addActionListener(e -> enviarMensaje());
    }

    private void conectarAlServidor() {
        try {
            socket = new Socket("localhost", 12345);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            salida.writeUTF(nombreUsuario);
            salida.flush();

            new Thread(this::escucharServidor).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor");
            System.exit(0);
        }
    }

    private void escucharServidor() {
        try {
            while (true) {
                String mensaje = entrada.readUTF();

                if (mensaje.startsWith("/usuarios")) {
                    actualizarListaUsuarios(mensaje);

                } else if (mensaje.startsWith("/chat_solicitud")) {
                    String[] partes = mensaje.split(" ");
                    String solicitante = partes[1];

                    int respuesta = JOptionPane.showConfirmDialog(
                            this,
                            solicitante + " quiere hablar contigo. ¿Aceptar?",
                            "Solicitud de chat",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (respuesta == JOptionPane.YES_OPTION) {
                        usuarioDestino = solicitante;
                        salida.writeUTF("/chat_aceptado " + solicitante);
                        salida.flush();
                        areaChat.append("Has aceptado hablar con " + solicitante + "\n");
                        botonEnviar.setEnabled(true);
                    } else {
                        salida.writeUTF("/chat_rechazado " + solicitante);
                        salida.flush();
                    }

                } else if (mensaje.startsWith("/chat_aceptado")) {
                    String[] partes = mensaje.split(" ");
                    usuarioDestino = partes[1];
                    areaChat.append("Ahora estás hablando con " + usuarioDestino + "\n");
                    botonEnviar.setEnabled(true);

                } else if (mensaje.startsWith("/chat_rechazado")) {
                    String[] partes = mensaje.split(" ");
                    String usuario = partes[1];
                    areaChat.append(usuario + " rechazó tu solicitud de chat\n");

                } else if (mensaje.startsWith("/mensaje")) {
                    String[] partes = mensaje.split(" ", 3);
                    String remitente = partes[1];
                    String texto = partes[2];
                    areaChat.append(remitente + ": " + texto + "\n");
                }
            }

        } catch (IOException e) {
            areaChat.append("Desconectado del servidor\n");
        }
    }

    private void actualizarListaUsuarios(String mensaje) {
        modeloListaUsuarios.clear();

        String[] partes = mensaje.split(",");

        for (int i = 1; i < partes.length; i++) {
            if (!partes[i].equals(nombreUsuario)) {
                modeloListaUsuarios.addElement(partes[i]);
            }
        }
    }

    private void solicitarChat() {
        try {
            salida.writeUTF("/solicitar_chat " + usuarioDestino);
            salida.flush();
            areaChat.append("Solicitud enviada a " + usuarioDestino + "\n");
        } catch (IOException e) {
            areaChat.append("Error al solicitar chat\n");
        }
    }

    private void enviarMensaje() {
        String texto = campoMensaje.getText().trim();

        if (texto.isEmpty() || usuarioDestino == null) {
            return;
        }

        try {
            salida.writeUTF("/mensaje " + usuarioDestino + " " + texto);
            salida.flush();
            areaChat.append("Yo: " + texto + "\n");
            campoMensaje.setText("");
        } catch (IOException e) {
            areaChat.append("Error al enviar mensaje\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteChat::new);
    }
}