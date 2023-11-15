package Programa;

public class Categoria {
	
	//Atributos//
	
	private String nombre;
	private double tarifaDiariaAlta;
	private double tarifaDiariaBaja;
	private double valorSedeDiferente;
	private double valorConductorExtra;
	
	//Getters and Setters//
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getTarifaDiariaAlta() {
		return tarifaDiariaAlta;
	}
	public void setTarifaDiariaAlta(double tarifaDiariaAlta) {
		this.tarifaDiariaAlta = tarifaDiariaAlta;
	}
	public double getTarifaDiariaBaja() {
		return tarifaDiariaBaja;
	}
	public void setTarifaDiariaBaja(double tarifaDiariaBaja) {
		this.tarifaDiariaBaja = tarifaDiariaBaja;
	}
	public double getValorSedeDiferente() {
		return valorSedeDiferente;
	}
	public void setValorSedeDiferente(double valorSedeDiferente) {
		this.valorSedeDiferente = valorSedeDiferente;
	}
	public double getValorConductorExtra() {
		return valorConductorExtra;
	}
	public void setValorConductorExtra(double valorConductorExtra) {
		this.valorConductorExtra = valorConductorExtra;
	}
	
	//Constructor//
	public Categoria(String nombre, double tarifaDiariaAlta, double tarifaDiariaBaja, double valorSedeDiferente,
			double valorConductorExtra) {
		super();
		this.nombre = nombre;
		this.tarifaDiariaAlta = tarifaDiariaAlta;
		this.tarifaDiariaBaja = tarifaDiariaBaja;
		this.valorSedeDiferente = valorSedeDiferente;
		this.valorConductorExtra = valorConductorExtra;
	}
	
	
	

}
