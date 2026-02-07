package application;

import java.io.IOException;

import database.AuthDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrincipalController {

	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPass;
	@FXML
	private ComboBox<String> cmbCargo;
	@FXML
	private Label lblMsg;

	// Esto se ejecuta al cargar el FXML
	@FXML
	private void initialize() {
		// Rellenamos el combo
		cmbCargo.getItems().addAll("Alumno", "Profesor");
		cmbCargo.getSelectionModel().selectFirst(); // selecciona "Alumno"
	}

	@FXML
	private void onEntrar(ActionEvent e) throws IOException {
		String cargo = cmbCargo.getValue(); // "Alumno" o "Profesor"
		String user = txtUsuario.getText() == null ? "" : txtUsuario.getText().trim();
		String pass = txtPass.getText() == null ? "" : txtPass.getText();

		Integer id = cargo.equals("Profesor") ? AuthDAO.loginProfesor(user, pass) : AuthDAO.loginAlumno(user, pass);

		if (id != null) {
			lblMsg.setText("Bienvenido " + user);

			Session.setSession(id, user, cargo);

			String fxml = cargo.equals("Profesor") ? "/VentanaProfesor.fxml" : "/VentanaAlumno.fxml";
			Parent root = FXMLLoader.load(getClass().getResource(fxml));

			Stage stage = (Stage) txtUsuario.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Ventana " + cargo);
			stage.show();

		} else {
			lblMsg.setText("Datos incorrectos");
		}

	}

	@FXML
	private void onLimpiar(ActionEvent e) {
		txtUsuario.clear();
		txtPass.clear();
		lblMsg.setText("");
		cmbCargo.getSelectionModel().selectFirst();
	}

	@FXML
	private void onSalir(ActionEvent e) {
		Stage stage = (Stage) txtUsuario.getScene().getWindow();
		stage.close();
	}
}
