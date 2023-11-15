package Programa;

import java.time.LocalDate;

public class LicenciaConduccion {
	
	//Atributos//
	
	private long numero;
	private String paisExpedicion;
	private LocalDate fechaVencimiento;
	private String imagen;
	
	//Constructor//
	
	public LicenciaConduccion(long numero, String paisExpedicion, LocalDate fechaVencimiento, String imagen) {
		this.numero = numero;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		this.imagen = imagen;
	}
	
	//Getters//
	
	public long getNumero() {
		return numero;
	}
	public String getPaisExpedicion() {
		return paisExpedicion;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public String getImagen() {
		return imagen;
	}
	
	//Metodos//
	
	

}

