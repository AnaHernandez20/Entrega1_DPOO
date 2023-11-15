package Programa;

import java.time.LocalDate;

import Usuarios.Cliente;

public class VehiculoAlquilado extends Vehiculo{
	
	private Cliente clientePoseedor;
	private LocalDate fechaDevolucion;
	private Sede sedeDevolucion;

	public VehiculoAlquilado(Categoria categoria, String placa, String marca, String modelo, String color,
			String tipoTransmision, int capacidadPersonas, boolean alquilado, Cliente clientePoseedor, LocalDate fechaDevolucion,
			Sede sedeDevolucion) {
		super(categoria, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, alquilado);
		this.clientePoseedor = clientePoseedor;
		this.fechaDevolucion = fechaDevolucion;
		this.sedeDevolucion = sedeDevolucion;
	}
	
	//Getters and Setters//

	public Cliente getClientePoseedor() {
		return clientePoseedor;
	}

	public void setClientePoseedor(Cliente clientePoseedor) {
		this.clientePoseedor = clientePoseedor;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Sede getSedeDevolucion() {
		return sedeDevolucion;
	}

	public void setSedeDevolucion(Sede sedeDevolucion) {
		this.sedeDevolucion = sedeDevolucion;
	}
	

}
