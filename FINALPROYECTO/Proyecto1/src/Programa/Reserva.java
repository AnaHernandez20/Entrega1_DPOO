package Programa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Usuarios.Cliente;

public class Reserva {
	
	//Atributos//
	
	private Categoria categoria;
	private Vehiculo vehiculoEntregado;
	private Cliente cliente;
	private ArrayList<ConductorAdicional> conductoresExtra = new ArrayList<ConductorAdicional>();
	private Sede sedeReclamar;
	private Sede sedeDevolucion;
	private LocalDate fechaReclamar;
	private LocalDate fechaDevolucion;
	private LocalTime horaReclamar;
	private LocalTime horaDevolucion;
	private ArrayList<Seguro> segurosTomados = new ArrayList<Seguro>();
	private Tarifa montoEsperado;
	
	//Constructor//
	public Reserva(Categoria categoria, Vehiculo vehiculoEntregado, Cliente cliente,
			ArrayList<ConductorAdicional> conductoresExtra, Sede sedeReclamar, Sede sedeDevolucion,
			LocalDate fechaReclamar, LocalDate fechaDevolucion, LocalTime horaReclamar, LocalTime horaDevolucion,
			ArrayList<Seguro> segurosTomados, Tarifa montoEsperado) {
		this.categoria = categoria;
		this.vehiculoEntregado = vehiculoEntregado;
		this.cliente = cliente;
		this.conductoresExtra = conductoresExtra;
		this.sedeReclamar = sedeReclamar;
		this.sedeDevolucion = sedeDevolucion;
		this.fechaReclamar = fechaReclamar;
		this.fechaDevolucion = fechaDevolucion;
		this.horaReclamar = horaReclamar;
		this.horaDevolucion = horaDevolucion;
		this.segurosTomados = segurosTomados;
		this.montoEsperado = montoEsperado;
	}
	
	//Metodos//
	
	public void agregarSeguro(Seguro seguro) {
		if (segurosTomados == null) {
			this.segurosTomados = new ArrayList<Seguro>();
		}
		segurosTomados.add(seguro);
	}
	
	public void agregarConductorExtra(ConductorAdicional conductorExtra) {
		if (conductoresExtra == null) {
			this.conductoresExtra = new ArrayList<ConductorAdicional>();
		}
		conductoresExtra.add(conductorExtra);
	}

	
	//Getters and Setters//
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Vehiculo getVehiculoEntregado() {
		return vehiculoEntregado;
	}
	
	public void setVehiculoEntregado(Vehiculo vehiculoEntregado) {
		this.vehiculoEntregado = vehiculoEntregado;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<ConductorAdicional> getConductoresExtra() {
		return conductoresExtra;
	}
	
	public void setConductoresExtra(ArrayList<ConductorAdicional> conductoresExtra) {
		this.conductoresExtra = conductoresExtra;
	}
	
	public Sede getSedeReclamar() {
		return sedeReclamar;
	}
	
	public void setSedeReclamar(Sede sedeReclamar) {
		this.sedeReclamar = sedeReclamar;
	}
	
	public Sede getSedeDevolucion() {
		return sedeDevolucion;
	}
	
	public void setSedeDevolucion(Sede sedeDevolucion) {
		this.sedeDevolucion = sedeDevolucion;
	}
	
	public LocalDate getFechaReclamar() {
		return fechaReclamar;
	}
	
	public void setFechaReclamar(LocalDate fechaReclamar) {
		this.fechaReclamar = fechaReclamar;
	}
	
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	
	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	public LocalTime getHoraReclamar() {
		return horaReclamar;
	}
	
	public void setHoraReclamar(LocalTime horaReclamar) {
		this.horaReclamar = horaReclamar;
	}
	
	public LocalTime getHoraDevolucion() {
		return horaDevolucion;
	}
	
	public void setHoraDevolucion(LocalTime horaDevolucion) {
		this.horaDevolucion = horaDevolucion;
	}
	
	public ArrayList<Seguro> getSegurosTomados() {
		return segurosTomados;
	}
	
	public void setSegurosTomados(ArrayList<Seguro> segurosTomados) {
		this.segurosTomados = segurosTomados;
	}
	
	public Tarifa getMontoEsperado() {
		return montoEsperado;
	}
	
	public void setMontoEsperado(Tarifa montoEsperado) {
		this.montoEsperado = montoEsperado;
	}
	
	

}
