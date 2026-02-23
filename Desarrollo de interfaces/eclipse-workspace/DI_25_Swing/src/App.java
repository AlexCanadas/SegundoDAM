import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class App extends JFrame {
    private final JTextField txtDni = new JTextField(12);
    private final JTextField txtNombre = new JTextField(12);
    private final JTextField txtApellidos = new JTextField(12);
    private final JTextField txtDireccion = new JTextField(12);
    private final JPasswordField txtContrasena = new JPasswordField(12);

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"DNI", "NOMBRE", "APELLIDOS", "DIRECCION", "CONTRASEÑA"}, 0
    );
    private final JTable tabla = new JTable(model);

    public App() {
        super("DI 2025 - Swing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        addFila(form, c, 0, "DNI", txtDni);
        addFila(form, c, 1, "NOMBRE", txtNombre);
        addFila(form, c, 2, "APELLIDOS", txtApellidos);
        addFila(form, c, 3, "DIRECCION", txtDireccion);
        addFila(form, c, 4, "CONTRASEÑA", txtContrasena);

        // Botones derecha
        JPanel botones = new JPanel();
        botones.setLayout(new BoxLayout(botones, BoxLayout.Y_AXIS));

        JButton btnInsertar = new JButton("INSERTAR");
        JButton btnBorrar = new JButton("BORRAR");
        JButton btnModificar = new JButton("MODIFICAR");
        JButton btnLimpiar = new JButton("LIMPIAR");

        Dimension d = new Dimension(140, 35);
        for (JButton b : new JButton[]{btnInsertar, btnBorrar, btnModificar, btnLimpiar}) {
            b.setMaximumSize(d);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            botones.add(b);
            botones.add(Box.createVerticalStrut(25));
        }

        // Barra medio: búsqueda (sin funcionalidad obligatoria)
        JPanel medio = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JTextField txtBuscar = new JTextField(10);
        JButton btnBuscar = new JButton("BUSCAR");
        JButton btnMostrar = new JButton("MOSTRAR TODOS");
        medio.add(txtBuscar);
        medio.add(btnBuscar);
        medio.add(btnMostrar);

        // Tabla
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnSalir = new JButton("Salir");

        // Layout general
        JPanel arriba = new JPanel(new BorderLayout());
        arriba.add(form, BorderLayout.CENTER);
        arriba.add(botones, BorderLayout.EAST);

        JPanel cont = new JPanel(new BorderLayout());
        cont.add(arriba, BorderLayout.NORTH);
        cont.add(medio, BorderLayout.CENTER);
        cont.add(scroll, BorderLayout.SOUTH);

        // Para que la tabla tenga espacio grande
        scroll.setPreferredSize(new Dimension(680, 320));

        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnSalir);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cont, BorderLayout.CENTER);
        getContentPane().add(abajo, BorderLayout.SOUTH);

        // --- Lógica pedida ---
        btnInsertar.addActionListener(e -> insertar());
        btnBorrar.addActionListener(e -> borrarSeleccion());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> System.exit(0));

        // Los demás botones no hacen nada (como pide el enunciado)
    }

    private void addFila(JPanel panel, GridBagConstraints c, int row, String label, JComponent field) {
        c.gridx = 0; c.gridy = row; c.weightx = 0;
        panel.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1;
        panel.add(field, c);
    }

    private void insertar() {
        String dni = txtDni.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String pass = new String(txtContrasena.getPassword()).trim();

        if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
            return;
        }

        if (dniExiste(dni)) {
            JOptionPane.showMessageDialog(this, "Ese DNI ya existe en la tabla.");
            return;
        }

        model.addRow(new Object[]{dni, nombre, apellidos, direccion, pass});
        limpiarCampos();
    }

    private boolean dniExiste(String dni) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (dni.equalsIgnoreCase(String.valueOf(model.getValueAt(i, 0)))) return true;
        }
        return false;
    }

    private void borrarSeleccion() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para borrar.");
            return;
        }
        model.removeRow(fila);
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtContrasena.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
