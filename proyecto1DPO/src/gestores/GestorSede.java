package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manejadorArchivos.ManejadorDeArchivos;
import solicitud.Solicitud;

public class GestorSede extends Gestor {

	static public List<Sede> sedes;
	public List<String> sedesregistradas;

	public GestorSede() {
		// Inicializar la lista de vehículos al construir una instancia de
		// GestorInventario
		this.sedes = new ArrayList<>();
		this.sedesregistradas = new ArrayList<>();

		cargarSedes(); // Cargar las sedes inmediatamente al construir el gestor
	}

	public List<Sede> getSedes() {
		return sedes;
	}

	public List<String> getsedesregistradas() {
		return sedesregistradas;
	}

	public void cargarSedes() {
		try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/sedes/sedes.txt"))) {
			String lineaGlobal;
			while ((lineaGlobal = br.readLine()) != null) {
				String[] partes = lineaGlobal.split(",");
				if (partes.length == 11) {
					Sede sede = new Sede(partes[0], partes[1], partes[2], partes[3], Boolean.parseBoolean(partes[4]),
							Boolean.parseBoolean(partes[5]), Boolean.parseBoolean(partes[6]),
							Boolean.parseBoolean(partes[7]), Boolean.parseBoolean(partes[8]),
							Boolean.parseBoolean(partes[9]), Boolean.parseBoolean(partes[10]));
					if (!sedesregistradas.contains(sede.getNombre())) {
						sedesregistradas.add(sede.getNombre());
					}
					sedes.add(sede);

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void procesarSolicitud(Solicitud solicitud) {
		solicitud.getPasosResolucion().add("Solicitud recibida por el gestor de sedes...");

		if (solicitud.getTextoSolicitud().equals("Agregar sede")) {
			
			agregarSede(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/sedes/sedes.txt");
		}

		else if (solicitud.getTextoSolicitud().equals("Borrar sede")) {

			borrarSede(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/sedes/sedes.txt");

		}

		super.terminarSolicitud(solicitud);
	}

	public void agregarSede(Solicitud solicitud) {

		// PARAMETROS NECESARIOS EN LA SOLICITUD PARA AGREGAR UNA SEDE
		solicitud.getParametrosSolicitud().put("NOMBRE", null);
		solicitud.getParametrosSolicitud().put("UBICACION", null);
		solicitud.getParametrosSolicitud().put("HINICIO", null);
		solicitud.getParametrosSolicitud().put("HFINAL", null);
		solicitud.getParametrosSolicitud().put("ABRELUNES", null);
		solicitud.getParametrosSolicitud().put("ABREMARTES", null);
		solicitud.getParametrosSolicitud().put("ABREMIERCOLES", null);
		solicitud.getParametrosSolicitud().put("ABREJUEVES", null);
		solicitud.getParametrosSolicitud().put("ABREVIERNES", null);
		solicitud.getParametrosSolicitud().put("ABRESABADO", null);
		solicitud.getParametrosSolicitud().put("ABREDOMINGO", null);

		// pedir a a la interfaz que pida los parametros necesarios
		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud... ");

		super.pedirParametrosAInterfaz(solicitud);

		// crear sedes
		Sede nuevaSede = new Sede(solicitud.getParametrosSolicitud().get("NOMBRE"),
				solicitud.getParametrosSolicitud().get("UBICACION"), solicitud.getParametrosSolicitud().get("HINICIO"),
				solicitud.getParametrosSolicitud().get("HFINAL"),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABRELUNES")),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABREMARTES")),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABREMIERCOLES")),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABREJUEVES")),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABREVIERNES")),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABRESABADO")),
				Boolean.parseBoolean(solicitud.getParametrosSolicitud().get("ABREDOMINGO")));
		solicitud.getPasosResolucion().add("Añadiendo sede a sedes...");
		sedes.add(nuevaSede);
		if (!sedesregistradas.contains(nuevaSede.getNombre())) {
			sedesregistradas.add(nuevaSede.getNombre());

		}
		
		for(Sede sede : sedes) {
			System.out.println(sede.getNombre());
		}
		solicitud.getPasosResolucion().add("Nueva sede agregada al inventario exitosamente ...");
		solicitud.getPasosResolucion().add("Solicitud atentida exitosamente ...");

	}

	public void borrarSede(Solicitud solicitud) {
		solicitud.getPasosResolucion().add("Solicitud recibida por el gestor de inventario...");
		// PARAMETROS NECESARIOS EN LA SOLICITUD PARA ELIMINAR UNA SEDE
		solicitud.getParametrosSolicitud().put("NOMBRE SEDE", null);
		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud... ");
		super.pedirParametrosAInterfaz(solicitud);

		// buscar sede

		if (sedesregistradas.contains(solicitud.getParametrosSolicitud().get("NOMBRE SEDE"))) {

			Sede sedeABorrar = null;
			while (sedeABorrar == null) {
				for (Sede sedecita : sedes) {
					if (sedecita.getNombre().equals(solicitud.getParametrosSolicitud().get("NOMBRE SEDE"))) {
						sedeABorrar = sedecita;
					}

				}

			}
			if (sedes.contains(sedeABorrar)) {
				sedes.remove(sedeABorrar);
			}

		}else {
			solicitud.getPasosResolucion().add("La sede que intenta agregar no está registrada ...");

		}
	}

}