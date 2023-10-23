package gestores;

public class Sede {
	private String nombre;
    private String ubicacion;
    private String horaInicialAtencion;
    private String horaFinalAtencion;
    private boolean abreLunes;
    private boolean abreMartes;
    private boolean abreMiercoles;
    private boolean abreJueves;
    private boolean abreViernes;
    private boolean abreSabado;
    private boolean abreDomingo;
    
    
public Sede(String nombre, String ubicacion, String horaInicialAtencion, String horaFinalAtencion, boolean abreLunes, boolean abreMartes, boolean abreMiercoles, boolean abreJueves, boolean abreViernes, boolean abreSabado, boolean abreDomingo) {
	this.nombre = nombre;
	this.ubicacion = ubicacion;
	this.horaInicialAtencion = horaInicialAtencion;
	this.horaFinalAtencion = horaFinalAtencion;
	this.abreLunes = abreLunes;
	this.abreMartes = abreMartes;
	this.abreMiercoles = abreMiercoles;
	this.abreJueves = abreJueves;
	this.abreViernes = abreViernes;
	this.abreSabado = abreSabado;
	this.abreDomingo = abreDomingo;
}

public String getNombre() {
    return nombre;
}

public String getUbicacion() {
    return ubicacion;
}

public String getHoraInicialAtencion() {
    return horaInicialAtencion;
}

public String getHoraFinalAtencion() {
    return horaFinalAtencion;
}

public boolean getAbreLunes() {
    return abreLunes;
}

public boolean getAbreMartes() {
    return abreMartes;
}

public boolean getAbreMiercoles() {
    return abreMiercoles;
}

public boolean getAbreJueves() {
    return abreJueves;
}

public boolean getAbreViernes() {
    return abreViernes;
}

public boolean getAbreSabado() {
    return abreSabado;
}

public boolean getAbreDomingo() {
    return abreDomingo;
}


}
