package Programa;

public class Vehiculo {
	
	//Atributos//
	
	private Categoria categoria;
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String tipoTransmision;
	private int capacidadPersonas;
	private boolean alquilado;
	
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTipoTransmision() {
		return tipoTransmision;
	}
	public void setTipoTransmision(String tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}
	public int getCapacidadPersonas() {
		return capacidadPersonas;
	}
	public void setCapacidadPersonas(int capacidadPersonas) {
		this.capacidadPersonas = capacidadPersonas;
	}
	public boolean isAlquilado() {
		return alquilado;
	}
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	
	//Constructor//
	public Vehiculo(Categoria categoria, String placa, String marca, String modelo, String color,
			String tipoTransmision, int capacidadPersonas, boolean alquilado) {
		this.categoria = categoria;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoTransmision = tipoTransmision;
		this.capacidadPersonas = capacidadPersonas;
		this.alquilado = alquilado;
	}
	
	
	

}
