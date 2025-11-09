import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class VentanaCitaMedicaJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldTelefono;
	private JScrollPane scrollPane;
	private JTable tableCitas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCitaMedicaJFrame frame = new VentanaCitaMedicaJFrame();
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
	public VentanaCitaMedicaJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelFormulario = new JPanel();
		contentPane.add(panelFormulario, BorderLayout.NORTH);
		panelFormulario.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelFormulario.add(lblNombre);

		textFieldNombre = new JTextField();
		panelFormulario.add(textFieldNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelFormulario.add(lblApellido);

		textFieldApellido = new JTextField();
		panelFormulario.add(textFieldApellido);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelFormulario.add(lblTelefono);

		textFieldTelefono = new JTextField();
		panelFormulario.add(textFieldTelefono);

		JLabel lblCita = new JLabel("Fecha de la cita:");
		lblCita.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelFormulario.add(lblCita);

		// Creamos un subpanel para meter TextField + calendario
		JPanel panelFecha = new JPanel(new BorderLayout());

		// JDateChooser (ya incluye un textField + icono de calendario)
		JDateChooser elegirFecha = new JDateChooser();
		elegirFecha.setDateFormatString("dd/MM/yyyy");
		panelFecha.add(elegirFecha);

		// Añadimos el subpanel a la celda derecha de la fila
		panelFormulario.add(panelFecha);

		// Panel para meter la tabla
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tableCitas = new JTable();
		scrollPane.setViewportView(tableCitas); // Metemos la tabla en el panel
		String[] columnasTableCitas = { "Nombre", "Apellido", "Teléfono", "Fecha de Cita" };
		DefaultTableModel modelo = new DefaultTableModel(columnasTableCitas, 0); // 0 filas al empezar (hasta agregar
																					// con addRow()
		tableCitas.setModel(modelo);

		// Creamos panel para los botones
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		// Botón para agregar cita
		JButton btnAgregarCita = new JButton("Agregar Cita");
		panelBotones.add(btnAgregarCita);

		btnAgregarCita.addActionListener(e -> {
			// Validamos campos y limpiamos
			String nombre = textFieldNombre.getText().trim();
			String apellido = textFieldApellido.getText().trim();
			String telefono = textFieldTelefono.getText().trim();
			String fecha = null;

			if (elegirFecha.getDate() != null) {
				fecha = new java.text.SimpleDateFormat("dd/MM/yyyy").format(elegirFecha.getDate());
			}

			if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || fecha == null) {
				javax.swing.JOptionPane.showMessageDialog(this,
						"Por favor, complete todos los campos antes de agregar la cita.", "Campos incompletos",
						javax.swing.JOptionPane.WARNING_MESSAGE);
				return; // Salir del action listener
			}

			// Añadir fila a la tabla
			DefaultTableModel modeloTabla = (DefaultTableModel) tableCitas.getModel();
			modeloTabla.addRow(new Object[] { nombre, apellido, telefono, fecha });

			// Limpiar campos después de agregar
			textFieldNombre.setText("");
			textFieldApellido.setText("");
			textFieldTelefono.setText("");
			elegirFecha.setDate(null);
		});

		// Botón cancelar cita
		JButton btnCancelarCita = new JButton("Cancelar cita");
		panelBotones.add(btnCancelarCita);

		btnCancelarCita.addActionListener(e -> {
			int filaSeleccionada = tableCitas.getSelectedRow();
			if (filaSeleccionada >= 0) {
				DefaultTableModel modeloTabla = (DefaultTableModel) tableCitas.getModel();
				modeloTabla.removeRow(filaSeleccionada);
			} else {
				javax.swing.JOptionPane.showMessageDialog(this,
						"Seleccione una fila de la tabla para cancelar la cita.", "Aviso",
						javax.swing.JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}

}
