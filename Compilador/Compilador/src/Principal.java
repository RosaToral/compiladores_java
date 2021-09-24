import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/** COMPILADORES
 * Toral Maldonado Rosa Guadalupe.
 * 2153045948
 */
public class Principal {
	// Variables que permiten manejar archivos
	private static File archivoError, archivoObj;
	public static BufferedWriter bwError, bwObj;
	
	public static void main(String[] args){
		Tabla_de_Simbolos tabla = new Tabla_de_Simbolos();
		BufferDeEntrada buffer = new BufferDeEntrada();
		String archivo = args[0];
		buffer.identificaF(archivo);
		creaArchivos(archivo);
		AnalizadorSintactico parser = new AnalizadorSintactico(buffer, tabla);
		parser.programa();
		try {
			bwError.close();
			bwObj.close();
			JOptionPane.showMessageDialog(null, "Archivos creados");
		} catch (IOException e) {
			System.out.println("No se pudieron cerrar los archivos");
		}
	}
	
	/** Metodo que permite crear los archivos
	 */
	private static void creaArchivos(String direccion) {
		String nombre = "";
		for (int i = 0; i < direccion.length() - 4; i++)
			nombre = nombre + direccion.charAt(i);
		archivoError = new File(nombre + ".err");
		archivoObj = new File(nombre + ".obj");
		try {
			bwError = new BufferedWriter(new FileWriter(archivoError));
			bwObj = new BufferedWriter(new FileWriter(archivoObj));
		} catch (IOException e) {
			System.out.println("No se puede escribir en los archivos");
		}
	}
}
