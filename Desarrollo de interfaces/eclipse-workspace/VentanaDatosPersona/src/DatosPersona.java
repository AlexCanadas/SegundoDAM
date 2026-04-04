import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DatosPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtContrasena;
	private JTextField txtBuscar;

	private JButton btnInsertar;
	private JButton btnBorrar;
	private JButton btnModificar;
	private JButton btnLimpiar;
	private JButton btnBuscar;
	private JButton btnMostrarTodos;
	private JButton btnSalir;

	private DefaultTableModel modelo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatosPersona frame = new DatosPersona();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DatosPersona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// PANEL SUPERIOR
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(5, 3, 5, 5));

		panelSuperior.add(new JLabel("DNI"));
		txtDni = new JTextField();
		panelSuperior.add(txtDni);
		btnInsertar = new JButton("INSERTAR");
		panelSuperior.add(btnInsertar);

		panelSuperior.add(new JLabel("NOMBRE"));
		txtNombre = new JTextField();
		panelSuperior.add(txtNombre);
		btnBorrar = new JButton("BORRAR");
		panelSuperior.add(btnBorrar);

		panelSuperior.add(new JLabel("APELLIDOS"));
		txtApellidos = new JTextField();
		panelSuperior.add(txtApellidos);
		btnModificar = new JButton("MODIFICAR");
		panelSuperior.add(btnModificar);

		panelSuperior.add(new JLabel("DIRECCION"));
		txtDireccion = new JTextField();
		panelSuperior.add(txtDireccion);
		btnLimpiar = new JButton("LIMPIAR");
		panelSuperior.add(btnLimpiar);

		panelSuperior.add(new JLabel("CONTRASEÑA"));
		txtContrasena = new JTextField();
		panelSuperior.add(txtContrasena);
		panelSuperior.add(new JLabel(""));

		// PANEL CENTRO
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));

		// BUSQUEDA
		JPanel panelBusqueda = new JPanel();
		panelCentro.add(panelBusqueda, BorderLayout.NORTH);
		panelBusqueda.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtBuscar = new JTextField();
		txtBuscar.setColumns(15);
		panelBusqueda.add(txtBuscar);

		btnBuscar = new JButton("BUSCAR");
		panelBusqueda.add(btnBuscar);

		btnMostrarTodos = new JButton("MOSTRAR TODOS");
		panelBusqueda.add(btnMostrarTodos);

		// TABLA
		JPanel panelTabla = new JPanel();
		panelCentro.add(panelTabla, BorderLayout.CENTER);
		panelTabla.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		// PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnSalir = new JButton("Salir");
		panelInferior.add(btnSalir);

		// MODELO DE TABLA
		modelo = new DefaultTableModel();
		modelo.addColumn("DNI");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("DIRECCION");
		modelo.addColumn("CONTRASEÑA");

		table.setModel(modelo);

		// EVENTOS

		// INSERTAR
		btnInsertar.addActionListener(e -> {
			String dni = txtDni.getText().trim();
			String nombre = txtNombre.getText().trim();
			String apellidos = txtApellidos.getText().trim();
			String direccion = txtDireccion.getText().trim();
			String contrasena = txtContrasena.getText().trim();

			if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty()
					|| direccion.isEmpty() || contrasena.isEmpty()) {

				JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenos");
				return;
			}

			// comprobar DNI repetido
			for (int i = 0; i < modelo.getRowCount(); i++) {
				if (modelo.getValueAt(i, 0).toString().equals(dni)) {
					JOptionPane.showMessageDialog(null, "El DNI no puede repetirse");
					return;
				}
			}

			modelo.addRow(new Object[] { dni, nombre, apellidos, direccion, contrasena });
			JOptionPane.showMessageDialog(null, "Insertado correctamente");
			limpiarCampos();
		});

		// BORRAR
		btnBorrar.addActionListener(e -> {
			int fila = table.getSelectedRow();

			if (fila == -1) {
				JOptionPane.showMessageDialog(null, "Selecciona una fila");
			} else {
				modelo.removeRow(fila);
			}
		});

		// LIMPIAR
		btnLimpiar.addActionListener(e -> limpiarCampos());

		// SALIR
		btnSalir.addActionListener(e -> System.exit(0));
	}

	// METODO LIMPIAR
	private void limpiarCampos() {
		txtDni.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDireccion.setText("");
		txtContrasena.setText("");
	}
}