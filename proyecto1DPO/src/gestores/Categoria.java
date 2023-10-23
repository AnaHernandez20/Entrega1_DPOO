package gestores;

import java.util.List;

public class Categoria {
	String nombre;
	double precioTarifaDiaria;
	List<Vehiculo> vehiculos;
	
	public Categoria(String nombre, double precioTarifaDiaria, List<Vehiculo> vehiculos) {
		this.nombre = nombre;
		this.precioTarifaDiaria = precioTarifaDiaria;
		this.vehiculos = vehiculos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioTarifaDiaria() {
		return precioTarifaDiaria;
	}

	public void setPrecioTarifaDiaria(double precioTarifaDiaria) {
		this.precioTarifaDiaria = precioTarifaDiaria;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	
	
	

}
