package gestores;

import java.util.Date;

public class Reserva {
	private String cliente;
    private String tipoCarro;
    private String sedeRecogida;
    private Date fechaHoraRecogida;
    private String sedeEntrega;
    private Date fechaHoraEntrega;
    private double costoProyectado;
    private double montoPagado;
    private TarjetaCredito tarjetaCredito;
    private LicenciaConductor licenciaConductor;

    public Reserva(String  cliente,String tipoCarro, String sedeRecogida, Date fechaHoraRecogida, String sedeEntrega, Date fechaHoraEntrega, double costoProyectado, double montoPagado) {
        this.cliente =  cliente;
    	this.tipoCarro = tipoCarro;
        this.sedeRecogida = sedeRecogida;
        this.fechaHoraRecogida = fechaHoraRecogida;
        this.sedeEntrega = sedeEntrega;
        this.fechaHoraEntrega = fechaHoraEntrega;
        this.costoProyectado = costoProyectado;
        this.montoPagado = montoPagado;
    }

    // Getters y setters
    public String getCliente() {
    	return cliente;
    }
    public void setCliente(String cliente) {
    	this.cliente =cliente;
    }
    public String getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(String tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public String getSedeRecogida() {
        return sedeRecogida;
    }

    public void setSedeRecogida(String sedeRecogida) {
        this.sedeRecogida = sedeRecogida;
    }

    public Date getFechaHoraRecogida() {
        return fechaHoraRecogida;
    }

    public void setFechaHoraRecogida(Date fechaHoraRecogida) {
        this.fechaHoraRecogida = fechaHoraRecogida;
    }

    public String getSedeEntrega() {
        return sedeEntrega;
    }

    public void setSedeEntrega(String sedeEntrega) {
        this.sedeEntrega = sedeEntrega;
    }

    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public double getCostoProyectado() {
        return costoProyectado;
    }

    public void setCostoProyectado(double costoProyectado) {
        this.costoProyectado = costoProyectado;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }
}
