package Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Programa.Categoria;
import Programa.Empresa;
import Programa.Sede;
import Programa.Seguro;
import Programa.VehiculoNoAlquilado;

public class InterfazAdminGeneral {

	private Empresa empresaAlquiler;
	
	public void mostrarOpciones() throws IOException {
		this.empresaAlquiler = new Empresa();
		System.out.println("Sea bienvenido Jefazo");
		System.out.println("Que es lo que desea hacer?");
		System.out.println("1) Registrar Vehiculo");
		System.out.println("2) Eliminar Vehiculo");
		System.out.println("3) Agregar Sede");
		System.out.println("4) Eliminar Sede");
		System.out.println("5) Agregar Seguro");
		System.out.println("6) Eliminar Seguro");
		
		int respuesta = Integer.parseInt(input("Ingrese su respuesta"));
		if (respuesta == 1) {
			agregarVehiculo();
		}
		else if (respuesta == 2) {
			eliminarVehiculo();
		}
		else if (respuesta == 3) {
			agregarSede();
		}
		else if (respuesta == 4) {
			eliminarSede();
		}
		else if (respuesta == 5) {
			agregarSeguro();
		}
		else if (respuesta == 6) {
			eliminarSeguro();
		}
		else {
			System.out.println("Porfavor ingrese una opcion valida");
			mostrarOpciones();
		}
		
	}
	
	public void agregarVehiculo() {
		System.out.println("Ahora se le van a pedir todos los datos para ingresar el nuevo vehiculo");
		mostrarCategorias();
		
		int categoriaID = Integer.parseInt(input("Ingrese su respuesta porfavor"));
		
		String placa = input("Ingrese la placa del vehiculo");
		String marca = input("Ingrese la marca del vehiculo");
		String modelo = input("Ingrese el modelo del vehiculo");
		String color = input("Ingrese el color del vehiculo");
		String tipoTransmision = input("Ingrese el tipo de transmision del vehiculo");
		
		int capacidadPersonas = Integer.parseInt(input("Ingrese la capacidad de personas que puede tener el vehiculo"));
		System.out.println("\n");
		System.out.println("AVISO: EL VEHICULO NO PODRA ESTAR DISPONIBLE, PRIMERO TOCA MANDARLOA REVISION O LIMPIEZA");
		System.out.println("0) Mantenimiento");
		System.out.println("1) Limpieza");
		
		int indexEstado = Integer.parseInt(input("Ingrese el estado en el que va a estar el vehiculo"));
		
		String fechaString = input("Ingrese la fecha en la que el carro va a estar disponible (DD/MM/AA)");
		
		mostrarSedes();
		int indexSede = Integer.parseInt(input("Ingrese la opcion de la sede donde va a ingresar el vehiculo"));
		
		empresaAlquiler.registrarVehiculo(categoriaID, placa, marca, modelo, color, tipoTransmision, capacidadPersonas, fechaString, indexSede, indexEstado);
		System.out.println("Se ha guardado el vehiculo correctamente");
	}
	
	public void agregarSede() {
		System.out.println("Ahora se le van a pedir todos los datos para ingresar la nueva sede\n");
		System.out.println("A quien va a designar como administrador para la nueva Sede\n?");
		System.out.println("Agrege la informacion para crear el nuevo Administrador local\n?");
		
		String nombreAdmin = input("Ingrese el nombre del administrador");
		String loginAdmin = input("Ingrese el nuevo login del administrador");
		String nuevaPassword = input("Ingrese la nueva password del administrador");
		
		String nombreSede = input("Ingrese el nombre de la nueva Sede");
		String ubicacion = input("Ingrese la ubicacion en donde va a estar la sede");
		
		empresaAlquiler.agregarNuevaSede(nombreSede, nombreAdmin, ubicacion, loginAdmin, nuevaPassword);
	}
	
	public void eliminarVehiculo() {
		System.out.println("Que vehiculo desea eliminar?\n");
		ArrayList<VehiculoNoAlquilado> vehiculos = empresaAlquiler.getInventario().getVehiculosNoAlquilados();
		int contador = 0;
		for (VehiculoNoAlquilado vehiculo: vehiculos) {
			System.out.println(contador+") "+vehiculo.getPlaca());
			contador++;
		}
		
		int index = Integer.parseInt(input("Ingrese su respuesta"));
		if (index >= vehiculos.size()) {
			System.out.println("Elija una opcion valida\n");
			eliminarVehiculo();
		}
		else {
			empresaAlquiler.getInventario().eliminarVehiculoArchivo(vehiculos.get(index).getPlaca());
			vehiculos.remove(index);
			System.out.println("Chaolin pinguin, el vehiculo ha sido eliminado con exito");
		}
		
	}
	
	public void eliminarSede() {
		System.out.println("A continuacion se le van a mostrar las sedes que se pueden eliminar");
		mostrarSedes();
		
		int respuesta = Integer.parseInt(input("Ingrese la opcion deeseada"));
		ArrayList<Sede> sedes = empresaAlquiler.getInventario().getSedes();
		if (respuesta >= sedes.size()) {
			System.out.println("Elija una opcion valida");
			eliminarSede();
		}
		else {
			empresaAlquiler.getInventario().eliminarSede(sedes.get(respuesta));
			sedes.remove(respuesta);
			System.out.println("Listos, la sede ha sido eliminada");
		}
		
	}
	
	
	public void mostrarCategorias() {
		ArrayList<Categoria> allCategorias = empresaAlquiler.getInventario().getCategorias();
		int contador = 0;
		for(Categoria categoria: allCategorias) {
			System.out.println(contador+") "+categoria.getNombre());
			System.out.println("\n");
			contador++;
		}
	}
	
	public void mostrarSedes() {
		ArrayList<Sede> allSedes = empresaAlquiler.getInventario().getSedes();
		int contador = 0;
		for(Sede sede: allSedes) {
			System.out.println(contador+") "+sede.getNombre());
			System.out.println("\n");
			contador++;
		}
	}
	
	public void agregarSeguro() {
		System.out.println("A continuacion se le van a mostrar los parametros que necesita para agregar un seguro");
		String nombreSeguro = input("Ingrese el nombre de su seguro");
		double tarifaSeguro = Double.parseDouble(input("Ingrese el precio del seguro"));
		empresaAlquiler.getInventario().agregarSegurito(nombreSeguro, tarifaSeguro);
		System.out.println("Se ha agregado correctamente el seguro "+nombreSeguro);
	}
	
	public void eliminarSeguro() {
		System.out.println("A continuacion se le van a mostrar los seguros que puede eliminar");
		int contador = 0;
		ArrayList<Seguro> seguros = empresaAlquiler.getInventario().getSegurosDisponibles();
		for(Seguro seguro: seguros) {
			System.out.println(contador+") "+seguro.getNombre());
			contador++;
		}
		
		int respuesta = Integer.parseInt(input("Ingrese la opcion deseada"));
		empresaAlquiler.getInventario().eliminarSegurito(respuesta);
		System.out.println("Se ha eliminado el seguro exitosamente");
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
