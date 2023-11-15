package Programa;

import java.util.ArrayList;
import Usuarios.AdminLocal;
import Usuarios.Empleado;

public class Sede {
	
	//Atributos//
	
	private String nombre;
	private String ubicacion;
	private AdminLocal adminLocal;
	private ArrayList<Empleado> empleados;
	
	//Getters and Setters//
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public AdminLocal getAdminLocal() {
		return adminLocal;
	}
	public void setAdminLocal(AdminLocal adminLocal) {
		this.adminLocal = adminLocal;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	//Constructor//
	public Sede(String nombre, String ubicacion) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.empleados = new ArrayList<Empleado>();
	}
	
	//Metodos
	public void agregarEmpleadosSede(Empleado empleado) {
		empleados.add(empleado);
	}
	

}
