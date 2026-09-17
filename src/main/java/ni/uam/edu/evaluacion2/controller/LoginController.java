package ni.uam.edu.evaluacion2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.uam.edu.evaluacion2.MainApp;

import java.io.IOException;

public class LoginController {

        @FXML
        private TextField txtUsuario;
        @FXML private PasswordField txtPassword;
        @FXML private Button btnIniciar;
        @FXML private Button btnSalir;

        // Evento de Teclado (KeyEvent): Presionar ENTER en el password inicia sesión
        @FXML
        private void handlePasswordKeyPressed(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                intentarLogin();
            }
        }

        @FXML
        private void handleBtnIniciarAction() { intentarLogin(); }

        private void intentarLogin() {
            if (txtUsuario.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Debe ingresar usuario y contraseña.");
                return;
            }
            // Validación simple (Usuario: admin, Pass: 123)
            if (txtUsuario.getText().equals("admin") && txtPassword.getText().equals("123")) {
                try { MainApp.mostrarPrincipal(); } catch (IOException e) { e.printStackTrace(); }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de Acceso", "Credenciales incorrectas.");
            }
        }

        @FXML
        private void handleBtnSalirAction() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que desea salir del sistema?");
            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    System.exit(0);
                }
            });
        }

        private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
            Alert alert = new Alert(tipo);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(contenido);
            alert.showAndWait();
        }
}

