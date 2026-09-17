package ni.uam.edu.evaluacion2.model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Cliente {
    private SimpleStringProperty nombres, apellidos, tipoCliente, ciudad, tipoSolicitud, servicios, fotoPath;
    private LocalDate fechaNacimiento;

    public Cliente(String nombres, String apellidos, String tipoCliente, String ciudad,
                   LocalDate fechaNacimiento, String tipoSolicitud, String servicios, String fotoPath) {
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.tipoCliente = new SimpleStringProperty(tipoCliente);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSolicitud = new SimpleStringProperty(tipoSolicitud);
        this.servicios = new SimpleStringProperty(servicios);
        this.fotoPath = new SimpleStringProperty(fotoPath);
    }

    // Getters y Setters (Usando SimpleStringProperty para TableView)
    public String getNombres() { return nombres.get(); }
    public void setNombres(String v) { nombres.set(v); }
    public String getApellidos() { return apellidos.get(); }
    public String getTipoCliente() { return tipoCliente.get(); }
    public String getCiudad() { return ciudad.get(); }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getTipoSolicitud() { return tipoSolicitud.get(); }
    public String getServicios() { return servicios.get(); }
    public String getFotoPath() { return fotoPath.get(); }
}
