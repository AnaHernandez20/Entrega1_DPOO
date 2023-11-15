package Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Programa.Categoria;
import Programa.Empresa;
import Programa.LicenciaConduccion;
import Programa.Reserva;
import Programa.Sede;
import Programa.Seguro;

public class InterfazCliente {
	
	private Empresa empresaAlquiler;
	
	//Es un metodo para mostrar un menu con lo que puede hacer el cliente//
	public void mostrarOpciones(String user) throws IOException {
		this.empresaAlquiler = new Empresa();
		System.out.print("Listones, vamos a ver que quieres hacer hoy\n");
		System.out.print("Porfavor elige una opcion\n");
		System.out.print("\n");
		System.out.print("1. Reservar un vehiculo\n");
		
		int respuesta = Integer.parseInt(input("Ingrese su respuesta"));
		if (respuesta == 1) {
			mostrarParametrosReserva(user);
		}
		else {
			System.out.println("Elija una opcion valida");
			mostrarOpciones(user);
		}
	}
	
	public void mostrarOpcionesCategoria() {
		ArrayList<Categoria> allCategorias = empresaAlquiler.getInventario().getCategorias();
		int contador = 0;
		for (Categoria categoria: allCategorias) {
			System.out.println(contador+") "+categoria.getNombre()+"");
			contador++;
		}
	}
	
	public String revisarCategoria(String respuesta) {
		int index = Integer.parseInt(respuesta);
		Categoria categoriaSeleccionada = empresaAlquiler.getInventario().getCategorias().get(index);
		return categoriaSeleccionada.getNombre();
	}
	
	public void mostrarOpcionesSede() {
		ArrayList<Sede> allSedes = empresaAlquiler.getInventario().getSedes();
		int contador = 0;
		for (Sede sede: allSedes) {
			System.out.println(contador+") "+sede.getNombre()+"");
			contador++;
		}
	}
	
	public String revisarSede(String respuesta) {
		int index = Integer.parseInt(respuesta);
		Sede sedeSeleccionada = empresaAlquiler.getInventario().getSedes().get(index);
		return sedeSeleccionada.getNombre();
	}
	
	
	//Es un metodo para mostrar que parametros debe ingresar para que se cree su reserva//
	public void mostrarParametrosReserva(String user) {
		System.out.print("A continuacion se muestra lo que debe ingresar para crear su reserva\n");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:MM");
		
		mostrarOpcionesCategoria();
		String categoriaSeleccionada = input("Categoria de su preferencia");
		String categoriaSeleccionadaS = revisarCategoria(categoriaSeleccionada);
		
		mostrarOpcionesSede();
		int sedeInicial = Integer.parseInt(input("Sede en la que va a recoger el producto"));
		
		mostrarOpcionesSede();
		int sedeFinal = Integer.parseInt(input("Sede en la que va a devolver el producto (Recuerde que si es diferente a la anterior, implica un cobro extra de 50,000)"));
		
		String nombre = input("Nombre completo");
		
		String fechaInicioString = input("Fecha en la que va a recoger el vehiculo (DD/MM/AA)");
		String fechaFinalString = input("Fecha en la que a devolver el vehiculo (DD/MM/AA)");
		
		LocalDate fechaInicio = LocalDate.parse(fechaInicioString, formatter);
		LocalDate fechaFinal = LocalDate.parse(fechaFinalString, formatter);
		
		String horaInicioString = input("Hora en la que va a recoger el vehiculo (HH:MM)");
		String horaFinalString = input("Hora en la que va a entregar el vehiculo (HH:MM)");
		
		LocalTime horaInicio = LocalTime.parse(horaInicioString, formatterTime);
		LocalTime horaFinal = LocalTime.parse(horaFinalString, formatterTime);
		
		System.out.println("A continuacion se le presentaran los parametros para ingresar de su licencia de conduccion");
		long numLicencia = Long.parseLong(input("Ingrese el numero de su licencia"));
		String paisExpedicion = input("Ingrese el pais de expedicion de su licencia");
		
		String fechaVencimientoLicenciaString = input("Ingrese la fecha de vencimiento de su licencia (DD/MM/AA)");
		LocalDate fechaVencimientoLicencia = LocalDate.parse(fechaVencimientoLicenciaString, formatter);

		String URLimagenLicencia = input("Ingrese la URL de la imganen de su licencia");
		
		LicenciaConduccion licenciaCliente = empresaAlquiler.crearNuevaLicencia(numLicencia, paisExpedicion, fechaVencimientoLicencia, URLimagenLicencia);
		
		int numeroConductoresExtra = Integer.parseInt(input("Ingrese el numero de conductores extra que va a agregar en la reserva (si no va a agregar ninguno, inserte 0)"));
		String nombreConductorExtra;
		String telefonoConductoExtra;
		String correoConductorExtra;
		long numeroLicenciaCOnductorExtra;
		String paisExpedicionConductoExtra;
		
		String fechita;
		LocalDate fechaVencimientoLicenciaConductorExtra;
		
		String URLimagenConductorExtra;
		
		
		for(int i = 0; i < numeroConductoresExtra; i++) {
			nombreConductorExtra = input("Ingrese el nombre del conductor Extra numero "+(i+1));
			telefonoConductoExtra = input("Ingrese el telefono del conductor extra numero "+(i+1));
			correoConductorExtra = input("Ingrese el correo del conductor Extra numero "+(i+1));
			numeroLicenciaCOnductorExtra = Long.parseLong(input("Ingrese el numero de la licencia de conduccion del conductor extra numero "+(i+1)));
			paisExpedicionConductoExtra = input("Ingrese el pais de expedicion del conductor extra numero "+(i+1));
			fechita = input("Ingrese la fecha de vencimiento de la licencia de conduccion (DD/MM/AA) del conductor numero "+(i+1));
			fechaVencimientoLicenciaConductorExtra = LocalDate.parse(fechita, formatter);
			URLimagenConductorExtra = input("Ingrese el URL a la imagen de la licencia del conducto extra");
			
			empresaAlquiler.nuevoConductor(nombreConductorExtra, telefonoConductoExtra, correoConductorExtra, numLicencia, paisExpedicionConductoExtra, fechaVencimientoLicenciaConductorExtra, URLimagenConductorExtra);
			
		}
		
		String tipoTarjetaCredito = input("Ingrese el tipo de su tarjeta de credito");
		long numeroTarjetaCredito = Long.parseLong(input("Ingrese el numero de la tarjeta de credito"));
		String fechaVencimientoTarjetaString = input("Ingrese la fecha de vencimiento de la tarjeta de credito (DD/MM/AA)");
		LocalDate fechaVencimientoTarjeta = LocalDate.parse(fechaVencimientoTarjetaString, formatter);
		double montoTarjeta = Double.parseDouble(input("Ingrese el monto disponible en su tarjeta"));
		
		System.out.println("A continuacion se le van a mostrar todos los seguros que puede tomar con nosotros");
		for(int i = 0; i < empresaAlquiler.getInventario().getSegurosDisponibles().size(); i++) {
			int respuesta = Integer.parseInt(input("Desea tomar el "+empresaAlquiler.getInventario().getSegurosDisponibles().get(i).getNombre()+" "+empresaAlquiler.getInventario().getSegurosDisponibles().get(i).getTarifaDiaria()+"? (Si=1/No=0)"));
			if(respuesta == 1) {
				empresaAlquiler.modificarSegurosReserva(i);
			}
		}
		
		Reserva nuevaReserva = empresaAlquiler.nuevaReserva(categoriaSeleccionadaS, sedeInicial, sedeFinal, user, fechaInicioString, fechaFinalString, horaInicio, horaFinal, fechaInicio, fechaFinal, licenciaCliente, numeroConductoresExtra);
		if(nuevaReserva.getMontoEsperado().getPrecioTotal() > montoTarjeta) {
			System.out.println("Lo sentimos, parece que su tarjeta no tiene el monto suficiente para el auto seleccionado");
		}
		else {
			System.out.println("PERFECTO!");
			System.out.println("Ya tu reserva esta lista");
			System.out.println("A continuacion te mostramos los detalles de tu reserva");
			System.out.println("Reserva para: "+nombre);
			System.out.println("Categoria seleccionada: "+nuevaReserva.getCategoria().getNombre());
			System.out.println("Placa de su vehiculo: "+nuevaReserva.getVehiculoEntregado().getPlaca());
			System.out.println("Modelo del vehiculo: "+nuevaReserva.getVehiculoEntregado().getModelo());
			System.out.println("Marca del vehiculo: "+nuevaReserva.getVehiculoEntregado().getMarca());
			System.out.println("Capacidad de su vehiculo: "+nuevaReserva.getVehiculoEntregado().getCapacidadPersonas());
			System.out.println("Color de su vehiculo "+nuevaReserva.getVehiculoEntregado().getColor());
			System.out.println("Tipo de transmision del vehiculo: "+nuevaReserva.getVehiculoEntregado().getTipoTransmision());
			System.out.println("Sede de recogida: "+nuevaReserva.getSedeReclamar().getNombre());
			System.out.println("Ubicacion de la sede de recogida: "+nuevaReserva.getSedeReclamar().getUbicacion());
			System.out.println("Sede de retorno: "+nuevaReserva.getSedeReclamar().getNombre());
			System.out.println("Ubicacion de la sede de retorno: "+nuevaReserva.getSedeReclamar().getUbicacion());
			System.out.println("Dia de la recogida: "+fechaInicioString);
			System.out.println("Dia del retorno: "+fechaFinalString);
			System.out.println("Hora de la recogida: "+horaInicioString);
			System.out.println("Hora del retorno: "+horaFinalString);
			System.out.println("Se admitieron estos conductores Extra:");
			for (int i = 0; i < numeroConductoresExtra; i++) {
				System.out.println(nuevaReserva.getConductoresExtra().get(i).getNombre());
			}
			
			System.out.println("El costo total de tu reserva fue de: "+nuevaReserva.getMontoEsperado().getPrecioTotal());
			System.out.println("Gracias por rentar con Rapidos y Aletosos, ten un feliz dia.");
		}
		
	}
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	

}
