package ventas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class jFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrame frame = new jFrame();
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
	public jFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 209);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(212, 212, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 5));

		JLabel lblVentas = new JLabel("Ventas");
		lblVentas.setBackground(new Color(212, 212, 212));
		lblVentas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblVentas);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(212, 212, 212));
		contentPane.add(panel);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCliente);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnBuscar.setBackground(new Color(225, 227, 227));
		panel.add(btnBuscar);
	}

}
