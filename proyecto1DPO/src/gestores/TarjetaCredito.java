package gestores;

import java.time.LocalDate;

public class TarjetaCredito {
	private int numeroReserva;
	private String numeroTarjeta;
	private String tipo;
	private LocalDate fechaVencimiento;
	private double monto;
	
	public TarjetaCredito(int numeroReserva, String numeroTarjeta, String tipo, LocalDate fechaVencimiento,
			double monto) {
		this.numeroReserva = numeroReserva;
		this.numeroTarjeta = numeroTarjeta;
		this.tipo = tipo;
		this.fechaVencimiento = fechaVencimiento;
		this.monto = monto;
	}

	public int getNumeroReserva() {
		return numeroReserva;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}


	public double getMonto() {
		return monto;
	}

	

	
	
}