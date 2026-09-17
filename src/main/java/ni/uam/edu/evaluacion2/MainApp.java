package ni.uam.edu.evaluacion2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ni.uam.edu.evaluacion2.controller.DetalleController;
import ni.uam.edu.evaluacion2.model.Cliente;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        mostrarLogin();
    }

    public static void mostrarLogin() throws IOException {
        cambiarEscena("/ni/uam/edu/evaluacion2/login.fxml", "Inicio de Sesión");
    }

    public static void mostrarPrincipal() throws IOException {
        cambiarEscena("/ni/uam/edu/evaluacion2/main.fxml", "Menú Principal");
    }

    public static void mostrarRegistro() throws IOException {
        cambiarEscena("/ni/uam/edu/evaluacion2/registro.fxml", "Registro de Cliente");
    }

    public static void mostrarConsulta() throws IOException {
        cambiarEscena("/ni/uam/edu/evaluacion2/consulta.fxml", "Consulta de Clientes");
    }

    public static void mostrarDetalle(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/ni/uam/edu/evaluacion2/detalle.fxml"));
        Parent root = loader.load();

        // Pasa el cliente al controlador de detalle
        DetalleController controller = loader.getController();
        controller.setCliente(cliente);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Detalle del Cliente");
        primaryStage.show();
    }

    private static void cambiarEscena(String fxml, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle(titulo);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
