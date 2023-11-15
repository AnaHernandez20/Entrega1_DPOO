package Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Programa.Empresa;
import Usuarios.Empleado;

public class InterfazAdminLocal {
	
	private Empresa empresaAlquiler;
	
	public void mostrarOpciones(String usuario, String password) throws IOException {
		this.empresaAlquiler = new Empresa();
		System.out.println("Sea bienvenido senior administrador\n");
		System.out.println("Que es lo que desea hacer?");
		System.out.println("1) Registrar nuevo empleado");
		System.out.println("2) Eliminar a algun empleado (se porto mal)");
		
		String nombreSede = empresaAlquiler.buscarSedeAdminLocal(usuario);
		
		int respuesta = Integer.parseInt(input("Ingrese su respuesta"));
		if (respuesta == 1) {
			mostrarParametrosAgregarEmpleado(nombreSede);
		}
		else if(respuesta == 2){
			eliminarEmpleado(nombreSede);
		}
		else {
			System.out.println("Ingrese una opcion valida porfavor");
			mostrarOpciones(usuario, password);
		}
	}
	
	//Metodo pa eliminar a algun empleado
	public void eliminarEmpleado(String nombreSede) {
		System.out.println("Vamos a ver quienes son los empleados de esta sede\n");
		ArrayList<Empleado> empleadosPorSede = empresaAlquiler.getInventario().getEmpleados().get(nombreSede);
		int contador = 0;
		for (Empleado empleadoDeseado: empleadosPorSede) {
			System.out.println(contador+") "+empleadoDeseado.getNombre());
			System.out.println("\n");
			contador++;
		}
		
		int index = Integer.parseInt(input("Ingrese su respuesta"));
		if (index > empleadosPorSede.size()) {
			System.out.println("Elija una opcion valida\n");
			eliminarEmpleado(nombreSede);
		}
		else {
			empresaAlquiler.getInventario().eliminarEmpleadoArchivo(empleadosPorSede.get(index).getLogin());
			empleadosPorSede.remove(index);
			System.out.println("Chaolin pinguin, el empleado ha sido despedido con exito");
		}
		
	}
	
	//Metodo para agregar empleado
	public void mostrarParametrosAgregarEmpleado(String nombreSede) {
		System.out.println("Se le van a pedir los parametros del empleado que desea agregar\n");
		String nombreEmpleado = input("Nombre del empleado");
		String nuevoLogin = input("Nuevo usuario para el empleado");
		String nuevaContrasena = input("Nueva contrasena para el empleado");
		
		empresaAlquiler.getInventario().agregarEmpleadoTodo(nombreEmpleado, nombreSede, nuevoLogin, nuevaContrasena);
		System.out.println("Se ha agregado su empleado correctamente");
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
