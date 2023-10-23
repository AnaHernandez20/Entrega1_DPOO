package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import manejadorArchivos.ManejadorDeArchivos;
import solicitud.Solicitud;

public class GestorInventario extends Gestor {
	static public List<Vehiculo> vehiculos;
	private List<Vehiculo> vehiculosAlquilados;
	static public List<Vehiculo> vehiculosNoAlquiladosDisponibles;
	static public List<Vehiculo> vehiculosNoAlquiladosNoDisponibles;
	public List<String> nombreCategorias;
	private List<String> placas;

	public GestorInventario() {
		// Inicializar la lista de vehículos al construir una instancia de
		// GestorInventario
		this.vehiculos = new ArrayList<>();
		this.vehiculosAlquilados = new ArrayList<>();
		this.vehiculosNoAlquiladosDisponibles = new ArrayList<>();
		this.vehiculosNoAlquiladosNoDisponibles = new ArrayList<>();
		this.nombreCategorias = new ArrayList<>();
		this.placas = new ArrayList<>();
		cargarVehiculos(); // Cargar los vehículos inmediatamente al construir el gestor
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public List<String> getnombreCategorias() {

		return nombreCategorias;
	}

	public void cargarVehiculos() {
		try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/inventario/inventarioGlobal.txt"))) {
			String lineaGlobal;
			while ((lineaGlobal = br.readLine()) != null) {
				String[] partes = lineaGlobal.split(",");
				if (partes.length == 8) {
					String estaAlquilado = partes[6].trim();
					String disponibilidad = partes[7].trim();
					Vehiculo vehiculo = new Vehiculo(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5],
							estaAlquilado, disponibilidad, "", "", "", "", "", "");

					if ("ALQUILADO".equals(estaAlquilado)) {
						try (BufferedReader brr = new BufferedReader(
								new FileReader("src/archivos/inventario/inventarioAlquilados.txt"))) {
							String lineaAlquilados;
							while ((lineaAlquilados = brr.readLine()) != null) {
								String[] partesAlquilados = lineaAlquilados.split(",");
								if (partes[0].equals(partesAlquilados[0])) {
									vehiculo.setClienteQueLoTiene(partesAlquilados[7].trim());
									vehiculo.setSedeDevolucion(partesAlquilados[8].trim());
									vehiculo.setFechaDevolucion(partesAlquilados[9].trim());
									vehiculosAlquilados.add(vehiculo);
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (estaAlquilado.equals("NO_ALQUILADO") && disponibilidad.equals("DISPONIBLE")) {
						try (BufferedReader brr = new BufferedReader(
								new FileReader("src/archivos/inventario/inventarioDisponibles.txt"))) {
							String lineaDisponibles;
							while ((lineaDisponibles = brr.readLine()) != null) {
								String[] partesDisponibles = lineaDisponibles.split(",");
								if (partes[0].equals(partesDisponibles[0])) {
									vehiculo.setSedeUbicacion(partesDisponibles[8].trim());
									vehiculosNoAlquiladosDisponibles.add(vehiculo);
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (estaAlquilado.equals("NO_ALQUILADO") && disponibilidad.equals("NO_DISPONIBLE")) {
						try (BufferedReader brr = new BufferedReader(
								new FileReader("src/archivos/inventario/inventarioNoDisponibles"))) {
							String lineaNoDisponibles;
							while ((lineaNoDisponibles = brr.readLine()) != null) {
								String[] partesNoDisponibles = lineaNoDisponibles.split(",");
								if (partes[0].equals(partesNoDisponibles[0])) {
									vehiculo.setNovedad(partesNoDisponibles[8].trim());
									vehiculo.setSedeUbicacion(partesNoDisponibles[9].trim());
									vehiculo.setFechaEstimadaDeRegreso(partesNoDisponibles[10].trim());
									vehiculosNoAlquiladosNoDisponibles.add(vehiculo);
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					vehiculos.add(vehiculo);
					if (!nombreCategorias.contains(vehiculo.getCategoria())) {
						nombreCategorias.add(vehiculo.getCategoria());
					}
					if (!placas.contains(vehiculo.getPlaca())) {
						placas.add(vehiculo.getPlaca());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void procesarSolicitud(Solicitud solicitud) {
		solicitud.getPasosResolucion().add("Solicitud recibida por el gestor de inventario...");
		if (solicitud.getTextoSolicitud().equals("Registrar nuevo vehiculo")) {

			registrarNuevoVehiculo(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/inventario/inventarioGlobal.txt");
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/inventario/inventarioDisponibles.txt");

		}

		if (solicitud.getTextoSolicitud().equals("Dar baja a un vehiculo")) {

			darBajaVehiculo(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/inventario/inventarioGlobal.txt");
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/inventario/inventarioDisponibles.txt");
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/inventario/inventarioNoDisponibles");			

		}

		terminarSolicitud(solicitud);
	}

	public void registrarNuevoVehiculo(Solicitud solicitud) {

		// Define los parámetros necesarios en la solicitud para agregar un vehículo
		solicitud.getParametrosSolicitud().put("PLACA", null);
		solicitud.getParametrosSolicitud().put("MARCA", null);
		solicitud.getParametrosSolicitud().put("MODELO", null);
		solicitud.getParametrosSolicitud().put("COLOR", null);
		solicitud.getParametrosSolicitud().put("TIPO TRANSMISION", null);
		solicitud.getParametrosSolicitud().put("CATEGORIA", null);
		solicitud.getParametrosSolicitud().put("SEDE UBICACION", null);

		// Añade un paso para recibir los parámetros de la solicitud
		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud...");

		// Pide los parámetros a través de la interfaz
		pedirParametrosAInterfaz(solicitud);

		// ver si el vehiculo ya existe

		if (placas.contains(solicitud.getParametrosSolicitud().get("PLACA"))) {
			solicitud.getPasosResolucion().add("El vehiculo que intenta agregar ya está registrado. ");

		} else {

			// Crea un nuevo vehículo con los parámetros proporcionados
			Vehiculo nuevoCarro = new Vehiculo(solicitud.getParametrosSolicitud().get("PLACA"),
					solicitud.getParametrosSolicitud().get("MARCA"), solicitud.getParametrosSolicitud().get("MODELO"),
					solicitud.getParametrosSolicitud().get("COLOR"),
					solicitud.getParametrosSolicitud().get("TIPO TRANSMISION"),
					solicitud.getParametrosSolicitud().get("CATEGORIA"), "NO_ALQUILADO", "DISPONIBLE", null, null, null,
					null, solicitud.getParametrosSolicitud().get("SEDE UBICACION"), null);

			// Añade el vehículo al inventarioGlobal
			vehiculos.add(nuevoCarro);

			// Añade el vehículo al inventarioNoAlquiladosDisponibles
			vehiculosNoAlquiladosDisponibles.add(nuevoCarro);
			//añade placa a lsita de placas
			placas.add(nuevoCarro.getPlaca());

			// Añade pasos de resolución adicionales
			solicitud.getPasosResolucion().add("Nuevo vehículo agregado al inventario exitosamente.");
			solicitud.getPasosResolucion().add("Solicitud atendida exitosamente.");
		}

	}

	public void darBajaVehiculo(Solicitud solicitud) {

		// Define los parámetros necesarios en la solicitud para dar de baja un vehículo
		solicitud.getParametrosSolicitud().put("PLACA", null);

		// Añade un paso para recibir los parámetros de la solicitud
		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud...");

		// Pide los parámetros a través de la interfaz
		super.pedirParametrosAInterfaz(solicitud);
		
		// ver si la placa del vehiculo ya esta registrada
		if (placas.contains(solicitud.getParametrosSolicitud().get("PLACA"))) {
			// Buscar vehículo
			boolean encontrado = false;
			Vehiculo vehiculoABorrar = null;
			while (!encontrado) {
				for (Vehiculo carro : vehiculos) {
					if (carro.getPlaca().equals(solicitud.getParametrosSolicitud().get("PLACA"))) {
						vehiculoABorrar = carro;
						encontrado = true;
					}
				}
			}

			solicitud.getPasosResolucion().add("Eliminando vehículo ...");

			if (encontrado) {
				vehiculos.remove(vehiculoABorrar);
				if(vehiculosNoAlquiladosNoDisponibles.contains(vehiculoABorrar)) {

					vehiculosNoAlquiladosNoDisponibles.remove(vehiculoABorrar);
				}
				if(vehiculosNoAlquiladosDisponibles.contains(vehiculoABorrar)) {
					vehiculosNoAlquiladosDisponibles.remove(vehiculoABorrar);
				}
				placas.remove(vehiculoABorrar.getPlaca());
			} else {
				solicitud.getPasosResolucion().add("No se encontró ningún vehículo con la placa proporcionada.");
			}

			solicitud.getPasosResolucion().add("Solicitud terminada con éxito");

		} else { //si no esta registrado, no se puede borrar
			solicitud.getPasosResolucion().add("El vehiculo que intenta borrar no está registrado. ");

		}

	}

}
          



