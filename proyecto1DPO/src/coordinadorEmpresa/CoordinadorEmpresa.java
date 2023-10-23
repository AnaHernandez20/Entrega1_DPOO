package coordinadorEmpresa;
import gestores.Vehiculo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import solicitud.Solicitud;
import gestores.GestorAlquiler;
import gestores.GestorInventario;
import gestores.GestorSede;

public class CoordinadorEmpresa {
    private Map<String, List<Solicitud>> gestores; //atributo 
    private GestorInventario gestorInventario;
    private GestorAlquiler gestorAlquiler;
    
    private GestorSede gestorSede;

    public CoordinadorEmpresa() {
    	this.gestorAlquiler = new GestorAlquiler();
    	this.gestorInventario = new GestorInventario();
    	this.gestorSede = new GestorSede();

    	gestores = new HashMap<>(); // mapa de gestores 
        gestores.put("gestorInventario", new ArrayList<Solicitud>()); //llave gestor inventario ; valor lista de solicitudes que resuelve el gestor
        gestores.put("gestorSedes", new ArrayList<Solicitud>());
        gestores.put("gestorAlquiler", new ArrayList<Solicitud>());
        
        
        //asignacion de solicitudes para solicitudes hechas por el administrador global
        llenarSolicitudes("gestorInventario", new Solicitud("Registrar nuevo vehiculo","administradorGlobal","administradorGlobal" ));
        llenarSolicitudes("gestorInventario", new Solicitud("Dar baja a un vehiculo","administradorGlobal","administradorGlobal"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Agregar seguro","administradorGlobal","administradorGlobal"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Borrar seguro","administradorGlobal","administradorGlobal"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Modificar seguro","administradorGlobal","administradorGlobal"));
        
        llenarSolicitudes("gestorAlquiler", new Solicitud("Definir tarifa diaria para una categoria","administradorGlobal","administradorGlobal"));
        llenarSolicitudes("gestorSedes", new Solicitud("Agregar sede","administradorGlobal","administradorGlobal"));
        llenarSolicitudes("gestorSedes", new Solicitud("Borrar sede","administradorGlobal","administradorGlobal"));
        
        //asignacion de solicitudes para solicitudes hechas por el administrador local
        llenarSolicitudes("gestorSedes", new Solicitud("Registrar empleado","administradorLocal","administradorLocal"));
        llenarSolicitudes("gestorSedes", new Solicitud("Gestionar información de un empleado","administradorLocal","administradorLocal"));
        llenarSolicitudes("gestorSedes", new Solicitud("Retirar empleado","administradorLocal","administradorLocal"));
        llenarSolicitudes("gestorSedes", new Solicitud("Registrar usuario","administradorLocal","administradorLocal"));
        
        //asignación de solicitudes para solicitudes hechas por un empleadoEntregas
        llenarSolicitudes("gestorAlquiler", new Solicitud("Registrar conductor adicional","empleadoEntregas","empleadoEntregas"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Registrar entrega vehiculo","empleadoEntregas","empleadoEntregas"));
        
        //asignaciónde solicitudes para solicitudes hechas por un empleadoRevision
        llenarSolicitudes("gestorAlquiler", new Solicitud("Registrar devolucion","empleadoRevision","empleadoRevision"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Registrar disponibilidad","empleadoRevision","empleadoRevision"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Registrar fecha estimada de regreso","empleadoRevision","empleadoRevision"));
        
        //asignación de solicitudes para solicitudes hechas por un cliente
        llenarSolicitudes("gestorAlquiler", new Solicitud("Reservar vehiculo","empleadoRevision","cliente"));
        llenarSolicitudes("gestorAlquiler", new Solicitud("Alquilar vehiculo","empleadoRevision","cliente"));
        

    }

    public void coordinarSolicitud(Solicitud solicitud) {
        // Determinar a qué gestor asignar la solicitud
        String gestorAsignado = determinarGestor(solicitud);
        
        // enviar la solicitud al gestor asignado
        
        if (gestorAsignado != null) {
            if(gestorAsignado.equals("gestorInventario")) {
            	gestorInventario.procesarSolicitud(solicitud);
            	
         
            }else if(gestorAsignado.equals("gestorAlquiler")) {
            	gestorAlquiler.procesarSolicitud(solicitud);
            	
            }else if(gestorAsignado.equals("gestorSedes")) {
            	gestorSede.procesarSolicitud(solicitud);
            }
        }
    }

    private String determinarGestor(Solicitud solicitud) {
        for (Map.Entry<String, List<Solicitud>> entry : gestores.entrySet()) { //iterar cada pareja llave valor del mapa gestores ej: lalve gestorInventario ; valor lista de solicitudes que resuelve ese gestor
            for(Solicitud cadasolicitud : entry.getValue()) {      //iterar sobre cada lista de solicitudes de cada gestor
            	if (cadasolicitud.getTextoSolicitud().equals(solicitud.getTextoSolicitud())){  // si el texto de la solicitud es igual al texto de la solicitud hecha por el usuario
            	  String gestorasignado = entry.getKey(); //devolver la llave (gestor) a la que pertenece 
            	  return gestorasignado;
            		
            	 }
            }
        }
		return null; //si no se encuentra un gestor
    }
    
    

    
    
    public Map<String, List<Solicitud>> getGestores() {
        return gestores;
    }
    
    

    public void llenarSolicitudes(String gestor, Solicitud solicitud) {
        List<Solicitud> listaSolicitudes = gestores.get(gestor);

        if (listaSolicitudes != null) {
            listaSolicitudes.add(solicitud);
        }
    }
    
    public List<String> darNombresCategorias(){
    	return gestorInventario.getnombreCategorias();
    }
    
    public List<String> darSedesRegistradas(){
    	return gestorSede.getsedesregistradas();
    }
    
    
    
}