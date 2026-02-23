import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    // credenciales estáticas (como pide el enunciado)
    private static final String USER_OK = "admin";
    private static final String PASS_OK = "1234";

    public App() {
        super("Acceso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 220);
        setLocationRelativeTo(null);

        JTextField txtUser = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnSalir = new JButton("Salir");

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Usuario:"), c);
        c.gridx = 1;
        form.add(txtUser, c);

        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("Contraseña:"), c);
        c.gridx = 1;
        form.add(txtPass, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.add(btnEntrar);
        botones.add(btnSalir);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(botones, BorderLayout.SOUTH);

        btnSalir.addActionListener(e -> System.exit(0));

        btnEntrar.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = new String(txtPass.getPassword()).trim();

            if (u.equals(USER_OK) && p.equals(PASS_OK)) {
                JOptionPane.showMessageDialog(this, "Bienvenido a la aplicación");
            } else {
                JOptionPane.showMessageDialog(this, "Datos incorrectos");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
