import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DatosPersonaController {

	@FXML
	private TextField txtDni;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellidos;
	@FXML
	private TextField txtDireccion;
	@FXML
	private TextField txtContrasena;
	@FXML
	private TextField txtBuscar;

	@FXML
	private Button btnInsertar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnLimpiar;
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnMostrarTodos;
	@FXML
	private Button btnSalir;

	@FXML
	private TableView<Persona> tablaPersonas;
	@FXML
	private TableColumn<Persona, String> colDni;
	@FXML
	private TableColumn<Persona, String> colNombre;
	@FXML
	private TableColumn<Persona, String> colApellidos;
	@FXML
	private TableColumn<Persona, String> colDireccion;
	@FXML
	private TableColumn<Persona, String> colContrasena;

	private ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
		colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		colContrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));

		tablaPersonas.setItems(listaPersonas);
	}

	@FXML
	private void insertar() {
		String dni = txtDni.getText().trim();
		String nombre = txtNombre.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		String direccion = txtDireccion.getText().trim();
		String contrasena = txtContrasena.getText().trim();

		if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || contrasena.isEmpty()) {
			mostrarMensaje("Error", "Todos los campos deben estar rellenos");
			return;
		}

		for (Persona p : listaPersonas) {
			if (p.getDni().equals(dni)) {
				mostrarMensaje("Error", "El DNI no puede repetirse");
				return;
			}
		}

		Persona persona = new Persona(dni, nombre, apellidos, direccion, contrasena);
		listaPersonas.add(persona);
		limpiar();
		mostrarMensaje("Información", "Registro insertado correctamente");
	}

	@FXML
	private void borrar() {
		Persona seleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

		if (seleccionada == null) {
			mostrarMensaje("Error", "Debes seleccionar una fila");
			return;
		}

		listaPersonas.remove(seleccionada);
	}

	@FXML
	private void limpiar() {
		txtDni.clear();
		txtNombre.clear();
		txtApellidos.clear();
		txtDireccion.clear();
		txtContrasena.clear();
	}

	@FXML
	private void salir() {
		Stage stage = (Stage) btnSalir.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void modificar() {
		// No hace nada de momento
	}

	@FXML
	private void buscar() {
		// No hace nada de momento
	}

	@FXML
	private void mostrarTodos() {
		// No hace nada de momento
	}

	private void mostrarMensaje(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}