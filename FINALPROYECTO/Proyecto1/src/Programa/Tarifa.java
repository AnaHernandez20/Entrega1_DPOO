package Programa;

public class Tarifa {
	
	private int diasRenta;
	private int numeroConductoresExtra;
	private double tarifaDiariaNormal;
	private double precioSeguros;
	private double tarifaDiferenteSede;
	private double tarifaConductoresExtra = 2000;
	private double precioTotal = 0;
	
	//Constructor//
	public Tarifa(int diasRenta, boolean mismaSede, int numeroConductoresExtra, double tarifaDiariaNormal,
			double precioSeguros) {
		if (mismaSede) {
			this.tarifaDiferenteSede = 0;
		}
		else {
			this.tarifaDiferenteSede = 2000;
		}
		this.diasRenta = diasRenta;
		this.numeroConductoresExtra = numeroConductoresExtra;
		this.tarifaDiariaNormal = tarifaDiariaNormal;
		this.precioSeguros = precioSeguros;
		precioTotal = diasRenta*tarifaDiariaNormal + precioSeguros + tarifaDiferenteSede + tarifaConductoresExtra*numeroConductoresExtra;
	}

	public int getDiasRenta() {
		return diasRenta;
	}

	public void setDiasRenta(int diasRenta) {
		this.diasRenta = diasRenta;
	}

	public int getNumeroConductoresExtra() {
		return numeroConductoresExtra;
	}

	public void setNumeroConductoresExtra(int numeroConductoresExtra) {
		this.numeroConductoresExtra = numeroConductoresExtra;
	}

	public double getTarifaDiariaNormal() {
		return tarifaDiariaNormal;
	}

	public void setTarifaDiariaNormal(double tarifaDiariaNormal) {
		this.tarifaDiariaNormal = tarifaDiariaNormal;
	}

	public double getPrecioSeguros() {
		return precioSeguros;
	}

	public void setPrecioSeguros(double precioSeguros) {
		this.precioSeguros = precioSeguros;
	}

	public double getTarifaDiferenteSede() {
		return tarifaDiferenteSede;
	}

	public void setTarifaDiferenteSede(double tarifaDiferenteSede) {
		this.tarifaDiferenteSede = tarifaDiferenteSede;
	}

	public double getTarifaConductoresExtra() {
		return tarifaConductoresExtra;
	}

	public void setTarifaConductoresExtra(double tarifaConductoresExtra) {
		this.tarifaConductoresExtra = tarifaConductoresExtra;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	
	
	
}
