package ni.uam.edu.evaluacion2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.uam.edu.evaluacion2.MainApp;
import ni.uam.edu.evaluacion2.model.Cliente;

import java.io.File;
import java.io.IOException;

public class DetalleController {
    @FXML private Label lblNombres, lblApellidos, lblTipo, lblCiudad, lblFecha, lblSolicitud, lblServicios;
    @FXML
    private ImageView imgFotoDetalle;

    // Método para recibir los datos desde la ventana anterior
    public void setCliente(Cliente cliente) {
        lblNombres.setText(cliente.getNombres());
        lblApellidos.setText(cliente.getApellidos());
        lblTipo.setText(cliente.getTipoCliente());
        lblCiudad.setText(cliente.getCiudad());
        lblFecha.setText(cliente.getFechaNacimiento().toString());
        lblSolicitud.setText(cliente.getTipoSolicitud());
        lblServicios.setText(cliente.getServicios());

        if (cliente.getFotoPath() != null && !cliente.getFotoPath().isEmpty()) {
            File file = new File(cliente.getFotoPath());
            if (file.exists()) {
                Image image = new Image(file.toURI().toString(), 200, 200, true, true);
                imgFotoDetalle.setImage(image);
            }
        }
    }

    // Evento de Teclado (KeyEvent): Presionar ESC para volver
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            handleVolver();
        }
    }

    @FXML
    private void handleVolver() {
        try { MainApp.mostrarConsulta(); } catch (IOException e) { e.printStackTrace(); }
    }
}
