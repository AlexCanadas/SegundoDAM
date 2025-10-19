package ventanaCalculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class jFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPantalla;

	/**
	 * Variables para guardar número seleccionado anteriormente y número que se está
	 * seleccionando
	 */
	private double num1 = 0;
	private double num2 = 0;
	// Variable para guardar que operación se elige
	private String operador = "";

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		txtPantalla = new JTextField();
		txtPantalla.setEditable(false); // Ponemos false para que el usuario no pueda cambiarlo
		contentPane.add(txtPantalla, BorderLayout.NORTH);
		txtPantalla.setColumns(10);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 4, 5, 5));

		JButton btn7 = new JButton("7");
		btn7.setBackground(new Color(0, 128, 255)); // Color azul para los números
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "7");
			}
		});
		panel.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setBackground(new Color(0, 128, 255));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "8");
			}
		});
		panel.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setBackground(new Color(0, 128, 255));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "9");
			}
		});
		panel.add(btn9);

		JButton btnDivide = new JButton("/");
		btnDivide.setBackground(new Color(255, 128, 0)); // Color naranja para las operaciones
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num1 = Double.parseDouble(txtPantalla.getText());
				operador = "/";
				txtPantalla.setText("");
			}
		});
		panel.add(btnDivide);

		JButton btn4 = new JButton("4");
		btn4.setBackground(new Color(0, 128, 255));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "4");
			}
		});
		panel.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setBackground(new Color(0, 128, 255));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "5");
			}
		});
		panel.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setBackground(new Color(0, 128, 255));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "6");
			}
		});
		panel.add(btn6);

		JButton btnMultiplica = new JButton("*");
		btnMultiplica.setBackground(new Color(255, 128, 0));
		btnMultiplica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num1 = Double.parseDouble(txtPantalla.getText());
				operador = "*";
				txtPantalla.setText("");
			}
		});
		panel.add(btnMultiplica);

		JButton btn1 = new JButton("1");
		btn1.setBackground(new Color(0, 128, 255));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "1");
			}
		});
		panel.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setBackground(new Color(0, 128, 255));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "2");
			}
		});
		panel.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setBackground(new Color(0, 128, 255));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "3");
			}
		});
		panel.add(btn3);

		JButton btnResta = new JButton("-");
		btnResta.setBackground(new Color(255, 128, 0));
		btnResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num1 = Double.parseDouble(txtPantalla.getText());
				operador = "-";
				txtPantalla.setText("");
			}
		});
		panel.add(btnResta);

		JButton btn0 = new JButton("0");
		btn0.setBackground(new Color(0, 128, 255));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText(txtPantalla.getText() + "0");
			}
		});
		panel.add(btn0);

		JButton btnC = new JButton("C");
		btnC.setBackground(new Color(128, 255, 128)); // Color verde para borrar e igual
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPantalla.setText("");
				num1 = 0;
				num2 = 0;
				operador = "";
			}
		});
		panel.add(btnC);

		JButton btnIgual = new JButton("=");
		btnIgual.setBackground(new Color(128, 255, 128));
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num2 = Double.parseDouble(txtPantalla.getText());
				double resultado = 0;

				switch (operador) {
				case "+":
					resultado = num1 + num2;
					break;
				case "-":
					resultado = num1 - num2;
					break;
				case "*":
					resultado = num1 * num2;
					break;
				case "/":
					if (num2 != 0)
						resultado = num1 / num2;
					else {
						txtPantalla.setText("Error");
						return;
					}
					break;
				}

				txtPantalla.setText(String.valueOf(resultado));
			}
		});
		panel.add(btnIgual);

		JButton btnSuma = new JButton("+");
		btnSuma.setBackground(new Color(255, 128, 0));
		btnSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num1 = Double.parseDouble(txtPantalla.getText());
				operador = "+";
				txtPantalla.setText("");
			}
		});
		panel.add(btnSuma);
	}

}
