package ni.uam.edu.evaluacion2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ni.uam.edu.evaluacion2.MainApp;
import ni.uam.edu.evaluacion2.model.Cliente;
import ni.uam.edu.evaluacion2.util.DataHolder;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class RegistroController {
    @FXML private TextField txtNombres, txtApellidos;
    @FXML private ComboBox<String> cmbTipoCliente, cmbCiudad;
    @FXML private DatePicker dpFechaNac;
    @FXML private ToggleGroup tgSolicitud;
    @FXML private CheckBox chkAsesoria, chkSoporte;
    @FXML private ImageView imgFoto;

    private String fotoPath = "";

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().addAll("Natural", "Jurídico");
        cmbCiudad.getItems().addAll("Managua", "Masaya", "Esteli");
    }

    @FXML
    private void handleSeleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Fotografía");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(MainApp.getPrimaryStage());
        if (file != null) {
            fotoPath = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString(), imgFoto.getFitWidth(), imgFoto.getFitHeight(), true, true);
            imgFoto.setImage(image);
        }
    }
    @FXML

    private void handleAgregarCiudad() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Nueva Ciudad");
        dialog.setHeaderText("La ciudad no se encuentra en la lista");
        dialog.setContentText("Por favor, escriba el nombre de la nueva ciudad:");


        Optional<String> resultado = dialog.showAndWait();


        resultado.ifPresent(nuevaCiudad -> {
            if (!nuevaCiudad.trim().isEmpty()) {
                // Agregamos la ciudad al ComboBox
                if (!cmbCiudad.getItems().contains(nuevaCiudad.trim())) {
                    cmbCiudad.getItems().add(nuevaCiudad.trim());
                }
                // Seleccionamos automáticamente la nueva ciudad
                cmbCiudad.setValue(nuevaCiudad.trim());

                // Opcional: mensaje de éxito
                Alert exito = new Alert(Alert.AlertType.INFORMATION, "Ciudad '" + nuevaCiudad + "' agregada y seleccionada.");
                exito.showAndWait();
            }
        });
    }

    @FXML
    private void handleGuardar() {

        if (txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty() ||
                cmbTipoCliente.getValue() == null || cmbCiudad.getValue() == null || dpFechaNac.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete todos los campos obligatorios.");
            alert.showAndWait(); return;
        }
        if (tgSolicitud.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione un tipo de solicitud.");
            alert.showAndWait(); return;
        }


        RadioButton rbSeleccionado = (RadioButton) tgSolicitud.getSelectedToggle();
        String tipoSol = rbSeleccionado.getText();
        String servicios = (chkAsesoria.isSelected() ? "Asesoría " : "") + (chkSoporte.isSelected() ? "Soporte" : "");


        Cliente nuevoCliente = new Cliente(txtNombres.getText(), txtApellidos.getText(),
                cmbTipoCliente.getValue(), cmbCiudad.getValue(), dpFechaNac.getValue(),
                tipoSol, servicios.trim(), fotoPath);

        DataHolder.getListaClientes().add(nuevoCliente);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cliente guardado exitosamente.");
        alert.showAndWait();

        try { MainApp.mostrarConsulta(); } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    private void handleLimpiar() {
        txtNombres.clear(); txtApellidos.clear();
        cmbTipoCliente.setValue(null); cmbCiudad.setValue(null); dpFechaNac.setValue(null);
        tgSolicitud.selectToggle(null); chkAsesoria.setSelected(false); chkSoporte.setSelected(false);
        imgFoto.setImage(null); fotoPath = "";
    }

    @FXML
    private void handleCancelar() {
        try { MainApp.mostrarPrincipal(); } catch (IOException e) { e.printStackTrace(); }
    }
    }
