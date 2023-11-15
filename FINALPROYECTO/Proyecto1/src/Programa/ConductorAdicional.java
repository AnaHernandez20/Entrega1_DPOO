package Programa;

public class ConductorAdicional {
	
	//Atributos//
	
	private String nombre;
	private String telefono;
	private String correo;
	private LicenciaConduccion licencia;
	
	//Getters and Setters//
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public LicenciaConduccion getLicencia() {
		return licencia;
	}
	public void setLicencia(LicenciaConduccion licencia) {
		this.licencia = licencia;
	}
	
	
	//Constructor//
	public ConductorAdicional(String nombre, String telefono, String correo, LicenciaConduccion licencia) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.licencia = licencia;
	}
	

}
