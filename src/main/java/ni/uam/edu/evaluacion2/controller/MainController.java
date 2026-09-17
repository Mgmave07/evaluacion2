package ni.uam.edu.evaluacion2.controller;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import ni.uam.edu.evaluacion2.util.DataHolder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import ni.uam.edu.evaluacion2.MainApp;

import java.io.File;
import java.io.IOException;

public class MainController {
    @FXML private Label lblAreaTrabajo;

    @FXML
    private void initialize() {
        // 1. Creamos el menú contextual
        ContextMenu menuContextual = new ContextMenu();

        // 2. Opción 1: Ver resumen de clientes
        MenuItem verResumen = new MenuItem("📊 Ver resumen de clientes registrados");
        verResumen.setOnAction(e -> {
            int total = DataHolder.getListaClientes().size();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resumen del Sistema");
            alert.setHeaderText("Estadísticas de clientes");
            alert.setContentText("Total de clientes registrados: " + total);
            alert.showAndWait();
        });

        // 3. Opción 2: Limpiar todos los registros (ÚTIL para pruebas)
        MenuItem limpiarTodo = new MenuItem("🗑️ Limpiar todos los registros");
        limpiarTodo.setOnAction(e -> {
            if (DataHolder.getListaClientes().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Sin datos");
                alert.setContentText("No hay registros para eliminar.");
                alert.showAndWait();
                return;
            }

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("¿Está seguro de eliminar TODOS los clientes?");
            confirmacion.setContentText("Esta acción no se puede deshacer.");

            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    DataHolder.getListaClientes().clear();
                    Alert exito = new Alert(Alert.AlertType.INFORMATION);
                    exito.setTitle("Éxito");
                    exito.setContentText("Todos los registros han sido eliminados.");
                    exito.showAndWait();
                }
            });
        });

        // 4. Agregamos las opciones al menú
        menuContextual.getItems().addAll(verResumen, limpiarTodo);

        // 5. Asignamos el menú al Label central
        lblAreaTrabajo.setContextMenu(menuContextual);
    }

    @FXML
    private void handleBtnRegistro() {
        try { MainApp.mostrarRegistro(); } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    private void handleBtnConsulta() {
        try { MainApp.mostrarConsulta(); } catch (IOException e) { e.printStackTrace(); }
    }

    // Uso de DirectoryChooser
    @FXML
    private void handleConfigurarCarpeta() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar Carpeta de Respaldos");
        File selectedDir = directoryChooser.showDialog(MainApp.getPrimaryStage());
        if (selectedDir != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Carpeta configurada en: " + selectedDir.getAbsolutePath());
            alert.showAndWait();
        }
    }

    // Uso de Dialog personalizado (TextInputDialog)
    @FXML
    private void handleAcercaDe() {
        TextInputDialog dialog = new TextInputDialog("Escriba su nombre");
        dialog.setTitle("Acerca de / Nota");
        dialog.setHeaderText("Registro de Nota del Sistema");
        dialog.setContentText("Por favor, ingrese su nombre:");
        dialog.showAndWait().ifPresent(nombre -> {
            System.out.println("Nota registrada por: " + nombre);
        });
    }
}
