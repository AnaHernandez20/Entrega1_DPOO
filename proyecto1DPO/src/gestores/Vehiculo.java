package gestores;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String tipoTransmision;
    private String categoria;
    private String estaAlquilado;
    private String disponibilidad;
    private String clienteQueLoTiene;
    private String sedeDevolucion;
    private String fechaDevolucion;
    private String sedeUbicacion;
    private String novedad;
    private String fechaEstimadaDeRegreso;

    public Vehiculo(String placa, String marca, String modelo, String color, String tipoTransmision, String categoria, String estaAlquilado, String disponibilidad, String clienteQueLoTiene, String sedeDevolucion, String fechaDevolucion,String novedad, String sedeUbicacion, String fechaEstimadaDeRegreso) {
        // Constructor
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoTransmision = tipoTransmision;
        this.categoria = categoria;
        this.estaAlquilado = estaAlquilado;
        this.disponibilidad = disponibilidad;
        this.clienteQueLoTiene = clienteQueLoTiene;
        this.sedeDevolucion = sedeDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.novedad = novedad;
        this.sedeUbicacion = sedeUbicacion;
        this.fechaEstimadaDeRegreso = fechaEstimadaDeRegreso;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstaAlquilado() {
        return estaAlquilado;
    }

    public void setEstaAlquilado(String estaAlquilado) {
        this.estaAlquilado = estaAlquilado;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getClienteQueLoTiene() {
        return clienteQueLoTiene;
    }

    public void setClienteQueLoTiene(String clienteQueLoTiene) {
        this.clienteQueLoTiene = clienteQueLoTiene;
    }

    public String getSedeDevolucion() {
        return sedeDevolucion;
    }

    public void setSedeDevolucion(String sedeDevolucion) {
        this.sedeDevolucion = sedeDevolucion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getSedeUbicacion() {
        return sedeUbicacion;
    }

    public void setSedeUbicacion(String sedeUbicacion) {
        this.sedeUbicacion = sedeUbicacion;
    }

    public String getNovedad() {
        return novedad;
    }

    public void setNovedad(String novedad) {
        this.novedad = novedad;
    }

    public String getFechaEstimadaDeRegreso() {
        return fechaEstimadaDeRegreso;
    }

    public void setFechaEstimadaDeRegreso(String fechaEstimadaDeRegreso) {
        this.fechaEstimadaDeRegreso = fechaEstimadaDeRegreso;
    }
}

