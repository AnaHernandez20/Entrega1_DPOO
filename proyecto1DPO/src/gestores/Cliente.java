package gestores;

import java.util.Date;

public class Cliente {
    private int numeroReserva;
    private String nombre;
    private String telefono;
    private String correo;
    private String nacionalidad;
    private String imagenDocumento;
    private Date fechaNacimiento;

    public Cliente(int numeroReserva, String nombre, String telefono, String correo, String nacionalidad, String imagenDocumento,
                   Date fechaNacimiento) {
        this.numeroReserva = numeroReserva;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.imagenDocumento = imagenDocumento;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters
    public int getNumeroReserva() {
        return numeroReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getImagenDocumento() {
        return imagenDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Setters
    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setImagenDocumento(String imagenDocumento) {
        this.imagenDocumento = imagenDocumento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

