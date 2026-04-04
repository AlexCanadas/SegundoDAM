import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana5Nombres extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreElegido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana5Nombres frame = new Ventana5Nombres();
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
	public Ventana5Nombres() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblEligeUnaOpcion = new JLabel("Elige una opción:");
		lblEligeUnaOpcion.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JComboBox comboNombres = new JComboBox();
		comboNombres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombreElegido.setText(comboNombres.getSelectedItem().toString());
			}
		});
		comboNombres.setModel(new DefaultComboBoxModel(new String[] {"Fernando", "María", "Lucía", "Carlos", "Ana"}));
		
		JLabel lblNombreElegido = new JLabel("Nombre elegido");
		lblNombreElegido.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		txtNombreElegido = new JTextField();
		txtNombreElegido.setEditable(false);
		txtNombreElegido.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(174)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEligeUnaOpcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
						.addComponent(lblNombreElegido, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
						.addComponent(comboNombres, 0, 99, Short.MAX_VALUE)
						.addComponent(txtNombreElegido, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
					.addGap(174))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addComponent(lblEligeUnaOpcion)
					.addGap(18)
					.addComponent(comboNombres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNombreElegido)
					.addGap(18)
					.addComponent(txtNombreElegido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
