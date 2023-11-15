package Programa;

public class Seguro {
	
	//Atributos//
	
	private String nombre;
	private double tarifaDiaria;
	
	//Getters and Setters// 
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getTarifaDiaria() {
		return tarifaDiaria;
	}
	public void setTarifaDiaria(double tarifaDiaria) {
		this.tarifaDiaria = tarifaDiaria;
	}
	
	//Constructor//
	public Seguro(String nombre, double tarifaDiaria) {
		super();
		this.nombre = nombre;
		this.tarifaDiaria = tarifaDiaria;
	}
	

}
