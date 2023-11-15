package Programa;

import java.time.LocalDate;

public class VehiculoNoAlquilado extends Vehiculo{
	
	//Se crea un enum para visualizar en que estado se encuentra el vehiculo//
	//Estos estados solo pueden ser modificados por la empresa o el administrador local//
	public enum Estado {
		DISPONIBLE,
		MANTENIMIENTO,
		LIMPIEZA
	}

	private Estado estado;
	private LocalDate fechaRegreso;
	private Sede sedeUbicacion;
	
	public VehiculoNoAlquilado(Categoria categoria, String placa, String marca, String modelo, String color,
			String tipoTransmision, int capacidadPersonas, boolean alquilado, Estado estado, LocalDate fechaRegreso,
			Sede sedeubicacion) {
		super(categoria, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, alquilado);
		this.estado = estado;
		this.fechaRegreso = fechaRegreso;
		this.sedeUbicacion = sedeubicacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public LocalDate getFechaRegreso() {
		return fechaRegreso;
	}

	public void setFechaRegreso(LocalDate fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}

	public Sede getSedeUbicacion() {
		return sedeUbicacion;
	}

	public void setSedeUbicacion(Sede sedeUbicacion) {
		this.sedeUbicacion = sedeUbicacion;
	}
	
	
	

}
