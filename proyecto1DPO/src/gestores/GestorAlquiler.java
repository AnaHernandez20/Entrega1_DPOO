package gestores;

import interfazUsuario.InterfazUsuario;
import manejadorArchivos.ManejadorDeArchivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import solicitud.Solicitud;

public class GestorAlquiler extends Gestor {
	static public List<Seguro> seguros;
	static public List<Tarifa> tarifas;
	static public List<Reserva> reservas;
	public List<String> nombreCategorias;
	public List<String> nombreSeguros;

	public GestorAlquiler() {
		// Inicializar la lista de seguros al construir una instancia de GestorAlquiler
		this.seguros = new ArrayList<>();
		this.tarifas = new ArrayList<>();
		this.nombreCategorias = new ArrayList<>();
		this.nombreSeguros = new ArrayList<>();
		cargarSeguros();
		cargarTarifas();

	}

	public void cargarSeguros() {
		try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/alquiler/seguros.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(",");
				if (partes.length == 2) {
					// Crear un objeto de seguro con los detalles de la línea
					Seguro seguro = new Seguro(partes[0], Double.parseDouble(partes[1]));

					// Agregar el seguro a la lista de seguros
					seguros.add(seguro);
					if (!nombreSeguros.contains(seguro.getNombreSeguro())) {
						nombreSeguros.add(seguro.getNombreSeguro());
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarTarifas() {
		try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/alquiler/tarifas.txt"))) {

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(",");
				if (partes.length == 3) {
					String categoria = partes[0];
					double precioTemporadaAlta = Double.parseDouble(partes[1]);
					double precioTemporadaBaja = Double.parseDouble(partes[2]);
					Tarifa tarifa = new Tarifa(categoria, precioTemporadaAlta, precioTemporadaBaja);
					tarifas.add(tarifa);
					if (!nombreCategorias.contains(tarifa.getCategoria())) {
						nombreCategorias.add(tarifa.getCategoria());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void procesarSolicitud(Solicitud solicitud) {

		solicitud.getPasosResolucion().add("Solicitud recibida por el gestor de alquiler...");
		if (solicitud.getTextoSolicitud().equals("Agregar seguro")) {

			agregarSeguro(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/alquiler/seguros.txt");

		}
		
		if (solicitud.getTextoSolicitud().equals("Borrar seguro")) {
			borrarSeguro(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/alquiler/seguros.txt");


		}
		if (solicitud.getTextoSolicitud().equals("Definir tarifa diaria para una categoria")) {

			definirTarifaCategoria(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/alquiler/tarifas.txt");

			

		}
		if (solicitud.getTextoSolicitud().equals("Modificar seguro")) {
			modificarSeguro(solicitud);
			ManejadorDeArchivos.escribirListaEnArchivo("src/archivos/alquiler/seguros.txt");

		}
		
		if(solicitud.getTextoSolicitud().equals("Reservar vehiculo")) {
			reservarVehiculo(solicitud);
			
		}
		super.terminarSolicitud(solicitud);
	}
	
	
	public void reservarVehiculo(Solicitud solicitud) {
		solicitud.getParametrosSolicitud().put("CATEGORIA REQUERIDA", null);
		solicitud.getParametrosSolicitud().put("SEDE DONDE SE VA A RECOGER", null);
		solicitud.getParametrosSolicitud().put("FECHA EN LA QUE SE VA A RECOGER (formato dd/MM/yyyy HH:mm)", null);
		solicitud.getParametrosSolicitud().put("SEDE EN LA QUE SE VA A ENTREGAR", null);
		solicitud.getParametrosSolicitud().put("FECHA EN LA QUE SE VA A ENTREGAR (formato dd/MM/yyyy HH:mm)", null);
		solicitud.getParametrosSolicitud().put("¿DESEA ADQUIRIR SEGUROS ADICIONALES? (S/N)", null);
		
		
		super.pedirParametrosAInterfaz(solicitud);
		System.out.println(solicitud.getParametrosSolicitud().get("CATEGORIA REQUERIDA"));
		boolean categoriaDisponible = verificarDisponibilidad(solicitud.getParametrosSolicitud().get("CATEGORIA REQUERIDA"));
		if(!categoriaDisponible) {
			solicitud.getPasosResolucion().add("No tenemos vehiculos para alquilar en la categoria ingresada ...");

		}
		else {
			double totalproyectado = 0 ;
			double excedendeporsedesdistintas = 0;
			double totalSeguros = 0;
			double tarifacategoria = calcularTarifaCategoria(solicitud);
			double tarifaDiariaPorNumeroDeDias = calculartarifaDiariaPorNumeroDeDias(solicitud,tarifacategoria);
			if(solicitud.getParametrosSolicitud().get("SEDE DONDE SE VA A RECOGER") !=  solicitud.getParametrosSolicitud().get("SEDE DONDE SE VA A ENTREGAR")) {
				excedendeporsedesdistintas= 50000;	
			}
			
			if(solicitud.getParametrosSolicitud().get("¿DESEA ADQUIRIR SEGUROS ADICIONALES? (S/N)").equals("S")) {
				
				List<Seguro> segurosadquiridos = new ArrayList<Seguro>();
				Scanner scaner = new Scanner(System.in);
				for(Seguro seguro:seguros) {
					System.out.println("¿Dese adquirir el seguro "+ seguro.getNombreSeguro()+"?  (S/N)");
					
						String desicion = scaner.nextLine();
						if(desicion.equals("S")) {
							segurosadquiridos.add(seguro);
						}
					
					

				}
				for(Seguro seguroanadido : segurosadquiridos) {
					double costodelseguro = calculartarifaDiariaPorNumeroDeDias(solicitud,seguroanadido.getPrecioSeguro());
					totalSeguros+= costodelseguro;
				}
			}
			
			totalproyectado = tarifaDiariaPorNumeroDeDias + excedendeporsedesdistintas + totalSeguros;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			try {
				Reserva reserva = new Reserva(
						solicitud.getNombreUsuario(),
					    solicitud.getParametrosSolicitud().get("CATEGORIA REQUERIDA"),
					    solicitud.getParametrosSolicitud().get("SEDE DONDE SE VA A RECOGER"),
					    dateFormat.parse(solicitud.getParametrosSolicitud().get("FECHA EN LA QUE SE VA A RECOGER (formato dd/MM/yyyy HH:mm)")),
					    solicitud.getParametrosSolicitud().get("SEDE EN LA QUE SE VA A ENTREGAR"),
					    dateFormat.parse(solicitud.getParametrosSolicitud().get("FECHA EN LA QUE SE VA A ENTREGAR (formato dd/MM/yyyy HH:mm)")),
					    totalproyectado, 
					    totalproyectado * 0.7
					);
				reservas.add(reserva);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
	}

	public void agregarSeguro(Solicitud solicitud) {

		// PARAMETROS NECESARIOS EN LA SOLICITUD PARA AGREGAR UN CARRO
		solicitud.getParametrosSolicitud().put("NOMBRE SEGURO", null);
		solicitud.getParametrosSolicitud().put("VALOR SEGURO", null);
		// pedir a a la interfaz que pida los parametros necesarios
		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud... ");
		pedirParametrosAInterfaz(solicitud);
		if (!nombreSeguros.contains(solicitud.getParametrosSolicitud().get("NOMBRE SEGURO"))) {
			solicitud.getPasosResolucion().add("Añadiendo seguro...");
			Seguro nuevoseguro = new Seguro(solicitud.getParametrosSolicitud().get("NOMBRE SEGURO"),
					Double.parseDouble(solicitud.getParametrosSolicitud().get("VALOR SEGURO")));
			seguros.add(nuevoseguro);
			nombreSeguros.add(nuevoseguro.getNombreSeguro());
			solicitud.getPasosResolucion().add("Solicitud atentida exitosamente ...");
		} else {
			solicitud.getPasosResolucion().add("El seguro que trata de agregar ya existe ...");

		}

	}

	public void borrarSeguro(Solicitud solicitud) {
		// PARAMETROS NECESARIOS EN LA SOLICITUD PARA BORRAR UN SEGURO
		solicitud.getParametrosSolicitud().put("NOMBRE SEGURO", null);
		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud... ");
		super.pedirParametrosAInterfaz(solicitud);
		if (!nombreSeguros.contains(solicitud.getParametrosSolicitud().get("NOMBRE SEGURO"))) {

			solicitud.getPasosResolucion()
					.add("El seguro que intenta agregar no existe en el sistema, primero debe agregarlo ...");
			solicitud.getPasosResolucion().add("Solicitud atentida exitosamente ...");
		} else {
			// buscar seguro
			boolean encontrado = false;
			Seguro seguroABorrar = null;
			while (!encontrado) {
				for (Seguro segur : seguros) {
					if (segur.getNombreSeguro().equals(solicitud.getParametrosSolicitud().get("NOMBRE SEGURO"))) {
						seguroABorrar = segur;
						encontrado = true;
					}

				}
			}

			solicitud.getPasosResolucion().add("Eliminando seguro ...");
			if (seguros.contains(seguroABorrar)) {
				seguros.remove(seguroABorrar);

			}
			if(nombreSeguros.contains(seguroABorrar.getNombreSeguro())) {
				nombreSeguros.remove(seguroABorrar.getNombreSeguro());
			}
			if(nombreSeguros.contains(seguroABorrar.getNombreSeguro())) {
				nombreSeguros.remove(seguroABorrar.getNombreSeguro());
			}
			
			

			solicitud.getPasosResolucion().add("Solicitud atentida exitosamente ...");
		}
	}
	
	public double calculartarifaDiariaPorNumeroDeDias(Solicitud solicitud,double tarifaCategoria) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaInicio = null;
        Date fechaEntrega = null;

        try {
            fechaInicio = dateFormat.parse(solicitud.getParametrosSolicitud().get("FECHA EN LA QUE SE VA A RECOGER (formato dd/MM/yyyy HH:mm)"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            fechaEntrega = dateFormat.parse(solicitud.getParametrosSolicitud().get("FECHA EN LA QUE SE VA A ENTREGAR (formato dd/MM/yyyy HH:mm)"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Calcular el número de milisegundos entre fechaInicio y fechaEntrega
        long diferenciaTiempo = fechaEntrega.getTime() - fechaInicio.getTime();

        // Convertir la diferencia de tiempo a días
        int diasDiferencia = (int) (diferenciaTiempo / (1000 * 60 * 60 * 24));

        // Calcular la tarifa multiplicando por el número de días
        double tarifaTotal = tarifaCategoria * diasDiferencia;

        return tarifaTotal;
    }
		
		
	
	
	
 	public double calcularTarifaCategoria(Solicitud solicitud) {
		String categoriadeseada = solicitud.getParametrosSolicitud().get("CATEGORIA REQUERIDA");
		double tarifadeseada = 0;
		for(Tarifa tarifa : tarifas ) {
			if(tarifa.getCategoria().equals(categoriadeseada)) {
				tarifadeseada = tarifa.getPrecioTemporadaAlta();
			}
			
		}
		return tarifadeseada;
	}
	
	
	public boolean verificarDisponibilidad(String categoriarequerida) {
		for(Vehiculo carro : GestorInventario.vehiculosNoAlquiladosDisponibles) {
			if(carro.getCategoria().equals(categoriarequerida)) {
				return true;
			}
		}
		return false;
	}
	public void modificarSeguro(Solicitud solicitud) {
		solicitud.getParametrosSolicitud().put("NOMBRE SEGURO", null);
		solicitud.getParametrosSolicitud().put("NUEVO VALOR DEL SEGURO", null);

		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud... ");

		super.pedirParametrosAInterfaz(solicitud);
		if (!nombreSeguros.contains(solicitud.getParametrosSolicitud().get("NOMBRE SEGURO"))) {
			solicitud.getPasosResolucion().add("El seguro que intenta agregar no existe en el sistema, primero debe agregarlo ...");
		} else {
			// buscar seguro
			boolean encontrado = false;
			Seguro seguroAModificar = null;
			while (!encontrado) {
				for (Seguro segur : seguros) {
					if (segur.getNombreSeguro().equals(solicitud.getParametrosSolicitud().get("NOMBRE SEGURO"))) {
						seguroAModificar = segur;
						encontrado = true;
					}

				}
			}
			solicitud.getPasosResolucion().add("Modificando seguro ...");
			seguroAModificar.setPrecioSeguro(Double.parseDouble(solicitud.getParametrosSolicitud().get("NUEVO VALOR DEL SEGURO")));
			solicitud.getPasosResolucion().add("Solicitud atentida exitosamente ...");

			

		}

	}

	public void definirTarifaCategoria(Solicitud solicitud) {
		
		// PARAMETROS NECESARIOS EN LA SOLICITUD PARA definir la tarifa de una categoria

		solicitud.getParametrosSolicitud().put("CATEGORIA", null);
		solicitud.getParametrosSolicitud().put("PRECIO TEMPORADA ALTA", null);
		solicitud.getParametrosSolicitud().put("PRECIO TEMPORADA BAJA", null);

		solicitud.getPasosResolucion().add("Recibiendo parámetros de la solicitud... ");
		super.pedirParametrosAInterfaz(solicitud);
		solicitud.getPasosResolucion().add("Añadiendo tarifa ...");

		// crear tarifa
		Tarifa tarifa = new Tarifa(solicitud.getParametrosSolicitud().get("CATEGORIA"),
				Double.parseDouble(solicitud.getParametrosSolicitud().get("PRECIO TEMPORADA ALTA")),
				Double.parseDouble(solicitud.getParametrosSolicitud().get("PRECIO TEMPORADA BAJA")));

		nombreCategorias.add(tarifa.getCategoria());
		tarifas.add(tarifa);
		
		solicitud.getPasosResolucion().add("Solicitud atentida exitosamente ...");

	}
	
	

	public List<Seguro> getSeguros() {
		return seguros;
	}

	public List<Tarifa> getTarifas() {
		return tarifas;
	}

	public List<String> getNombreCategorias() {
		return nombreCategorias;
	}
}

