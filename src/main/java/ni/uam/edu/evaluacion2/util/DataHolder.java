package ni.uam.edu.evaluacion2.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.uam.edu.evaluacion2.model.Cliente;

public class DataHolder {
    private static final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    public static ObservableList<Cliente> getListaClientes() {
        return listaClientes;
    }
}
