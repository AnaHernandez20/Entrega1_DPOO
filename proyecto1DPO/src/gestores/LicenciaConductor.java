package gestores;

import java.sql.Date;

public class LicenciaConductor {
long numero;
String paisexpedicion;
Date fechaVencimiento;
String imagen;

public LicenciaConductor(long numero, String paisexpedicion, Date fechaVencimiento, String imagen) {
	this.numero = numero;
	this.paisexpedicion = paisexpedicion;
	this.fechaVencimiento = fechaVencimiento;
	this.imagen = imagen;
}

public long getNumero() {
	return numero;
}

public void setNumero(long numero) {
	this.numero = numero;
}

public String getPaisexpedicion() {
	return paisexpedicion;
}

public void setPaisexpedicion(String paisexpedicion) {
	this.paisexpedicion = paisexpedicion;
}

public Date getFechaVencimiento() {
	return fechaVencimiento;
}

public void setFechaVencimiento(Date fechaVencimiento) {
	this.fechaVencimiento = fechaVencimiento;
}

public String getImagen() {
	return imagen;
}

public void setImagen(String imagen) {
	this.imagen = imagen;
}



}
