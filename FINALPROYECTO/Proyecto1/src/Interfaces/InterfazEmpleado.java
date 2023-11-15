package Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Programa.Empresa;
import Programa.Vehiculo;

public class InterfazEmpleado {
	
	private Empresa empresaAlquiler;
	private int indexVehiculo;
	private String enviarVehiculo;
	private LocalDate fechaRetorno;
	
	public void mostrarOpciones(String user) throws IOException {
		this.empresaAlquiler = new Empresa();
		System.out.println("Bienvenido empleado");
		System.out.println("Aqui esta el trabajo de hoy");
		System.out.println("Recuerde seleccionar el auto que va a revisar y mandar a mantenimiento o a limpieza");
		ArrayList<Vehiculo> vehiculosEspera = empresaAlquiler.getInventario().getVehiculosEspera();
		int contador = 0;
		for(Vehiculo vehiculo: vehiculosEspera) {
			System.out.println(contador+") "+vehiculo.getPlaca()+" "+vehiculo.getMarca());
			contador++;
		}
		
		
		int respuesta = Integer.parseInt(input("Ingrese su opcion deseada"));
		if (respuesta >= vehiculosEspera.size()) {
			System.out.println("Ingrese una opcion valida porfavor");
			mostrarOpciones(user);
		}
		else {
			enviarVehiculo(user, respuesta);
		}
		
	}
	
	public void enviarVehiculo(String user, int respuesta){
		System.out.println("Elija a donde quiere mandar el vehiculo");
		System.out.println("0) MANTENIMIENTO");
		System.out.println("1) LIMPIEZA");
		
		int revisar = Integer.parseInt(input("Ingrese la opcion deseada"));
		if (revisar >= 2) {
			System.out.println("Ingrese una opcion valida porfavor");
			enviarVehiculo(user, respuesta);
		}
		else {
			if (revisar == 0) {
				this.enviarVehiculo = "MANTENIMIENTO";
			}
			else {
				this.enviarVehiculo = "LIMPIEZA";
			}
			String fechaRetornoString = input("Ingrese una fecha estimada para que el vehiculo este disponible (DD/MM/AA)");
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fecha = LocalDate.parse(fechaRetornoString, formatterDate);
			this.fechaRetorno = fecha;
			empresaAlquiler.mandarRevisarVehiculo(respuesta, enviarVehiculo, fecha, user, fechaRetornoString);
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
