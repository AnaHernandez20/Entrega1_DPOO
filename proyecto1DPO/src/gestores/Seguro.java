package gestores;

public class Seguro {
    private String nombreSeguro;
    private double precioSeguro;

    public Seguro(String nombreSeguro, double precioSeguro) {
        this.nombreSeguro = nombreSeguro;
        this.precioSeguro = precioSeguro;
    }

    public String getNombreSeguro() {
        return nombreSeguro;
    }

    public void setNombreSeguro(String nombreSeguro) {
        this.nombreSeguro = nombreSeguro;
    }

    public double getPrecioSeguro() {
        return precioSeguro;
    }

    public void setPrecioSeguro(double precioSeguro) {
        this.precioSeguro = precioSeguro;
    }

    @Override
    public String toString() {
        return "Seguro [nombreSeguro=" + nombreSeguro + ", precioSeguro=" + precioSeguro + "]";
    }
}
