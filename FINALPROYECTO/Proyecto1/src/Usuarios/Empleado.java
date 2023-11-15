package Usuarios;

public class Empleado extends UsuarioGenerico{
	
	private String sedeTrabajo;

	public Empleado(String login, String password, String nombre, String rol, String sedeTrabajo) {
		super(login, password, nombre, rol);
		this.sedeTrabajo = sedeTrabajo;
	}

	public String getSedeTrabajo() {
		return sedeTrabajo;
	}

	public void setSedeTrabajo(String sedeTrabajo) {
		this.sedeTrabajo = sedeTrabajo;
	}
	
	

}
