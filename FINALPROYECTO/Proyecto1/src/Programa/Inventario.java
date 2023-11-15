package Programa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Programa.VehiculoNoAlquilado.Estado;
import Usuarios.AdminLocal;
import Usuarios.Cliente;
import Usuarios.Empleado;

public class Inventario {
	
	//Atributos//
	private ArrayList<Categoria> categorias;
	private ArrayList<Vehiculo> vehiculosEspera;
	private ArrayList<VehiculoAlquilado> vehiculosAlquilados;
	private ArrayList<VehiculoNoAlquilado> vehiculosNoAlquilados;
	private ArrayList<Cliente> clientes;
	private ArrayList<Sede> sedes;
	private HashMap<String, ArrayList<Empleado>> empleados;
	private ArrayList<Seguro> segurosDisponibles;
	private HashMap<AdminLocal, String> administradoresLocales;
	private ArrayList<LicenciaConduccion> licencias;
	private ArrayList<TarjetaCredito> tarjetas;
	
	//Constructor//
	public Inventario() {
		this.categorias = new ArrayList<Categoria>();
		this.vehiculosEspera = new ArrayList<Vehiculo>();
		this.vehiculosAlquilados = new ArrayList<VehiculoAlquilado>();
		this.vehiculosNoAlquilados = new ArrayList<VehiculoNoAlquilado>();
		this.clientes = new ArrayList<Cliente>();
		this.sedes = new ArrayList<Sede>();
		this.empleados = new HashMap<String,  ArrayList<Empleado>>();
		this.segurosDisponibles = new ArrayList<Seguro>();
		this.administradoresLocales = new HashMap<AdminLocal, String>();
		this.licencias = new ArrayList<LicenciaConduccion>();
		this.tarjetas = new ArrayList<TarjetaCredito>();
		
	}
	
	//Getters and Setters//
	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
	public ArrayList<Vehiculo> getVehiculosEspera() {
		return vehiculosEspera;
	}
	public void setVehiculosEspera(ArrayList<Vehiculo> vehiculosEspera) {
		this.vehiculosEspera = vehiculosEspera;
	}
	public ArrayList<VehiculoAlquilado> getVehiculosAlquilados() {
		return vehiculosAlquilados;
	}
	public void setVehiculosAlquilados(ArrayList<VehiculoAlquilado> vehiculosAlquilados) {
		this.vehiculosAlquilados = vehiculosAlquilados;
	}
	public ArrayList<VehiculoNoAlquilado> getVehiculosNoAlquilados() {
		return vehiculosNoAlquilados;
	}
	public void setVehiculosNoAlquilados(ArrayList<VehiculoNoAlquilado> vehiculosNoAlquilados) {
		this.vehiculosNoAlquilados = vehiculosNoAlquilados;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ArrayList<Sede> getSedes() {
		return sedes;
	}
	public void setSedes(ArrayList<Sede> sedes) {
		this.sedes = sedes;
	}
	public HashMap<String, ArrayList<Empleado>> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(HashMap<String, ArrayList<Empleado>> empleados) {
		this.empleados = empleados;
	}
	public ArrayList<Seguro> getSegurosDisponibles() {
		return segurosDisponibles;
	}
	public void setSegurosDisponibles(ArrayList<Seguro> segurosDisponibles) {
		this.segurosDisponibles = segurosDisponibles;
	}
	public HashMap<AdminLocal, String> getAdministradoresLocales() {
		return administradoresLocales;
	}
	public void setAdministradoresLocales(HashMap<AdminLocal, String> administradoresLocales) {
		this.administradoresLocales = administradoresLocales;
	}
	
	//Carga de datos//
	
	public void cargarVehiculosEspera(String archivovehiculosGlobal) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivovehiculosGlobal));
		String linea = br.readLine();
		
		while (linea != null){
			String[] partes = linea.split(",");
			String categoriaString = partes[0];
			String placa = partes[1];
			String marca = partes[2];
			String modelo = partes[3];
			String color = partes[4];
			String tipoTransmision = partes[5];
			int capacidadPersonas = Integer.parseInt(partes[6]);
			boolean alquilado = Boolean.parseBoolean(partes[7]);
			
			//Se crea el carrazo//
			Vehiculo vehiculo = new Vehiculo(null, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, alquilado);
			boolean checker = false;
			int contador = 0;
			while(checker == false && contador < categorias.size()) {
				if (categorias.get(contador).getNombre().equals(categoriaString)) {
					vehiculo.setCategoria(categorias.get(contador));
					checker = true;
				}
				contador ++;
			}
			
			vehiculosEspera.add(vehiculo);
		
			linea = br.readLine();
		}
		
		br.close();
	}
	
	public void cargarVehiculosAlquilados(String archivoVehiculosAlquilados) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoVehiculosAlquilados));
		String linea = br.readLine();
		
		while (linea != null) {
			String[] partes = linea.split(",");
			String categoriaString = partes[0];
			String placa = partes[1];
			String marca = partes[2];
			String modelo = partes[3];
			String color = partes[4];
			String tipoTransmision = partes[5];
			int capacidadPersonas = Integer.parseInt(partes[6]);
			boolean alquilado = Boolean.parseBoolean(partes[7]);
			String loginCliente  = partes[8];
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fechaRetorno = LocalDate.parse(partes[9], formatter);
			
			String nombreSede = partes[10];
			
			//Se crea el carrazo XD//
			VehiculoAlquilado nuevoVehiculo = new VehiculoAlquilado(null, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, alquilado, null, fechaRetorno, null);
			
			boolean checker1 = false;
			int contador1 = 0;
			while (checker1 == false && contador1 < clientes.size()) {
				if (clientes.get(contador1).getLogin().equals(loginCliente)) {
					Cliente clienteRevisar = clientes.get(contador1);
					nuevoVehiculo.setClientePoseedor(clienteRevisar);
					checker1 = true;
				}
				contador1 ++;
			}
			contador1 = 0;
			checker1  = false;
			while (checker1 == false && contador1 < sedes.size()) {
				if (sedes.get(contador1).getNombre().equals(nombreSede)) {
					Sede sedeRevisar = sedes.get(contador1);
					nuevoVehiculo.setSedeDevolucion(sedeRevisar);
					checker1 = true;
				}
				contador1 ++;
			}
			contador1 = 0;
			checker1 = false;
			while(checker1 == false && contador1 < categorias.size()) {
				if (categorias.get(contador1).getNombre().equals(categoriaString)) {
					nuevoVehiculo.setCategoria(categorias.get(contador1));
					checker1 = true;
				}
				contador1 ++;
			}
			
			vehiculosAlquilados.add(nuevoVehiculo);
			linea = br.readLine();
		}
		br.close();
	}
	
	//Se cargan las sedes//
	public void cargarSedes (String archivoSedes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoSedes));
		String linea = br.readLine();
		
		while (linea != null) {
			String[] partes = linea.split(",");
			String nombre = partes[0];
			String ubicacion = partes[1];
			String adminLocalNombre = partes[2];
			
			//Se crea la nueva sede con los empleados y el admin en null
			Sede nuevaSede = new Sede(nombre, ubicacion);
			
			boolean checker = false;
			int contador = 0;
			List<AdminLocal> keysList = new ArrayList<>(administradoresLocales.keySet());
			while(checker == false && contador < keysList.size()) {
				AdminLocal adminRevisar = keysList.get(contador);
				if(adminRevisar.getLogin().equals(adminLocalNombre)) {
					nuevaSede.setAdminLocal(adminRevisar);
					checker = true;
				}
				
				contador ++;
			}
			
			//Obtener la lista de empleados por sede
			ArrayList<Empleado> empleadosPorSede = empleados.get(nombre);
			if(empleadosPorSede == null) {
				ArrayList<Empleado> nuevaLista = new ArrayList<Empleado>();
				empleados.put(nombre, nuevaLista);
			}
			else {
				for (Empleado empleado: empleadosPorSede) {
					nuevaSede.agregarEmpleadosSede(empleado);
				}
			}
			
			sedes.add(nuevaSede);
			
			linea = br.readLine();
			
		}
		br.close();
	}
	
	public void cargarAdminsLocales(String archivoAdminLocal) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoAdminLocal));
		String linea = br.readLine();
		
		while (linea != null) {
			String[] partes = linea.split(",");
			String loginAdminLocal = partes[0];
			String contrasenaAdminLocal = partes[1];
			String rol = partes[2];
			String nombre = partes[3];
			String nombreSede = partes[4];
			
			AdminLocal nuevoAdmin = new AdminLocal(loginAdminLocal, contrasenaAdminLocal, nombre, rol);
			administradoresLocales.put(nuevoAdmin, nombreSede);
			
			linea = br.readLine();
		}
		
		br.close();
		
	}
	
	//Se cargan las categorias//
	public void cargarCategoria(String archivoCategoria) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoCategoria));
		String linea = br.readLine();
		
		while (linea != null) {
			String[] partes = linea.split(",");
			String nombreCategoria = partes[0];
			double tarifaDiariaAlta = Double.parseDouble(partes[1]);
			double tarifaDiariaBaja = Double.parseDouble(partes[2]);
			double valorSedeDiferente = Double.parseDouble(partes[3]);
			double valorConductorExtra = Double.parseDouble(partes[4]);
			
			//Creamos un nuevo objeto de tipo categoria pa guardarlo en la lista//
			Categoria nuevaCategoria = new Categoria(nombreCategoria, tarifaDiariaAlta, tarifaDiariaBaja, valorSedeDiferente, valorConductorExtra);
			categorias.add(nuevaCategoria);
			linea = br.readLine();
		}
		
		br.close();
		
	}
	
	public void cargarEmpleados(String archivoEmpleados) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoEmpleados));
		String linea = br.readLine();
		
		while (linea != null) {
			String[] partes = linea.split(",");
			String loginEmpleado = partes[0];
			String contrasenaEmpleado = partes[1];
			String rol = partes[2];
			String nombre = partes[3];
			String nombresede = partes[4];
			
			//Se crea el nuevo empleado
			Empleado nuevoEmpleado = new Empleado(loginEmpleado, contrasenaEmpleado, nombre, rol, nombresede);
			if (empleados.containsKey(nombresede)) {
				empleados.get(nombresede).add(nuevoEmpleado);
			}
			else {
				ArrayList<Empleado> newArray = new ArrayList<Empleado>();
	            newArray.add(nuevoEmpleado);
	            empleados.put(nombresede, newArray);
			}
			
			
			linea = br.readLine();
		}
		br.close();
	}
	
	public void cargarClientes(String archivoClientes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoClientes));
		String linea = br.readLine();
		
		while(linea != null) {
			String[] partes = linea.split(",");
			String login = partes[0];
			String contrasenia = partes[1];
			String rol = partes[2];
			String nombreCliente = partes[3];
			String telefono = partes[4];
			String correo = partes[5];
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fechaNacimiento = LocalDate.parse(partes[6], formatter);
			
			long numeroTarjeta = Long.parseLong(partes[7]);
			long numeroLicencia = Long.parseLong(partes[8]);
			
			//Se crea el nuevo cliente
			Cliente nuevoCliente = new Cliente(login, contrasenia, nombreCliente, rol, telefono, correo, fechaNacimiento, null, null);
			
			//Revisar las licencias y tarjetas
			boolean checker = false;
			int contador = 0;
			while(checker == false && contador < licencias.size()) {
				LicenciaConduccion licenciaRevisar = licencias.get(contador);
				if(licenciaRevisar.getNumero() == numeroLicencia) {
					nuevoCliente.setLicencia(licenciaRevisar);
					checker = true;
				}
				
				contador ++;
			}
			
			contador = 0;
			checker = false;
			while (checker == false && contador < tarjetas.size()) {
				TarjetaCredito tarjetarevisar = tarjetas.get(contador);
				if(tarjetarevisar.getNumero() == numeroTarjeta) {
					nuevoCliente.setMedioPago(tarjetarevisar);
					checker = true;
				}
				
				contador ++;
			}
			
			clientes.add(nuevoCliente);
			linea = br.readLine();
		}
		br.close();
	}
	
	public void cargarLicencias(String archivoLicencias) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoLicencias));
		String linea = br.readLine();
		
		while(linea != null) {
			String[] partes = linea.split(",");
			long numeroLicencia = Long.parseLong(partes[0]);
			String paisExpedicion = partes[1];
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fechaVencimiento = LocalDate.parse(partes[2], formatter);
			
			String URLimagen = partes[3];
			
			LicenciaConduccion nuevaLicencia = new LicenciaConduccion(numeroLicencia, paisExpedicion, fechaVencimiento, URLimagen);
			licencias.add(nuevaLicencia);
			
			linea = br.readLine();
			
		}
		
		br.close();
	}
	
	public void cargarTarjetas(String archivoTarjetas) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoTarjetas));
		String linea = br.readLine();
		
		while(linea!=null) {
			String[] partes = linea.split(",");
			long numeroTarjeta = Long.parseLong(partes[1]);
			String tipo = partes[0];
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fechaVencimiento = LocalDate.parse(partes[2], formatter);
			
			double montoDisponible = Double.parseDouble(partes[3]);
			
			TarjetaCredito nuevaTarjeta = new TarjetaCredito(tipo, numeroTarjeta, fechaVencimiento, montoDisponible);
			tarjetas.add(nuevaTarjeta);
			
			linea = br.readLine();
			
		}
		br.close();
	}
	
	public void cargarSeguros(String archivoSeguros) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoSeguros));
		String linea = br.readLine();
		
		while(linea!=null) {
			String[] partes = linea.split(",");
			String nombreSeguro = partes[0];
			double precioSeguro = Double.parseDouble(partes[1]);
			
			//Crear nuevo Seguro
			Seguro seguro = new Seguro(nombreSeguro, precioSeguro);
			segurosDisponibles.add(seguro);
			
			linea = br.readLine();
			
		}
		br.close();
	}
	
	public void cargarNoAlquilados(String archivoNoAlquilados) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoNoAlquilados));
		String linea = br.readLine();
		
		while(linea!=null) {
			String[] partes = linea.split(",");
			String categoriaString = partes[0];
			String placa = partes[1];
			String marca = partes[2];
			String modelo = partes[3];
			String color = partes[4];
			String tipoTransmision = partes[5];
			int capacidadPersonas = Integer.parseInt(partes[6]);
			boolean alquilado = Boolean.parseBoolean(partes[7]);
			String valorEstado = partes[8];
			
			Estado estadoReal = null;
			if (valorEstado.equalsIgnoreCase("DISPONIBLE")) {
				estadoReal = Estado.DISPONIBLE;
			}
			else if (valorEstado.equalsIgnoreCase("MANTENIMIENTO")) {
				estadoReal = Estado.MANTENIMIENTO;
			}
			else {
				estadoReal = Estado.LIMPIEZA;
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fechaRegreso = LocalDate.parse(partes[9], formatter);
			
			String nombreSede = partes[10];
			
			VehiculoNoAlquilado nuevoVehiculo = new VehiculoNoAlquilado(null, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, alquilado, estadoReal, fechaRegreso, null);
			
			boolean checker = false;
			int contador = 0;
			while(checker == false && contador < categorias.size()) {
				if (categorias.get(contador).getNombre().equals(categoriaString)) {
					nuevoVehiculo.setCategoria(categorias.get(contador));
					checker = true;
				}
				contador ++;
			}
			
			contador = 0;
			checker = false;
			while (checker == false && contador < sedes.size()) {
				if (sedes.get(contador).getNombre().equals(nombreSede)) {
					Sede sedeRevisar = sedes.get(contador);
					nuevoVehiculo.setSedeUbicacion(sedeRevisar);
					checker = true;
				}
				contador ++;
			}
			
			vehiculosNoAlquilados.add(nuevoVehiculo);
			linea = br.readLine();
		}
		br.close();
	}
	
	public void cargarTodaLaCosa() throws IOException {
		cargarCategoria("data/archivoCategorias.txt");
		cargarAdminsLocales("data/archivoAdminLocal.txt");
		cargarTarjetas("data/archivoTarjetas.txt");
		cargarLicencias("data/archivoLicencias.txt");
		cargarVehiculosEspera("data/archivoVehiculos.txt");
		cargarEmpleados("data/archivoEmpleados.txt");
		cargarClientes("data/archivoClientes.txt");
		cargarSeguros("data/archivoSeguros.txt");
		cargarSedes("data/archivoSedes.txt");
		cargarVehiculosAlquilados("data/archivoVehiculosAlquilados.txt");
		cargarNoAlquilados("data/archivoVehiculosNoAlquilados.txt");
	}
	
	public List<String> readFile(String filepath) {
		ArrayList<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public void writeFile(List<String> lines, String filepath){
		try (FileWriter writer = new FileWriter(filepath)){
			int contador = 0;
			for (String line: lines) {
				if (contador < lines.size()-1) {
					writer.write(line);
					writer.write("\n");
				}
				else {
					writer.write(line);
				}
				contador++;
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escribirFile(String filepath, String content, boolean append) {
		try (FileWriter writer = new FileWriter(filepath, append)){
			writer.write("\n");
			writer.write(content);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarEmpleadoArchivo(String loginEmpleado) {
		List<String> lines = readFile("data/archivoEmpleados.txt");
		lines.removeIf(line -> line.startsWith(loginEmpleado));
		writeFile(lines, "data/archivoEmpleados.txt");
		List<String> lines1 = readFile("data/archivoUsuarios.txt");
		lines1.removeIf(line -> line.startsWith(loginEmpleado));
		writeFile(lines1, "data/archivoUsuarios.txt");
		
	}
	
	public void eliminarVehiculoArchivo(String placa) {
		List<String> lines = readFile("data/archivoVehiculosNoAlquilados.txt");
		lines.removeIf(line -> line.contains(placa));
		writeFile(lines, "data/archivoVehiculosNoAlquilados.txt");
		List<String> lines1 = readFile("data/archivoVehiculosNoAlquilados.txt");
		lines1.removeIf(line -> line.contains(placa));
		writeFile(lines1, "data/archivoVehiculosNoAlquilados.txt");
		
	}
	
	public void eliminarVehiculoEspera(String placa) {
		List<String> lines = readFile("data/archivoVehiculos.txt");
		lines.removeIf(line -> line.contains(placa));
		writeFile(lines, "data/archivoVehiculos.txt");
	}
	
	public void eliminarSedeArchivo(String nombreSede, String loginAdminLocal, String nombreAdmin) {
		List<String> lines = readFile("data/archivoSedes.txt");
		lines.removeIf(line -> line.startsWith(nombreSede));
		writeFile(lines, "data/archivoSedes.txt");
		
		List<String> lines1 = readFile("data/archivoAdminLocal.txt");
		lines1.removeIf(line -> line.startsWith(loginAdminLocal));
		writeFile(lines1, "data/archivoAdminLocal.txt");
		
		List<String> lines2 = readFile("data/archivoUsuarios.txt");
		lines2.removeIf(line -> line.contains(nombreAdmin));
		writeFile(lines2, "data/archivoUsuarios.txt");
		
	}
	
	public void eliminarSeguroArchivo(String nombreSeguro) {
		List<String> lines = readFile("data/archivoSeguros.txt");
		lines.removeIf(line -> line.startsWith(nombreSeguro));
		writeFile(lines, "data/archivoSeguros.txt");
	}
	
	public void agregarEmpleadoTodo(String nombre, String nombreSede, String user, String password) {
		//Se crea el empleado
		Empleado nuevoEmpleado = new Empleado(user, password, nombre, "Empleado", nombreSede);
		
		//Agregar al hash
		empleados.get(nombreSede).add(nuevoEmpleado);
		String infoUsuarios = user+";"+password+";"+"Empleado;"+nombre;
		String infoEmpleado = user+","+password+","+"Empleado,"+nombre+","+nombreSede;
		
		
		escribirFile("data/archivoUsuarios.txt", infoUsuarios, true);
		escribirFile("data/archivoEmpleados.txt", infoEmpleado, true);
		
	}
	
	public void agregarVehiculo(Categoria categoriaSeleccionada, String placa, String marca, String modelo, String color, String tipoTransmision, int capacidad, LocalDate fechaResgreso, Estado estado, Sede sedeRetorno, String estadoString, String fechaString) {
		VehiculoNoAlquilado nuevoVehiculo = new VehiculoNoAlquilado(categoriaSeleccionada, placa, marca, modelo, color, tipoTransmision, capacidad, false, estado, fechaResgreso, sedeRetorno);
		vehiculosNoAlquilados.add(nuevoVehiculo);
		
		//Agregar a los archivos//
		String infoCarro = categoriaSeleccionada.getNombre()+","+placa+","+marca+","+modelo+","+color+","+tipoTransmision+","+capacidad+","+false+","+estadoString+","+fechaString+","+sedeRetorno.getNombre();
		escribirFile("data/archivoVehiculosNoAlquilados.txt", infoCarro, true);
	}
	
	public void agregarAlquilado(String categoria, String placa, String marca, String modelo, String color, String tipoTransmision, int capacidad, String loginCliente, String fechaRetorno, String nombreSede) {
		String infoAlquilado = categoria+","+placa+","+marca+","+modelo+","+color+","+tipoTransmision+","+capacidad+","+"true"+","+loginCliente+","+fechaRetorno+","+nombreSede;
		escribirFile("data/archivoVehiculosAlquilados.txt", infoAlquilado, true);
				
	}
	
	
	
	
	public void agregarSede(String nombreAdmin, String userAdmin, String passwordAdmin,String nombreSede, String ubicacionSede) {
		AdminLocal nuevoAdmin = new AdminLocal(userAdmin, passwordAdmin, nombreAdmin, "AdminLocal");
		Sede sede = new Sede(nombreSede, ubicacionSede);
		sede.setAdminLocal(nuevoAdmin);
		
		administradoresLocales.put(nuevoAdmin, nombreSede);
		sedes.add(sede);
		
		//Agregar archivo admins locales
		String infoAdmin = userAdmin+","+passwordAdmin+","+"AdminLocal,"+nombreAdmin+","+nombreSede;
		String infoAdminUsuarios = userAdmin+";"+passwordAdmin+";"+"AdminLocal;"+nombreAdmin;
		escribirFile("data/archivoAdminLocal.txt", infoAdmin, true);
		escribirFile("data/archivoUsuarios.txt", infoAdminUsuarios, true);
		
		//Agregar archivo sedes
		String infoSede = nombreSede+","+ubicacionSede+","+userAdmin;
		escribirFile("data/archivoSedes.txt", infoSede, true);

	}
	
	public void eliminarSede(Sede sede) {
		AdminLocal adminLocal = sede.getAdminLocal();
		String loginAdmin = adminLocal.getLogin();
		String nombreSede = sede.getNombre();
		String nombreAdmin = adminLocal.getNombre();
		
		eliminarSedeArchivo(nombreSede, loginAdmin, nombreAdmin);
		ArrayList<Empleado> empleadosPorSede = sede.getEmpleados();
		for(Empleado empleado: empleadosPorSede) {
			eliminarEmpleadoArchivo(empleado.getLogin());
			
		}
		
	}
	
	public void agregarSegurito(String nombreSeguro, double precioSeguro) {
		Seguro seguro = new Seguro(nombreSeguro, precioSeguro);
		segurosDisponibles.add(seguro);
		String infoSeguro = nombreSeguro+","+precioSeguro;
		escribirFile("data/archivoSeguros.txt", infoSeguro, true);
	}
	
	public void eliminarSegurito(int index) {
		Seguro seguro = segurosDisponibles.get(index);
		String nombreSeguro = seguro.getNombre();
		eliminarSeguroArchivo(nombreSeguro);
	}
	
	public void agregarVehiculoNoAlquilado(VehiculoNoAlquilado vehiculo, String fechaString, String mandar, String nombreSede) {
		String infoVehiculo = vehiculo.getCategoria().getNombre()+","+vehiculo.getPlaca()+","+vehiculo.getMarca()+","+vehiculo.getModelo()+","+vehiculo.getColor()+","+vehiculo.getTipoTransmision()+","+vehiculo.getCapacidadPersonas()+","+"false"+","+mandar+","+fechaString+","+nombreSede;
		escribirFile("data/archivoVehiculosNoAlquilados.txt", infoVehiculo, true);
		vehiculosNoAlquilados.add(vehiculo);
		eliminarVehiculoEspera(vehiculo.getPlaca());
	}
	
	
}
