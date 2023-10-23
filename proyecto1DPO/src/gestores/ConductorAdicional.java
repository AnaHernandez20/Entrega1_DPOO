package gestores;

import java.time.LocalDate;

public class ConductorAdicional {
String nombre;
String correo;
String telefono;
LocalDate fechaVencimiento;
private LicenciaConductor licenciaConductor;

public ConductorAdicional(String nombre, String correo, String telefono, LocalDate fechaVencimiento) {
	this.nombre = nombre;
	this.correo = correo;
	this.telefono = telefono;
	this.fechaVencimiento = fechaVencimiento;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public LocalDate getFechaVencimiento() {
	return fechaVencimiento;
}

public void setFechaVencimiento(LocalDate fechaVencimiento) {
	this.fechaVencimiento = fechaVencimiento;
}




}
