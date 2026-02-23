import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public App() {
        super("Ejercicio 2 - Nombres");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 180);
        setLocationRelativeTo(null);

        String[] nombres = {"Fernando", "Lucia", "Carlos", "Marta", "Ana"};
        JComboBox<String> combo = new JComboBox<>(nombres);

        JTextField txtSeleccion = new JTextField(20);
        txtSeleccion.setEditable(false);
        txtSeleccion.setText(nombres[0]);

        combo.addActionListener(e -> txtSeleccion.setText((String) combo.getSelectedItem()));

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.gridx = 0; c.gridy = 0; p.add(new JLabel("Elige un nombre:"), c);
        c.gridx = 1; p.add(combo, c);

        c.gridx = 0; c.gridy = 1; p.add(new JLabel("Seleccionado:"), c);
        c.gridx = 1; p.add(txtSeleccion, c);

        setContentPane(p);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
