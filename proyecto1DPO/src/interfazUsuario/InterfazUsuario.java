package interfazUsuario;

import manejadorDeSesiones.ManejadorDeSesiones;
import manejadorDeSesiones.Sesion;
import gestores.GestorAlquiler;
import gestores.GestorInventario;
import solicitud.Solicitud;
import coordinadorEmpresa.CoordinadorEmpresa;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InterfazUsuario {
	private Scanner scanner;
	private ManejadorDeSesiones manejadorDeSesiones; // Agrega un campo para el manejador
	private Sesion sesion;
	private static CoordinadorEmpresa coordinadorEmpresa = new CoordinadorEmpresa();

	public InterfazUsuario() {
		this.scanner = new Scanner(System.in);
		this.manejadorDeSesiones = new ManejadorDeSesiones();
		this.sesion = null;

	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void mostrarMenuInicioSesion() {
		System.out.println("Bienvenido al sistema de alquiler de vehículos.");
		System.out.print("Por favor, ingrese su nombre de usuario: ");
		String usuario = scanner.nextLine();

		System.out.print("Ingrese su contraseña: ");
		String contrasena = scanner.nextLine();

		// Crear una sesión con los datos de usuario
		sesion = new Sesion(usuario, contrasena, null);
		setSesion(sesion);
		mostrarMenuDeSolicitudes(getSesion());
	}

	public void mostrarMenuDeSolicitudes(Sesion sesion) {
		// Enviar la sesión a la clase ManejadorDeSesiones
		String tipoDeUsuario = ManejadorDeSesiones.validarSesion(sesion);

		if ("Sesion no encontrada".equals(tipoDeUsuario)) {
			System.out.println("Sesión no encontrada. Por favor, verifique sus credenciales.");
		} else {
			sesion.setTipoUsuario(tipoDeUsuario);
			System.out.println(
					"Bienvenido, " + sesion.getNombreDeUsuario() + ". Tipo de usuario: " + sesion.getTipoUsuario());
			// Obtener los métodos del tipo de usuario que inició sesión

			List<String> metodosDisponibles = manejadorDeSesiones.obtenerMetodosDisponibles(tipoDeUsuario);

			// Mostrar métodos disponibles
			int contador = 1;
			for (String metodo : metodosDisponibles) {
				System.out.println(contador + ". " + metodo);
				contador++;
			}

			int opcion;
			opcion = scanner.nextInt(); // Obtener número de la opción que seleccionó el usuario (por ejemplo, 1)

			String textoSolicitud;
			textoSolicitud = metodosDisponibles.get(opcion - 1); // Obtener solicitud que quiere hacer el usuario (por
																	// ejemplo, agregar nuevo vehículo)

			Solicitud solicitud = new Solicitud(textoSolicitud, tipoDeUsuario, sesion.getNombreDeUsuario());

			coordinadorEmpresa.coordinarSolicitud(solicitud);
		}
	}

	public void pedirParametrosaUsuario(Solicitud solicitud) {
		Collection<String> parametros_necesarios = solicitud.getParametrosSolicitud().keySet();
		for (String paso : solicitud.getPasosResolucion()) {
			System.out.println(paso);

		}
		solicitud.setPasosResolucion(new ArrayList<String>()); //borrar pasos anteriores 
		
	    for (String parametro : parametros_necesarios) {
	        String valorParametro;
	        
	        System.out.println(parametro+" :");
	        valorParametro = scanner.nextLine();
	        
	        if(parametro.equals("PLACA")) {
	        	
	        	 boolean placavalida = validarPlaca(valorParametro);
	        	 while(!placavalida) {
	        		 
	        		 System.out.println("PLACA :");
	        		 valorParametro = scanner.nextLine();
	        		 placavalida = validarPlaca(valorParametro);
	        	 }
	        }
	        
	        if(parametro.equals("CATEGORIA") && !solicitud.getTextoSolicitud().equals("Definir tarifa diaria para una categoria")) {
	        	List<String> categoriasregistradas = coordinadorEmpresa.darNombresCategorias();
	        	if(!categoriasregistradas.contains(valorParametro)) {
	        		System.out.println("La categoria del vehiculo no esta registrada. Por favor, defina la tarifa de la categoria seleccionando la opción 6.");
	        	}
	        }
	        
	        if(parametro.equals("SEDE UBICACION")) {
	        	List<String> sedesregistradas = coordinadorEmpresa.darSedesRegistradas();
	        	boolean sederegistrada = sedesregistradas.contains(valorParametro);
	        	if(!sederegistrada) {
	        		System.out.println("La sede que ha ingresado no esta registrada, por favor ingrese usa sede registrada.");
	        		while(!sederegistrada) {
		        		 System.out.println("SEDE UBICACION :");
		        		 valorParametro = scanner.nextLine();
		        		 sederegistrada = sedesregistradas.contains(valorParametro);
	        			
	        		}
	        	}
	        }
	        if(parametro.equals("VALOR SEGURO")) {
	        	boolean esdouble = encontrarLetras(valorParametro);
	        	while(!esdouble) {
	        		System.out.println("VALOR SEGURO :");
	        		 valorParametro = scanner.nextLine();
	        		 esdouble = encontrarLetras(valorParametro);
	        		
	        	}
	        	
	        }
	        
			if (parametro.equals("PRECIO TEMPORADA ALTA") || parametro.equals("PRECIO TEMPORADA BAJA")) {
				boolean esdouble = encontrarLetras(valorParametro);
				while (!esdouble) {
					System.out.println("Debe ingresar un número flotante ejemplo 12.34");
					if(parametro.equals("PRECIO TEMPORADA ALTA")) {
						System.out.println("PRECIO TEMPORADA ALTA :");
					}else if(parametro.equals("PRECIO TEMPORADA BAJA")) {
						System.out.println("PRECIO TEMPORADA BAJA :");

					}
					
					valorParametro = scanner.nextLine();
					esdouble = encontrarLetras(valorParametro);

				}
			}

	        solicitud.getParametrosSolicitud().put(parametro, valorParametro);
	    
	    }
	}

	public static boolean encontrarLetras(String valorParametro) {
		for (char pos : valorParametro.toCharArray()) {
			if (Character.isDigit(pos) || pos == '.') {
				return true;
			}
		}
		return false;
	}

	public static boolean validarPlaca(String valorParametro) {
		if (valorParametro.length() != 6) {
			System.out.println("La placa debe de tener 3 letras y 3 números");
			return false; // La longitud de la placa debe ser de 6 caracteres.
		}

		String letras = valorParametro.substring(0, 3);
		String numeros = valorParametro.substring(3);

		for (char c : letras.toCharArray()) {
			if (!Character.isLetter(c) || !Character.isUpperCase(c)) {
				System.out.println("Los 3 primeros caracteres de la placa deben ser letras mayúsculas");
				return false; // Verifica que los tres primeros caracteres sean letras mayúsculas.
			}
		}

		for (char c : numeros.toCharArray()) {
			if (!Character.isDigit(c)) {
				System.out.println("los 3 ultimos caracteres de la placa deben de ser números.");
				return false; // Verifica que los tres últimos caracteres sean números.
			}
		}

		return true;

	}

	public void mostrarTerminacionDeSolicitud(Solicitud solicitud) {
		for (String paso : solicitud.getPasosResolucion()) {
			System.out.println(paso);
		}
		System.out.println("¿Desea seguir haciendo solicitudes? S/N");
		String respuesta = scanner.nextLine();
		if (respuesta.equals("S")) {
			mostrarMenuInicioSesion();

		}
	}

}

	


