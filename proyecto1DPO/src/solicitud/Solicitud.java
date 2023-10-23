package solicitud;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Solicitud {
    public String textoSolicitud; //texto de la descripcion de la solicitud
    public String tipoUsuario; //tipo de usuario que hace la solitud
    public String nombreusuario;
    public List<String> respuesta; // Lista de pasos para resolver la solicitud
    private Map<String, String> parametrosSolicitud; //atributo 

    public Solicitud(String textoSolicitud, String tipoUsuario,String nombreusuario) {
        this.textoSolicitud = textoSolicitud;
        this.tipoUsuario = tipoUsuario;
        this.nombreusuario = nombreusuario;
        this.respuesta = new ArrayList<String>();
        this.parametrosSolicitud = new HashMap<String,String>();
        
    }
    
    public String getNombreUsuario() {
    	return nombreusuario;
    }
    public void setNombreUsuario(String nombreusuario) {
    	this.nombreusuario = nombreusuario;
    }

    public String getTextoSolicitud() {
        return textoSolicitud;
    }

    public void setTextoSolicitud(String textoSolicitud) {
        this.textoSolicitud = textoSolicitud;
    }
    public void setParametrosSolicitud(Map<String, String> parametrosSolicitud) {
    	this.parametrosSolicitud = parametrosSolicitud;
    }
    public Map<String, String> getParametrosSolicitud() {
    	return parametrosSolicitud;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<String> getPasosResolucion() {
        return respuesta;
    }

    public void setPasosResolucion(List<String> respuesta) {
        this.respuesta = respuesta;
    }
}

