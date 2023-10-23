package manejadorArchivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import solicitud.Solicitud;
import gestores.Vehiculo;
import gestores.GestorAlquiler;
import gestores.GestorInventario;
import gestores.GestorSede;
import gestores.Sede;
import gestores.Seguro;
import gestores.Tarifa;

public class ManejadorDeArchivos {
    public static void escribirListaEnArchivo(String nombreArchivo) {
        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
        	 if(nombreArchivo.equals("src/archivos/inventario/inventarioGlobal.txt")) {
        		 for(Vehiculo carro : GestorInventario.vehiculos) {
        			 String PLACA = carro.getPlaca();
        			 String MARCA = carro.getMarca();
        			 String MODELO = carro.getModelo(); 
        			 String COLOR = carro.getColor();
        			 String TIPOTRANSMISION = carro.getTipoTransmision();
        			 String CATEGORIA = carro.getCategoria();
        			 String ESTAALQUILADO = carro.getEstaAlquilado();
        			 String DISPONIBILIDAD = carro.getDisponibilidad();
        			 String linea = PLACA+","+MARCA+","+MODELO+","+COLOR+","+TIPOTRANSMISION+","+CATEGORIA+","+ESTAALQUILADO+","+DISPONIBILIDAD+ "\n";
        			 fileWriter.write(linea);
        		 }
        	 }else if (nombreArchivo.equals("src/archivos/inventario/inventarioDisponibles.txt")){
        		 for(Vehiculo carro : GestorInventario.vehiculosNoAlquiladosDisponibles) {
        			 String PLACA = carro.getPlaca();
        			 String MARCA = carro.getMarca();
        			 String MODELO = carro.getModelo(); 
        			 String COLOR = carro.getColor();
        			 String TIPOTRANSMISION = carro.getTipoTransmision();
        			 String CATEGORIA = carro.getCategoria();
        			 String ESTAALQUILADO = carro.getEstaAlquilado();
        			 String DISPONIBILIDAD = carro.getDisponibilidad();
        			 String SEDEUBICACION = carro.getSedeUbicacion();
        			 String linea = PLACA+","+MARCA+","+MODELO+","+COLOR+","+TIPOTRANSMISION+","+CATEGORIA+","+ESTAALQUILADO+","+DISPONIBILIDAD+","+ SEDEUBICACION+ "\n";
        			 fileWriter.write(linea);
        		 }
        		 
        	 }else if(nombreArchivo.equals("src/archivos/inventario/inventarioNoDisponibles")) {
        		// PLACA,MARCA,MODELO,COLOR,TIPOTRANSMISION,CATEGORIA,ESTAALQUILADO,DISPONIBILIDAD,NOVEDAD,SEDEUBICACION,FECHAESTIMADADEREGRESO
        		 for(Vehiculo carro : GestorInventario.vehiculosNoAlquiladosNoDisponibles) {
        			 String PLACA = carro.getPlaca();
        			 String MARCA = carro.getMarca();
        			 String MODELO = carro.getModelo(); 
        			 String COLOR = carro.getColor();
        			 String TIPOTRANSMISION = carro.getTipoTransmision();
        			 String CATEGORIA = carro.getCategoria();
        			 String ESTAALQUILADO = carro.getEstaAlquilado();
        			 String DISPONIBILIDAD = carro.getDisponibilidad();
        			 String NOVEDAD = carro.getNovedad();
        			 String SEDEUBICACION = carro.getSedeUbicacion();
        			 String FECHAESTIMADADEREGRESO = carro.getFechaEstimadaDeRegreso();
        			 String linea = PLACA+","+MARCA+","+MODELO+","+COLOR+","+TIPOTRANSMISION+","+CATEGORIA+","+ESTAALQUILADO+","+DISPONIBILIDAD+","+ NOVEDAD +","+ SEDEUBICACION+ ","+FECHAESTIMADADEREGRESO+"\n";
        			 fileWriter.write(linea);
        		 }
        	 }else if(nombreArchivo.equals("src/archivos/alquiler/seguros.txt")) {
        		 for(Seguro seguro : GestorAlquiler.seguros) {
            		 String NOMBRESEGURO = seguro.getNombreSeguro();
            		 String VALORSEGURO = String.valueOf(seguro.getPrecioSeguro());
            		 String linea = NOMBRESEGURO+","+VALORSEGURO+"\n";
            		 fileWriter.write(linea);
        		}
        		
        	 }else if(nombreArchivo.equals("src/archivos/alquiler/tarifas.txt")) {
        		 for(Tarifa tarifa : GestorAlquiler.tarifas) {
            		 String CATEGORIA = tarifa.getCategoria();
            		 String PRECIOTEMPALTA = String.valueOf(tarifa.getPrecioTemporadaAlta());
            		 String PRECIOTEMBAJA = String.valueOf(tarifa.getPrecioTemporadaBaja());
            		 String linea = CATEGORIA +","+ PRECIOTEMPALTA +"," + PRECIOTEMBAJA+ "\n";
            		 fileWriter.write(linea);
        		}
        		 
        		
        	 }else if(nombreArchivo.equals("src/archivos/sedes/sedes.txt")) {
     			for(Sede sede : GestorSede.sedes) {
     				String NOMBRESEDE = sede.getNombre();
     				String UBICACION = sede.getUbicacion();
     				String HORAINICIO = sede.getHoraInicialAtencion();
     				String HORAFIN = sede.getHoraFinalAtencion();
     				String LUNES = String.valueOf(sede.getAbreLunes());
     				String MARTES = String.valueOf(sede.getAbreMartes());
     				String MIERCOLES = String.valueOf(sede.getAbreMiercoles());
     				String JUEVES = String.valueOf(sede.getAbreJueves());
     				String VIERNES = String.valueOf(sede.getAbreViernes());
     				String SABADO = String.valueOf(sede.getAbreSabado());
     				String DOMINGO = String.valueOf(sede.getAbreDomingo());
     				String linea = NOMBRESEDE + "," + UBICACION + "," + HORAINICIO + "," + HORAFIN + "," + LUNES + "," + MARTES + "," + MIERCOLES + "," + JUEVES + "," + VIERNES + "," + SABADO + "," + DOMINGO+"\n";
     				fileWriter.write(linea);

     			}
     		}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
}

