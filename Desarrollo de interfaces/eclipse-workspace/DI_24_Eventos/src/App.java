import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {
    private final JComboBox<String> combo = new JComboBox<>();
    private final JTextField txtNombreElegido = new JTextField(18);

    private final JTextField txtSoloLetras = new JTextField(22);
    private final JTextField txtSoloDigitos = new JTextField(22);

    private final JLabel lblRadio = new JLabel("Estas sobre la opcion");

    public App() {
        super("Ventana de Eventos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(820, 420);
        setLocationRelativeTo(null);

        // --- Arriba: 3 columnas ---
        JPanel top = new JPanel(new GridLayout(1, 3, 25, 0));
        top.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));

        // Columna 1: botón + radios
        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));
        col1.add(new JLabel("Pulsa el boton"));
        col1.add(Box.createVerticalStrut(8));
        JButton btnPulsame = new JButton("Pulsame");
        btnPulsame.setAlignmentX(Component.LEFT_ALIGNMENT);
        col1.add(btnPulsame);

        col1.add(Box.createVerticalStrut(28));
        JRadioButton r1 = new JRadioButton("Opcion 1");
        JRadioButton r2 = new JRadioButton("Opcion 2");
        JRadioButton r3 = new JRadioButton("Opcion 3");
        ButtonGroup g = new ButtonGroup();
        g.add(r1); g.add(r2); g.add(r3);
        col1.add(r1); col1.add(r2); col1.add(r3);

        // Columna 2: combo + nombre elegido (no editable) + label
        JPanel col2 = new JPanel();
        col2.setLayout(new BoxLayout(col2, BoxLayout.Y_AXIS));
        col2.add(new JLabel("Elige una opcion:"));
        col2.add(Box.createVerticalStrut(8));

        combo.addItem("Fernando");
        combo.addItem("Lucia");
        combo.addItem("Carlos");
        combo.addItem("Marta");
        combo.addItem("Ana");

        col2.add(combo);
        col2.add(Box.createVerticalStrut(28));

        col2.add(new JLabel("Nombre Elegido"));
        txtNombreElegido.setEditable(false);
        col2.add(txtNombreElegido);

        col2.add(Box.createVerticalStrut(45));
        col2.add(lblRadio);

        // Columna 3: dos campos con validación + botón añadir
        JPanel col3 = new JPanel();
        col3.setLayout(new BoxLayout(col3, BoxLayout.Y_AXIS));
        col3.add(new JLabel("Escribe el nombre de una persona sin digitos"));
        col3.add(Box.createVerticalStrut(8));
        col3.add(txtSoloLetras);
        col3.add(Box.createVerticalStrut(12));

        JButton btnAnadir = new JButton("Añadir");
        btnAnadir.setAlignmentX(Component.LEFT_ALIGNMENT);
        col3.add(btnAnadir);

        col3.add(Box.createVerticalStrut(40));
        col3.add(new JLabel("Solo se pueden escribir digitos"));
        col3.add(Box.createVerticalStrut(8));
        col3.add(txtSoloDigitos);

        top.add(col1);
        top.add(col2);
        top.add(col3);

        setContentPane(top);

        // --- Eventos básicos ---
        combo.addActionListener(e -> txtNombreElegido.setText((String) combo.getSelectedItem()));
        txtNombreElegido.setText((String) combo.getSelectedItem());

        btnPulsame.addActionListener(e -> JOptionPane.showMessageDialog(this, "¡Botón pulsado!"));

        // Radios: cambia label
        r1.addActionListener(e -> lblRadio.setText("Estas sobre la opcion 1"));
        r2.addActionListener(e -> lblRadio.setText("Estas sobre la opcion 2"));
        r3.addActionListener(e -> lblRadio.setText("Estas sobre la opcion 3"));

        // Validación simple: solo letras y espacios
        txtSoloLetras.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!Character.isLetter(ch) && ch != ' ' && ch != '\b') e.consume();
            }
        });

        // Validación simple: solo dígitos
        txtSoloDigitos.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!Character.isDigit(ch) && ch != '\b') e.consume();
            }
        });

        btnAnadir.addActionListener(e -> {
            String texto = txtSoloLetras.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe un nombre primero.");
                return;
            }
            combo.addItem(texto);
            JOptionPane.showMessageDialog(this, "Añadido a la lista: " + texto);
            txtSoloLetras.setText("");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
