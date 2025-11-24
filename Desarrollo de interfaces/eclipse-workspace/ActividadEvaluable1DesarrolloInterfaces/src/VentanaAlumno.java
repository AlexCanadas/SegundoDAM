import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import bdd.GestionBD;

public class VentanaAlumno extends JFrame {

	private JPanel contentPane;
	private JList<String> listModulos;
	private int idAlumno;

	public VentanaAlumno(int idAlumno) {
		this.idAlumno = idAlumno;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaInicio.class.getResource("/imagenes/UE ICONO PEQUEÑO.jpg")));
		setTitle("Alumno - Mis módulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Selecciona los módulos para ver tus notas:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitulo, BorderLayout.NORTH);

		listModulos = new JList<>();
		listModulos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listModulos);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		JButton btnVolver = new JButton("Volver");
		panelBotones.add(btnVolver);
		JButton btnVerNotas = new JButton("Ver notas");
		panelBotones.add(btnVerNotas);
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		// Acción botón Volver
		btnVolver.addActionListener(e -> {
			VentanaInicio vi = new VentanaInicio();
			vi.setVisible(true);
			dispose();
		});

		// Acción botón Ver notas (simple ejemplo)
		btnVerNotas.addActionListener(e -> {
			java.util.List<String> seleccion = listModulos.getSelectedValuesList();
			if (seleccion.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un módulo.");
			} else {
				JOptionPane.showMessageDialog(this, "Módulos seleccionados: " + seleccion);
			}
		});

		// Cargar módulos del alumno
		cargarModulos();
	}

	private void cargarModulos() {
		DefaultListModel<String> modelo = new DefaultListModel<>();

		try {
			var rs = GestionBD.obtenerModulosAlumno(idAlumno);

			while (rs.next()) {
				String item = rs.getString("nombre_modulo") + " - Nota: " + rs.getDouble("nota");
				modelo.addElement(item);
			}

			listModulos.setModel(modelo);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar módulos");
		}
	}

}
