import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.ConexionBD;

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
		try (Connection conn = ConexionBD.conectar()) {
			String sql = "SELECT m.id, m.nombre_modulo FROM modulos m "
					+ "JOIN profesor_modulo pm ON m.id = pm.id_modulo " + "WHERE pm.id_profesor = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idProfesor);
			ResultSet rs = ps.executeQuery();

			comboBoxModulos.removeAllItems();
			while (rs.next()) {
				comboBoxModulos.addItem(rs.getInt("id") + " - " + rs.getString("nombre_modulo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar módulos");
		}
	}

	private void cargarAlumnos() {
		if (comboBoxModulos.getSelectedItem() == null)
			return;

		String item = comboBoxModulos.getSelectedItem().toString();
		int idModulo = Integer.parseInt(item.split(" - ")[0]);

		tableModel.setRowCount(0); // limpiar tabla

		try (Connection conn = ConexionBD.conectar()) {
			String sql = "SELECT u.id, u.nombre_usuario, am.nota " + "FROM alumno_modulo am "
					+ "JOIN usuarios u ON am.id_alumno = u.id " + "WHERE am.id_modulo = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idModulo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				tableModel
						.addRow(new Object[] { rs.getInt("id"), rs.getString("nombre_usuario"), rs.getDouble("nota") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar alumnos");
		}
	}

	private void guardarNotas() {
		if (comboBoxModulos.getSelectedItem() == null)
			return;
		String item = comboBoxModulos.getSelectedItem().toString();
		int idModulo = Integer.parseInt(item.split(" - ")[0]);

		try (Connection conn = ConexionBD.conectar()) {
			String sql = "UPDATE alumno_modulo SET nota = ? WHERE id_alumno = ? AND id_modulo = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			for (int i = 0; i < tableModel.getRowCount(); i++) {
				double nota = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
				int idAlumno = (int) tableModel.getValueAt(i, 0);

				ps.setDouble(1, nota);
				ps.setInt(2, idAlumno);
				ps.setInt(3, idModulo);
				ps.executeUpdate();
			}

			JOptionPane.showMessageDialog(this, "Notas guardadas correctamente!");

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar las notas");
		}
	}
}
