package Usuarios;

public class UsuarioGenerico {
	
	//Atributos//
	
	private String login;
	private String password;
	private String nombre;
	private String rol;
	
	//Getters//
	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public String getNombre() {
		return nombre;
	}
	public String getRol() {
		return rol;
	}
	
	public UsuarioGenerico(String login, String password, String nombre, String rol) {
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.rol = rol;
	}

}
