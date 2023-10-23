package manejadorArchivos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import solicitud.Solicitud;

public class BuscadorArchivos {
    public static Map<String, List<String>> archivos;

    public BuscadorArchivos() {
        this.archivos = new HashMap<>();
        //llave : gestorInventario ; valor ;
        archivos.put("inventarioGlobal.txt", new ArrayList<>());
        
        
        List<String> solicitudesinventarioGlobal = List.of("Registrar nuevo vehiculo", "Dar baja a un vehiculo");
        
        archivos.put("inventarioGlobal.txt", solicitudesinventarioGlobal);

        
        		
        		
        archivos.put("inventarioAlquilados.txt", new ArrayList<>());
        archivos.put("inventarioNoDisponibles", new ArrayList<>());
        archivos.put("inventarioDisponibles.txt", new ArrayList<>());
        archivos.put("sedes.txt", new ArrayList<>());
        archivos.put("tarifas.txt", new ArrayList<>());
        archivos.put("usuarios.txt", new ArrayList<>());
                
    }
    public  static String buscarArchivoPorSolicitud(Solicitud solicitud) {
    	for(Entry<String, List<String>> archivo_lista : archivos.entrySet()) {
    		if(archivo_lista.getValue().contains(solicitud.getTextoSolicitud())) {
    			return archivo_lista.getKey();
    			
    		}
    	}
    return null;	
    }
    
    
    

    public Map<String, List<String>> getArchivos() {
        return archivos;
    }

    public void setArchivos(Map<String, List<String>> archivos) {
        this.archivos = archivos;
    }

    // Otros métodos de la clase...
}

