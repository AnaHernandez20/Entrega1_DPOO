package Usuarios;

import java.time.LocalDate;

import Programa.LicenciaConduccion;
import Programa.TarjetaCredito;

public class Cliente extends UsuarioGenerico{
	
	public Cliente(String login, String password, String nombre, String rol, String telefono, String correo, LocalDate fechaNacimiento, LicenciaConduccion licencia, TarjetaCredito mediopago) {
		super(login, password, nombre, rol);
		this.telefono = telefono;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.licencia = licencia;
		this.medioPago = mediopago;
		
		
	}
	//Atributos//
	
	private String telefono;
	private String correo;
	private LocalDate fechaNacimiento;
	private LicenciaConduccion licencia;
	private TarjetaCredito medioPago;
	
	//Getters//
	
	public String getTelefono() {
		return telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public LicenciaConduccion getLicencia() {
		return licencia;
	}
	public TarjetaCredito getMedioPago() {
		return medioPago;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setLicencia(LicenciaConduccion licencia) {
		this.licencia = licencia;
	}
	public void setMedioPago(TarjetaCredito medioPago) {
		this.medioPago = medioPago;
	}
	
	

}
