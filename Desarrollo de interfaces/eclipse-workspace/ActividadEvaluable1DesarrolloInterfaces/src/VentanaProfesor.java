import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.GestionBD;

public class VentanaProfesor extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBoxModulos;
	private JTable tableAlumnos;
	private DefaultTableModel tableModel;
	private int idProfesor;

	public VentanaProfesor(int idProfesor) {
		this.idProfesor = idProfesor;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaInicio.class.getResource("/imagenes/UE ICONO PEQUEÑO.jpg")));
		setTitle("Profesor - Asignar notas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Selecciona un módulo:");
		contentPane.add(lblTitulo, BorderLayout.NORTH);

		comboBoxModulos = new JComboBox<>();
		contentPane.add(comboBoxModulos, BorderLayout.NORTH);

		// Tabla de alumnos y notas
		tableModel = new DefaultTableModel(new Object[] { "ID Alumno", "Nombre", "Nota" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2; // solo la columna "Nota" es editable
			}
		};
		tableAlumnos = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(tableAlumnos);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Botones
		JPanel panelBotones = new JPanel();
		JButton btnVolver = new JButton("Volver");
		JButton btnGuardar = new JButton("Guardar notas");
		panelBotones.add(btnVolver);
		panelBotones.add(btnGuardar);
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		// Accion Volver
		btnVolver.addActionListener(e -> {
			VentanaInicio vi = new VentanaInicio();
			vi.setVisible(true);
			dispose();
		});

		// Accion Guardar notas
		btnGuardar.addActionListener(e -> guardarNotas());
		// Cargar modulos del profesor
		cargarModulos();
		// Al cambiar de modulo, cargar alumnos
		comboBoxModulos.addActionListener(e -> {
			if (comboBoxModulos.getSelectedIndex() != -1) {
				cargarAlumnos();
			}
		});
	}

	private void cargarModulos() {
		try {
			var rs = GestionBD.obtenerModulosProfesor(idProfesor);

			comboBoxModulos.removeAllItems();
			while (rs.next()) {
				comboBoxModulos.addItem(rs.getInt("id") + " - " + rs.getString("nombre_modulo"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar módulos");
		}

	}

	private void cargarAlumnos() {
		if (comboBoxModulos.getSelectedItem() == null)
			return;

		String item = comboBoxModulos.getSelectedItem().toString(); // obtiene el texto del módulo seleccionado
		int idModulo = Integer.parseInt(item.split(" - ")[0]); // separa y se queda con el id solamente para la bdd

		tableModel.setRowCount(0); // limpiar tabla antes de mostrar nuevos

		try {
			var rs = GestionBD.obtenerAlumnosModulo(idModulo);

			tableModel.setRowCount(0); // se limpia de nuevo la tabla por si acaso

			while (rs.next()) {
				tableModel
						.addRow(new Object[] { rs.getInt("id"), rs.getString("nombre_usuario"), rs.getDouble("nota") });
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar alumnos");
		}

	}

	private void guardarNotas() {
		if (comboBoxModulos.getSelectedItem() == null)
			return;
		String item = comboBoxModulos.getSelectedItem().toString();
		int idModulo = Integer.parseInt(item.split(" - ")[0]);

		try {
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				int idAlumno = (int) tableModel.getValueAt(i, 0);
				double nota = Double.parseDouble(tableModel.getValueAt(i, 2).toString());

				GestionBD.guardarNota(idAlumno, idModulo, nota);
			}

			JOptionPane.showMessageDialog(this, "Notas guardadas correctamente!");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar las notas");
		}

	}
}
