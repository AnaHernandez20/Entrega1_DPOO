package gestores;

public class Tarifa {
    private String categoria;
    private double precioTemporadaAlta;
    private double precioTemporadaBaja;

    public Tarifa(String categoria, double precioTemporadaAlta, double precioTemporadaBaja) {
        this.categoria = categoria;
        this.precioTemporadaAlta = precioTemporadaAlta;
        this.precioTemporadaBaja = precioTemporadaBaja;
    }

    // Puedes agregar getters y setters si los necesitas.
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioTemporadaAlta() {
        return precioTemporadaAlta;
    }

    public void setPrecioTemporadaAlta(double precioTemporadaAlta) {
        this.precioTemporadaAlta = precioTemporadaAlta;
    }

    public double getPrecioTemporadaBaja() {
        return precioTemporadaBaja;
    }

    public void setPrecioTemporadaBaja(double precioTemporadaBaja) {
        this.precioTemporadaBaja = precioTemporadaBaja;
    }
    
    // Resto del código de la clase...
}

