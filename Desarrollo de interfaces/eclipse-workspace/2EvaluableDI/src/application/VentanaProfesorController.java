package application;

import java.util.List;

import database.MatriculasDAO;
import database.ProfesorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class VentanaProfesorController {

	@FXML
	private ComboBox<String> cmbModulosProfesor;
	@FXML
	private TableView<AlumnoNotaRow> tablaAlumnos;
	@FXML
	private TableColumn<AlumnoNotaRow, String> colAlumno;
	@FXML
	private TableColumn<AlumnoNotaRow, Double> colNota;

	private Integer moduloIdSeleccionado;

	@FXML
	public void initialize() {

		colAlumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));
		colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

		// Que las columnas ocupen todo el ancho
		tablaAlumnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Cargar módulos del profesor logueado
		int profesorId = Session.getUserId();
		List<String> modulos = ProfesorDAO.obtenerModulosProfesor(profesorId);

		cmbModulosProfesor.getItems().setAll(modulos);

		// Hacer tabla/columna editables
		tablaAlumnos.setEditable(true);
		colNota.setEditable(true);

		// Permitir editar la nota como texto
		colNota.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		// IMPORTANTE: cuando confirmas la edición (ENTER o cambio de foco),
		// copiar el valor editado al objeto de la fila.
		colNota.setOnEditCommit(ev -> {
			AlumnoNotaRow fila = ev.getRowValue();
			fila.setNota(ev.getNewValue());
		});

		// cuando cambie el módulo, recarga tabla
		cmbModulosProfesor.setOnAction(e -> cargarTabla());

		if (!modulos.isEmpty()) {
			cmbModulosProfesor.getSelectionModel().selectFirst();
			cargarTabla();
		}
	}

	private void cargarTabla() {
		String nombreModulo = cmbModulosProfesor.getValue();
		if (nombreModulo == null)
			return;

		moduloIdSeleccionado = ProfesorDAO.obtenerModuloIdPorNombre(nombreModulo);
		if (moduloIdSeleccionado == null)
			return;

		List<AlumnoNotaRow> rows = MatriculasDAO.alumnosConNotaEnModulo(moduloIdSeleccionado);
		ObservableList<AlumnoNotaRow> data = FXCollections.observableArrayList(rows);
		tablaAlumnos.setItems(data);
	}

	@FXML
	private void onGuardarNota() {

		// Forzar cierre/commit de la celda en edición para no guardar el valor antiguo
		if (tablaAlumnos.getEditingCell() != null) {
			tablaAlumnos.edit(-1, null);
		}

		AlumnoNotaRow fila = tablaAlumnos.getSelectionModel().getSelectedItem();
		if (fila == null) {
			mostrarAlerta("Selecciona un alumno de la tabla.");
			return;
		}
		if (moduloIdSeleccionado == null) {
			mostrarAlerta("Selecciona un módulo.");
			return;
		}

		Double nota = fila.getNota();
		if (nota == null) {
			mostrarAlerta("La nota está vacía. Escribe una nota primero.");
			return;
		}

		if (nota < 0 || nota > 10) {
			mostrarAlerta("La nota debe estar entre 0 y 10.");
			return;
		}

		MatriculasDAO.guardarNota(fila.getAlumnoId(), moduloIdSeleccionado, nota);
		mostrarAlerta("Nota guardada.");
		cargarTabla();
	}

	private void mostrarAlerta(String msg) {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.showAndWait();
	}

	@FXML
	private void onVolver() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Principal.fxml"));
			Stage stage = (Stage) tablaAlumnos.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Ventana Principal");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.clear();
	}
}
