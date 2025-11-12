import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bdd.ConexionBD;

public class VentanaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JButton btEntrar;
	private JComboBox<String> comboBoxListaDesplegable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaInicio.class.getResource("/imagenes/UE ICONO PEQUEÑO.jpg")));
		setTitle("Ventana de inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LabelUsuario = new JLabel("USUARIO");
		LabelUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelUsuario.setBounds(22, 35, 106, 20);
		contentPane.add(LabelUsuario);

		JLabel LabelCargo = new JLabel("CARGO");
		LabelCargo.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelCargo.setBounds(22, 169, 124, 20);
		contentPane.add(LabelCargo);

		JLabel LabelContraseña_1 = new JLabel("CONTRASEÑA");
		LabelContraseña_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelContraseña_1.setBounds(22, 102, 124, 20);
		contentPane.add(LabelContraseña_1);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(153, 37, 132, 22);
		contentPane.add(textFieldUsuario);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(156, 104, 129, 22);
		contentPane.add(passwordField);

		// Desplegable para seleccionar el rol de Estudiante o Profesor
		comboBoxListaDesplegable = new JComboBox<>();
		comboBoxListaDesplegable
				.setModel(new DefaultComboBoxModel<>(new String[] { "Seleccione cargo", "Estudiante", "Profesor" }));
		comboBoxListaDesplegable.setToolTipText("Seleccione cargo");
		comboBoxListaDesplegable.setBounds(156, 171, 129, 21);
		contentPane.add(comboBoxListaDesplegable);

		JLabel LogoUE = new JLabel("");
		LogoUE.setIcon(new ImageIcon(VentanaInicio.class.getResource("/imagenes/descarga.png")));
		LogoUE.setBounds(306, 59, 300, 95);
		contentPane.add(LogoUE);

		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpiar.setBounds(168, 213, 94, 25);
		contentPane.add(btnLimpiar);

		// Evento para el boton "Limpiar"
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUsuario.setText("");
				passwordField.setText("");
				comboBoxListaDesplegable.setSelectedIndex(0); // Vuelve a "Seleccione cargo"

			}
		});

		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(444, 213, 88, 25);
		contentPane.add(btnSalir);

		// Evento para el boton "Salir"
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		btEntrar = new JButton("ENTRAR");
		btEntrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btEntrar.setBounds(346, 213, 88, 25);
		contentPane.add(btEntrar);

		// Evento para el boton "Entrar"
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textFieldUsuario.getText();
				String contraseña = new String(passwordField.getPassword());
				String cargo = comboBoxListaDesplegable.getSelectedItem().toString();

				if (cargo.equals("Seleccione cargo")) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un cargo.");
					return;
				}

				// Conectamos a la base de datos
				try (Connection conexion = ConexionBD.conectar()) {
					String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ? AND cargo = ?";
					java.sql.PreparedStatement ps = conexion.prepareStatement(sql);
					ps.setString(1, usuario);
					ps.setString(2, contraseña);
					ps.setString(3, cargo);

					// Se ejecuta la query revisando si existe ese usuario en la bdd
					java.sql.ResultSet rs = ps.executeQuery();

					// Vamos a la ventana secundaria correspondiente dependiendo del cargo
					if (rs.next()) {
						int idUsuario = rs.getInt("id"); // id del usuario en la base
						if (cargo.equals("Estudiante")) {
							VentanaAlumno va = new VentanaAlumno(idUsuario);
							va.setVisible(true);
						} else if (cargo.equals("Profesor")) {
							VentanaProfesor vp = new VentanaProfesor(idUsuario);
							vp.setVisible(true);
						}
						dispose(); // Cierra la ventana de inicio
					} else {
						JOptionPane.showMessageDialog(null, "Usuario, contraseña o cargo incorrectos.");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
				}
			}
		});

	}

}
