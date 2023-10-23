package gestores;

import interfazUsuario.InterfazUsuario;
import solicitud.Solicitud;

public abstract class Gestor {
	

	public void terminarSolicitud(Solicitud solicitud) {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.mostrarTerminacionDeSolicitud(solicitud);
    }

    public void pedirParametrosAInterfaz(Solicitud solicitud) {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.pedirParametrosaUsuario(solicitud);
    }
}
