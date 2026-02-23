import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    public static class Persona {
        private final String dni, nombre, apellidos, direccion, contrasena;
        public Persona(String dni, String nombre, String apellidos, String direccion, String contrasena) {
            this.dni = dni; this.nombre = nombre; this.apellidos = apellidos; this.direccion = direccion; this.contrasena = contrasena;
        }
        public String getDni() { return dni; }
        public String getNombre() { return nombre; }
        public String getApellidos() { return apellidos; }
        public String getDireccion() { return direccion; }
        public String getContrasena() { return contrasena; }
    }

    private final ObservableList<Persona> datos = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("DI 2025 - JavaFX");

        TextField txtDni = new TextField();
        TextField txtNombre = new TextField();
        TextField txtApellidos = new TextField();
        TextField txtDireccion = new TextField();
        PasswordField txtContrasena = new PasswordField();

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(12);
        form.add(new Label("DNI"), 0, 0); form.add(txtDni, 1, 0);
        form.add(new Label("NOMBRE"), 0, 1); form.add(txtNombre, 1, 1);
        form.add(new Label("APELLIDOS"), 0, 2); form.add(txtApellidos, 1, 2);
        form.add(new Label("DIRECCION"), 0, 3); form.add(txtDireccion, 1, 3);
        form.add(new Label("CONTRASEÑA"), 0, 4); form.add(txtContrasena, 1, 4);

        Button btnInsertar = new Button("INSERTAR");
        Button btnSalir = new Button("SALIR");
        VBox botones = new VBox(18, btnInsertar, btnSalir);

        TableView<Persona> tabla = new TableView<>(datos);
        tabla.setPrefHeight(320);

        TableColumn<Persona, String> cDni = new TableColumn<>("DNI");
        cDni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        TableColumn<Persona, String> cNom = new TableColumn<>("NOMBRE");
        cNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Persona, String> cApe = new TableColumn<>("APELLIDOS");
        cApe.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<Persona, String> cDir = new TableColumn<>("DIRECCION");
        cDir.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<Persona, String> cCon = new TableColumn<>("CONTRASEÑA");
        cCon.setCellValueFactory(new PropertyValueFactory<>("contrasena"));

        tabla.getColumns().addAll(cDni, cNom, cApe, cDir, cCon);

        HBox arriba = new HBox(30, form, botones);
        VBox root = new VBox(15, arriba, tabla);
        root.setPadding(new Insets(15));

        btnInsertar.setOnAction(e -> {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String pass = txtContrasena.getText().trim();

            if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || pass.isEmpty()) {
                alerta("Rellena todos los campos.");
                return;
            }
            if (dniExiste(dni)) {
                alerta("No se puede repetir el DNI.");
                return;
            }

            datos.add(new Persona(dni, nombre, apellidos, direccion, pass));
            txtDni.clear(); txtNombre.clear(); txtApellidos.clear(); txtDireccion.clear(); txtContrasena.clear();
        });

        btnSalir.setOnAction(e -> stage.close());

        stage.setScene(new Scene(root, 750, 520));
        stage.show();
    }

    private boolean dniExiste(String dni) {
        for (Persona p : datos) {
            if (p.getDni().equalsIgnoreCase(dni)) return true;
        }
        return false;
    }

    private void alerta(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
