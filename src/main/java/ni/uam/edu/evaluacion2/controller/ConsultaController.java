package ni.uam.edu.evaluacion2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ni.uam.edu.evaluacion2.MainApp;
import ni.uam.edu.evaluacion2.model.Cliente;
import ni.uam.edu.evaluacion2.util.DataHolder;

import java.io.IOException;
import java.time.LocalDate;

public class ConsultaController {
    @FXML private TableView<Cliente> tableView;
    @FXML private TableColumn<Cliente, String> colNombre, colTipo, colCiudad, colSolicitud;
    @FXML private TableColumn<Cliente, String> colFecha; // Usaremos String para simplificar el mapeo

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));


        colFecha.setCellValueFactory(cellData -> {
            LocalDate fecha = cellData.getValue().getFechaNacimiento();
            return new javafx.beans.property.SimpleStringProperty(
                    fecha != null ? fecha.toString() : ""
            );
        });

        tableView.setItems(DataHolder.getListaClientes());
    }


    @FXML
    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Cliente seleccionado = tableView.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                try {
                    MainApp.mostrarDetalle(seleccionado); // Paso de datos
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void handleVolver() {
        try { MainApp.mostrarPrincipal(); } catch (IOException e) { e.printStackTrace(); }
    }
}
