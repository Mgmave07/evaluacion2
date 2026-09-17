module ni.uam.edu.evaluacion2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ni.uam.edu.evaluacion2 to javafx.fxml;
    opens ni.uam.edu.evaluacion2.controller to javafx.fxml;
    opens ni.uam.edu.evaluacion2.model to javafx.fxml;

    // Exportar paquetes para que sean visibles entre módulos
    exports ni.uam.edu.evaluacion2;
    exports ni.uam.edu.evaluacion2.controller;
    exports ni.uam.edu.evaluacion2.model;
}