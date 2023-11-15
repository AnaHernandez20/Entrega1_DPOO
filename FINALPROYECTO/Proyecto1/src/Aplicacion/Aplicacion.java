package Aplicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Interfaces.InterfazAdminGeneral;
import Interfaces.InterfazAdminLocal;
import Interfaces.InterfazCliente;
import Interfaces.InterfazEmpleado;
import Usuarios.ManejadorSesiones;

public class Aplicacion {
	
	//Interfaces//
	private InterfazAdminGeneral interfazAdminGeneral;
	private InterfazAdminLocal interfazAdminLocal;
	private InterfazCliente interfazCliente;
	private InterfazEmpleado interfazEmpleado;
	
	//Metodos//
	
	public void ejecutarAplicacion() throws IOException {
		
		System.out.print("Bienvenido a RapidosYAletosos\n");
		System.out.print("Adjunte su usuario para ingresar a la aplicacion \n");
		String usuario = input("Nombre de Usuario: ");
		String password = input("Ingrese su password: ");
		
		ManejadorSesiones manejador = new ManejadorSesiones();
		String rol = manejador.revisarUsuario(usuario, password);
		
		
		if (rol.equalsIgnoreCase("cliente")) {
			InterfazCliente interfazUsada = new InterfazCliente();
			interfazUsada.mostrarOpciones(usuario);
			
		}
		else if (rol.equalsIgnoreCase("adminLocal")) {
			InterfazAdminLocal interfazUsada = new InterfazAdminLocal();
			interfazUsada.mostrarOpciones(usuario, password);
		}
		else if (rol.equalsIgnoreCase("adminGeneral")) {
			InterfazAdminGeneral interfazUsada = new InterfazAdminGeneral();
			interfazUsada.mostrarOpciones();
		}
		else if (rol.equalsIgnoreCase("empleado")){
			InterfazEmpleado interfazUsada = new InterfazEmpleado();
			interfazUsada.mostrarOpciones(usuario);
			
		}
		else {
			System.out.print("pailangas");
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
	
	public static void main(String[] args) throws IOException {
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
	

}
