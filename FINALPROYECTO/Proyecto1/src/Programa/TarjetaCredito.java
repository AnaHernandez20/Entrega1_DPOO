package Programa;

import java.time.LocalDate;

public class TarjetaCredito {
	
	//Atributos//
	
	private String tipo;
	private long numero;
	private LocalDate fechaVencimiento;
	private double montoDisponible;
	//Constructors
	
	public TarjetaCredito(String tipo, long numero, LocalDate fechaVencimiento, double montoDisponible) {

		this.tipo = tipo;
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		this.montoDisponible = montoDisponible;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public double getMontoDisponible() {
		return montoDisponible;
	}

	public void setMontoDisponible(double montoDisponible) {
		this.montoDisponible = montoDisponible;
	}
	
	
	

}
