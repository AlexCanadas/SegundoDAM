package application;

import java.util.List;

import database.NotasDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaAlumnoController {

	@FXML
	private ComboBox<String> cmbModulos;
	@FXML
	private TableView<Nota> tablaNotas;
	@FXML
	private TableColumn<Nota, String> colModulo;
	@FXML
	private TableColumn<Nota, Double> colNota;
	@FXML
	private ObservableList<Nota> datosOriginales;

	@FXML
	public void initialize() {

		// Columnas tabla
		colModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
		colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

		int alumnoId = Session.getUserId();

		List<Nota> desdeBD = NotasDAO.obtenerNotasAlumno(alumnoId); // traemos notas desde la bdd
		// llenamos tabla
		datosOriginales = FXCollections.observableArrayList(desdeBD);
		tablaNotas.setItems(datosOriginales);

		// rellenar combo con "Todos" + módulos encontrados
		cmbModulos.getItems().clear();
		cmbModulos.getItems().add("Todos");
		for (Nota n : datosOriginales) {
			if (!cmbModulos.getItems().contains(n.getModulo())) {
				cmbModulos.getItems().add(n.getModulo());
			}
		}
		cmbModulos.getSelectionModel().selectFirst();
		cmbModulos.setOnAction(e -> filtrarTabla());

	}

	private void filtrarTabla() {
		String seleccionado = cmbModulos.getValue();

		if (seleccionado == null || seleccionado.equals("Todos")) {
			tablaNotas.setItems(datosOriginales);
			return;
		}

		ObservableList<Nota> filtrado = FXCollections.observableArrayList();
		for (Nota n : datosOriginales) {
			if (n.getModulo().equals(seleccionado)) {
				filtrado.add(n);
			}
		}
		tablaNotas.setItems(filtrado);
	}

	@FXML
	private void onVolver() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Principal.fxml"));
			Stage stage = (Stage) tablaNotas.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Ventana Principal");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.clear();

	}

}
