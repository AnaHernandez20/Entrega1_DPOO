package manejadorDeSesiones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManejadorDeSesiones {
    // Mapa que asocia tipos de usuario con métodos disponibles
    public Map<String, List<String>> metodosPorUsuario;

    public ManejadorDeSesiones() {
        // Inicializa el mapa y agrega métodos para cada tipo de usuario
        metodosPorUsuario = new HashMap<>();

        // Métodos disponibles para el "administradorGlobal"
        List<String> metodosAdminGlobal = List.of("Registrar nuevo vehiculo", "Dar baja a un vehiculo", "Agregar seguro", "Borrar seguro","Modificar seguro","Definir tarifa diaria para una categoria","Agregar sede","Borrar sede");
        metodosPorUsuario.put("administradorGlobal", metodosAdminGlobal);

        // Métodos disponibles para el "administradorLocal"
        List<String> metodosAdminLocal = List.of("Registrar empleado", "Gestionar información de un empleado","Retirar empleado","Registrar usuario");
        metodosPorUsuario.put("administradorLocal", metodosAdminLocal);
        
        // Métodos disponibles para el "empleadoEntregas"
        List<String> metodosempleadoEntregas = List.of("Registrar conductor adicional", "Registrar entrega vehiculo");
        metodosPorUsuario.put("empleadoEntregas", metodosempleadoEntregas);
        
        // Métodos disponibles para el "empleadoRevision"
        List<String> metodosempleadoRevision = List.of("Registrar devolucion", "Registrar disponibilidad","Registrar fecha estimada de regreso");
        metodosPorUsuario.put("empleadoRevision", metodosempleadoRevision);
        
     // Métodos disponibles para el "cliente"
        List<String> metodoscliente = List.of("Reservar vehiculo", "Alquilar vehiculo");
        metodosPorUsuario.put("cliente", metodoscliente);
    }

    public static String validarSesion(Sesion sesion) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombreUsuario = partes[0].trim();
                    String contrasena = partes[1].trim();
                    String tipoUsuario = partes[2].trim();

                    // Validar si la sesión proporcionada coincide con los datos del archivo usuarios.txt
                    if (sesion.getNombreDeUsuario().equals(nombreUsuario) && sesion.getContrasena().equals(contrasena)) {
                        return tipoUsuario; // La sesión es válida, devolver el tipo de usuario
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Sesion no encontrada"; // La sesión no fue encontrada o es inválida
    }

    public List<String> obtenerMetodosDisponibles(String tipoUsuario) {
        return metodosPorUsuario.get(tipoUsuario);
    }

    // Resto de la lógica para manejar sesiones y usuarios
}


