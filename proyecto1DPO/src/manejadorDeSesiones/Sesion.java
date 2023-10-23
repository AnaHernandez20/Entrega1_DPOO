package manejadorDeSesiones;

import java.util.HashMap;
import java.util.Map;

public class Sesion {
    private Map<String, String> datosSesion;

    public Sesion(String nombreUsuario, String contrasena,String tipoUsuario) {
        datosSesion = new HashMap<>();
        setNombreDeUsuario(nombreUsuario);
        setContrasena(contrasena);
        setTipoUsuario(tipoUsuario);
    }

    public void setNombreDeUsuario(String nombreUsuario) {
        datosSesion.put("nombreDeUsuario", nombreUsuario);
    }

    public String getNombreDeUsuario() {
        return datosSesion.get("nombreDeUsuario");
    }

    public void setContrasena(String contrasena) {
        datosSesion.put("contrasena", contrasena);
    }

    public String getContrasena() {
        return datosSesion.get("contrasena");
    }
    public void setTipoUsuario(String tipoUsuario) {
    	datosSesion.put("tipoUsuario", tipoUsuario);
    }
    public String getTipoUsuario() {
    	return datosSesion.get("tipoUsuario");
    }
}
