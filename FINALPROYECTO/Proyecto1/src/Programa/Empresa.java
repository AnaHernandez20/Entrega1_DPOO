package Programa;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import Programa.VehiculoNoAlquilado.Estado;
import Usuarios.AdminLocal;
import Usuarios.Cliente;
import Usuarios.Empleado;

public class Empresa {
	
	//Todos los inventarios que vamos a usar//
	private Inventario inventario;
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private ArrayList<Seguro> segurosSeleccionados = new ArrayList<Seguro>();
	private ArrayList<ConductorAdicional> extras = new ArrayList<ConductorAdicional>();
	
	//Constructor//
	public Empresa() throws IOException {
		this.inventario = new Inventario();
		inventario.cargarTodaLaCosa();
	}
	
	//Getters//
	public Inventario getInventario() {
		return inventario;
	}
	
	public ArrayList<Seguro> getseguros(){
		return this.segurosSeleccionados;
	}
	
	//Metodos//	

	//Se crea el metodo para iniciar una nueva reserva//
	public Reserva nuevaReserva(String categoria, int IndexsedeInicial, int IndexsedeFinal, String loginCliente, String fechaInicio, String fechafinal, LocalTime horaInicio, LocalTime horaFinal, LocalDate fechaInicioDate, LocalDate fechaFinalDate, LicenciaConduccion licenciaCliente, int numConductoresExtra) {
		//Buscamos el objeto vehiculo apto para nuestro clientazo//
		VehiculoAlquilado vehiculoParaCliente = buscarVehiculoCliente(categoria, IndexsedeFinal, fechafinal, loginCliente);
		
		//Buscamos las sedes de recogida y de llegada//
		Sede sedeRecogida = buscarSedeCliente(IndexsedeInicial);
		Sede sedeDevolver = buscarSedeCliente(IndexsedeFinal);
		
		//Sacamos los dias de alquilada//
		int diasRenta = (int) ChronoUnit.DAYS.between(fechaInicioDate, fechaFinalDate);
		
		//Sacar el objeto cliente
		Cliente cliente = buscarCliente(loginCliente);
		cliente.setLicencia(licenciaCliente);
		
		//Categoria del cehiculo
		Categoria categoriaSeleccionada = vehiculoParaCliente.getCategoria();
		
		//Crear Tarifa
		boolean mismaSede = false;
		if (sedeRecogida.getNombre().equals(sedeDevolver.getNombre())) {
			mismaSede = true;
		}
		
		
		//Se crea la nueva tarifa
		double precioTotalSeguros = 0;
		for(Seguro seguro: segurosSeleccionados) {
			precioTotalSeguros += seguro.getTarifaDiaria();
		}
		Tarifa nuevaTarifa = new Tarifa(diasRenta, mismaSede, numConductoresExtra, categoriaSeleccionada.getTarifaDiariaBaja(), precioTotalSeguros);
		Reserva nuevaReserva = new Reserva(categoriaSeleccionada, vehiculoParaCliente, cliente, extras, sedeRecogida, sedeDevolver, fechaInicioDate, fechaFinalDate, horaInicio, horaFinal, segurosSeleccionados, nuevaTarifa);
		return nuevaReserva;
		
	}
	
	public void modificarSegurosReserva(int index) {
		ArrayList<Seguro> seguros = inventario.getSegurosDisponibles();
		Seguro seguro = seguros.get(index);
		segurosSeleccionados.add(seguro);
	}
	
	public LicenciaConduccion crearNuevaLicencia(long numeroLicencia, String paisExpedicion, LocalDate fechaVencimiento, String URLimagen) {
		LicenciaConduccion nuevaLicencia = new LicenciaConduccion(numeroLicencia, paisExpedicion, fechaVencimiento, URLimagen);
		return nuevaLicencia;
	}
	
	public TarjetaCredito crearNuevaTarjeta(String tipo, long numeroTarjeta, LocalDate fechaVencimiento, double montoDisponible) {
		TarjetaCredito nuevaTarjeta = new TarjetaCredito(tipo, numeroTarjeta, fechaVencimiento, montoDisponible);
		return nuevaTarjeta;
	}
	
	public void nuevoConductor(String nombreConductor, String telefono, String correo, long numlicencia, String paisExpedicion, LocalDate fechaVencimientoLicencia, String URLImagen) {
		LicenciaConduccion nuevaLicencia = crearNuevaLicencia(numlicencia, paisExpedicion, fechaVencimientoLicencia, URLImagen);
		ConductorAdicional nuevoConductor = new ConductorAdicional(nombreConductor, telefono, correo, nuevaLicencia);
		extras.add(nuevoConductor);
	}

	//Metodo para buscar un vehiculo por categoria y transformarlo de no alquilado a alquilado//
	public VehiculoAlquilado buscarVehiculoCliente(String categoria, int indexSedeFinal, String fechaDevolucion, String login) {
	    //Vamos a revisar si la categoria ingresada por el usuario si esta disponible o no//
		
		boolean vehiculoDisponible = false;
		VehiculoNoAlquilado vehiculoCorrespondiente = null;
		int contador = 0;
		
		//Si esta disponible la categoria del usuario, se guarda el primer vehiculo que encuentra en la lista para entregarselo//
		
		while (contador < inventario.getVehiculosNoAlquilados().size() && vehiculoDisponible == false) {
			VehiculoNoAlquilado vehiculoRevisar = inventario.getVehiculosNoAlquilados().get(contador);
			if (vehiculoRevisar.getEstado() == VehiculoNoAlquilado.Estado.DISPONIBLE && vehiculoRevisar.getCategoria().getNombre().equals(categoria)) {
				vehiculoCorrespondiente = vehiculoRevisar;
				vehiculoDisponible = true;
			}
			
			contador ++;
		}
		
		//En caso de que la categoria no este disponible se le asigna un vehiculo random en la lista que este disponible//
		if (vehiculoDisponible == false) {
			int i = 0;
			while (i < inventario.getVehiculosNoAlquilados().size()) {
				VehiculoNoAlquilado vehiculoRevisar = inventario.getVehiculosNoAlquilados().get(i);
				if(vehiculoRevisar.getEstado() == VehiculoNoAlquilado.Estado.DISPONIBLE) {
					vehiculoCorrespondiente = vehiculoRevisar;
				}
				i ++;
			}
		}
		
		//Se obtienen los datos del vehiculoNoAlquilado que se eligio para crear un nuevo vehiculoAlquilado y borrar el otro de la lista de no alquilados//
		String placa = vehiculoCorrespondiente.getPlaca();
		String marca = vehiculoCorrespondiente.getMarca();
		String modelo = vehiculoCorrespondiente.getModelo();
		String color = vehiculoCorrespondiente.getColor();
		String tipoTransmision = vehiculoCorrespondiente.getTipoTransmision();
		int capacidadPersonas = vehiculoCorrespondiente.getCapacidadPersonas();
		Categoria categoriaXD = vehiculoCorrespondiente.getCategoria();
		
		//Eliminar vehiculo no alquilado//
		
		inventario.getVehiculosNoAlquilados().remove(vehiculoCorrespondiente);
		
		//Eliminar de archivos//
		inventario.eliminarVehiculoArchivo(placa);
		
		//Crear un nuevo Vehiculo//
		//Para esto toca obtener el objeto cliente que toca asignarle al vehiculo//
		
		Cliente clienteAsignado = buscarCliente(login);
		
		//Buscamos la sede de devolucion//
		
		Sede sedeDevolucion = buscarSedeCliente(indexSedeFinal);
		
		//Transformamos la fecha de string a localdate//
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate fechaRetorno = LocalDate.parse(fechaDevolucion, formatter);
		
		//Crear nuevo alquilado//
		
		VehiculoAlquilado nuevoAlquilado = new VehiculoAlquilado(categoriaXD, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, true, clienteAsignado, fechaRetorno, sedeDevolucion);
		
		//Agregamos el nuevo vehiculo a sus listas correspondientes//
		
		inventario.getVehiculosAlquilados().add(nuevoAlquilado);
		
		//Agregamos el vehiculo a sus archivos correspondientes//
		inventario.agregarAlquilado(categoria, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, login, fechaDevolucion, tipoTransmision);
		
		return nuevoAlquilado;
	}
	
	//Metodo para retornar un objeto de tipo sede segun su nombre//
	public Sede buscarSedeCliente(int index) {
		//Buscamos dentro de la lista sedes//
		ArrayList<Sede> sedes = inventario.getSedes();
		Sede miSede = sedes.get(index);
		return miSede;
	}
	
	//Metodo para buscar un objeto cliente por su nombre
	public Cliente buscarCliente(String nombre) {
		Cliente clienteAsignado = null;
		for (Cliente clienteRevisar: inventario.getClientes()) {
			if (clienteRevisar.getLogin().equals(nombre)) {
				clienteAsignado = clienteRevisar;
			}
		}
		
		return clienteAsignado;
	}
	
	//Metodo para agregarle un nuevo metodo de pago al cliente que hace la reserva
	
	public Categoria buscarCategoria(int index) {
		ArrayList<Categoria> categorias = inventario.getCategorias();
		return categorias.get(index);
	}
	
	//Buscar en el HashMap un adminLocal por su login
	public String buscarSedeAdminLocal(String loginAdminLocal) {
		ArrayList<AdminLocal> keysList = new ArrayList<>(inventario.getAdministradoresLocales().keySet());
		AdminLocal adminRevisar = null;
		for(AdminLocal admin: keysList) {
			if(admin.getLogin().equals(loginAdminLocal)) {
				adminRevisar = admin;
			}
		}
		
		String nombreSede = inventario.getAdministradoresLocales().get(adminRevisar);
		return nombreSede;
	}
	
	public Estado definirEstado(int indexEstado) {
		if(indexEstado == 0) {
			return Estado.MANTENIMIENTO;
		}
		else {
			return Estado.LIMPIEZA;
		}
	}
	
	public String definirStringEstado(int indexEstado) {
		if(indexEstado == 0) {
			return "MANTENIMIENTO";
		}
		else {
			return "LIMPIEZA";
		}
	}
	
	public void registrarVehiculo(int indexCategoria, String placa, String marca, String modelo, String color, String tipoTransmision, int capacidad, String fechaString, int indexSede, int indexEstado) {
		Categoria categoriaSeleccionada = buscarCategoria(indexCategoria);
		Sede sedeSeleccionada = buscarSedeCliente(indexSede);
		Estado estado = definirEstado(indexEstado);
		String stringEstado = definirStringEstado(indexEstado);
		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate fecha = LocalDate.parse(fechaString, formatterDate);
		
		inventario.agregarVehiculo(categoriaSeleccionada, placa, marca, modelo, color, tipoTransmision, capacidad, fecha, estado, sedeSeleccionada, stringEstado, fechaString);
		
	}
	
	public void agregarNuevaSede(String nombreSede, String nombreAdminLocal, String ubicacionSede, String loginAdmin, String passwordAdmin) {
		inventario.agregarSede(nombreAdminLocal, loginAdmin, passwordAdmin, nombreSede, ubicacionSede);
	}
	
	public void mandarRevisarVehiculo(int index, String mandar, LocalDate fechaRetorno, String user, String fechaString) {
		ArrayList<Vehiculo> vehiculosEspera = inventario.getVehiculosEspera();
		Vehiculo vehiculoEspera = vehiculosEspera.get(index);
		vehiculosEspera.remove(index);
		
		Categoria categoria = vehiculoEspera.getCategoria();
		String placa = vehiculoEspera.getPlaca();
		String marca = vehiculoEspera.getMarca();
		String color = vehiculoEspera.getColor();
		String modelo = vehiculoEspera.getModelo();
		String tipoTransmision = vehiculoEspera.getTipoTransmision();
		int capacidad = vehiculoEspera.getCapacidadPersonas();
		
		Empleado empleado = null;
		HashMap<String, ArrayList<Empleado>> hashempleados = inventario.getEmpleados();
		ArrayList<String> llaves = new ArrayList<>(hashempleados.keySet());
		for (String sede: llaves) {
			ArrayList<Empleado> empleaditos = hashempleados.get(sede);
			for(Empleado empleado1: empleaditos) {
				if(empleado1.getLogin().equals(user)) {
					empleado = empleado1;
				}
			}
		}
		
		String nombreSedesita = empleado.getSedeTrabajo();
		Sede sedeSeleccionada = null;
		int contador =0;
		boolean checkerbool = false;
		while (checkerbool == false && contador < inventario.getSedes().size()) {
			if (inventario.getSedes().get(contador).getNombre().equals(nombreSedesita)) {
				sedeSeleccionada = inventario.getSedes().get(contador);
			}
			contador++;
		}
		
		VehiculoNoAlquilado nuevoVehiculo = new VehiculoNoAlquilado(categoria, placa, marca, modelo, color, tipoTransmision, capacidad, false, Estado.valueOf(mandar), fechaRetorno, sedeSeleccionada);
		inventario.agregarVehiculoNoAlquilado(nuevoVehiculo, fechaString, mandar, nombreSedesita);
		
		
	}
		

}
