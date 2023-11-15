package Usuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManejadorSesiones {
	
	//Atributos//

	private ArrayList<UsuarioGenerico> users;
	
	public ManejadorSesiones() throws IOException {
		this.users = new ArrayList<UsuarioGenerico>();
		cargarUsuarios("data/archivoUsuarios.txt");
	}
	
	//Se carga en un array todas las sesiones de todos los tipos posibles que vienen en el archivo usuarios.txt//
	public void cargarUsuarios(String archivoUsuarios) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
		String linea = br.readLine();
		
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreUsuario = partes[0];
			String password = partes[1];
			String rol = partes[2];
			String nombreCompleto = partes[3];
			
			if (rol.equalsIgnoreCase("adminLocal")) {
				AdminLocal adminLocal = new AdminLocal(nombreUsuario, password, nombreCompleto, rol);
				users.add(adminLocal);
			}
			else if (rol.equalsIgnoreCase("cliente")) {
				Cliente cliente = new Cliente(nombreUsuario, password, nombreCompleto, rol, null, null, null, null, null);
				users.add(cliente);
			}
			else if (rol.equalsIgnoreCase("empleado")) {
				Empleado empleado = new Empleado(nombreUsuario, password, nombreCompleto, rol, null);
				users.add(empleado);
			}
			else {
				AdminGeneral adminGeneral = new AdminGeneral(nombreUsuario, password, nombreCompleto, rol);
				users.add(adminGeneral);
			}
			
			linea = br.readLine();
			
		}
		
		br.close();
		
	}
	
	//Se revisa a que rol pertenece el usuario ingresado//
	public String revisarUsuario(String usuario, String password) {
		String fraseSalida = "";
		
		//Revisar si el usuario existe//
		int i = 0;
		boolean usuarioEncontrado = false;
		UsuarioGenerico usuarioExaminar = null;
		
		while (i < this.users.size() && usuarioEncontrado == false) {
			if(users.get(i).getLogin().equals(usuario)) {
				usuarioExaminar = users.get(i);
				usuarioEncontrado = true;
			}
			i++;
		}
		
		//Si el usuario no existe se le notifica a la persona//
		//Se revisa si la contrasena si corresponda con el usuario//
		
		if (usuarioEncontrado == false) {
			fraseSalida = "Usuario no existe";
		}
		else if (usuarioEncontrado == true && usuarioExaminar.getPassword().equals(password) == false){
			fraseSalida = "Mi bro te equivocaste de contrasena";
		}
		else if (usuarioEncontrado == true && usuarioExaminar.getPassword().equals(password) == true) {
			fraseSalida = usuarioExaminar.getRol();
		}
		
		return fraseSalida;
	}
}
