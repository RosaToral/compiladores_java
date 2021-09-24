import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** COMPILADORES
 * Toral Maldonado Rosa Guadalupe.
 * 2153045948
 */

import javax.swing.JOptionPane;

public class BufferDeEntrada {

	// Define el tamaño del arreglo
	private static final int TAM_BUFFER = 200;

	// Arreglo tipo cola que simula al buffer de entrada.
	private char[] buffer = new char[TAM_BUFFER];

	// Reader que sirve para leer el archivo fuente.
	private BufferedReader b;

	// Apuntador al frente de la cola (buffer)
	private int fren = -1;

	// Apuntador al posterior de la cola (buffer)
	private int post = -1;
	
	/** Constructor de la clse BufferDeEntrada
	 */
	public BufferDeEntrada() {

	}

	/** Metodo que abre el archivo fuente
	 *  @param f
	 * @return 0 si se cargo con exito, 1 de lo contrario
	 */
	public Integer identificaF(String f) {
		// Se controla en caso de error
		try {
			// Se lee el archivo fuente
			b = new BufferedReader(new FileReader(f));
			// Se atrapa la excepcion en caso de que no lea el archivo y regresa
			// 1
		} catch (FileNotFoundException e) {
			return 1;
		}
		// Regresa 0 si se cargo con exito
		return 0;
	}

	/** Metodo que carga el buffer con el siguiente contenido del archivo fuente
	 */
	public void cargaBE() {
		// Variable del tipo entero que guarda el codigo ASCII del siguiente
		// caracter encontrado en el archivo
		int siguiente = 0;
		
		// El frente y el posterior deben reiniciarse cuando el buffer es
		// llenado nuevamente
		post = -1;
		fren = -1;

		// Se controla en caso de error
		try {
			// Se guardan los primeros 200 caracteres en el buffer
			while ((post < (TAM_BUFFER - 1)) && ((siguiente = b.read()) != -1)) {
				// Se eliminan los retornos de carro (\r) y se dejan solamente
				// los saltos de linea (\n)
				if (siguiente != '\r') {
					post++;
					buffer[post] = (char) siguiente;
				}
			}

			// Se atrapa la excepcion en caso de que no se pueda seguir leyendo
			// el contenido del archivo. Lanza un mensaje de error.
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se puede leer el contenido del archivo");
		}
	}

	/** Metodo que lee el siguiente caracter de la entrada
	 * @return el caracter leido
	 * @throws Exception
	 */
	public Character lee() {
		// Se verifica, si está vacío que no regrese nada, y el frente lo
		// reinicie a -1
		if (esVacio() == 0 || fren == (TAM_BUFFER - 1)) {
			// Si el frente esta en la última psición es porque ya se trabajo
			// con todo el contenido
			if (fren == (TAM_BUFFER - 1))
				// Se vuelve a cargar el Buffer de Entrada
				cargaBE();
		}

		// Si el frente es mayor al posterior, quiere decir que ya no hay más
		// caracteres que leer: llego al fin de archivo
		if (fren > post)
			return Constantes.EOS;

		// Incrementa el frente y regresa a lo que apunta fren
		fren++;
		return buffer[fren];
	}

	/** Metodo que deslee el último caracter encontrado
	 * @param f
	 */
	public void deslee() {
		// Verifica, si el frente está en la posición 0 o -1, manda un mensaje
		// de error
		if (fren == -1)
			System.out.println("No se puede hacer la operacion");
		// De otra manera decrementa el frente regresandolo a la posición
		// anterior
		else {
			fren--;
		}
	}

	/** Metodo que indica si el buffer esta lleno
	 * @return 0 si esta lleno o 1 de lo contrario
	 */
	public Integer estaLleno() {
		// Verifica, si el posterior esta situado antes de la ultima posicion,
		// entonces el buffer esta lleno y regresa 0
		if (post == (TAM_BUFFER - 1)) {
			return 0;
		}
		// Regresa 1, en caso de que no este lleno
		return 1;
	}

	/**Metodo que indica si el buffer esta vacio
	 * @return 0 si esta vacio o 0 de lo contrario
	 */
	public Integer esVacio() {
		// Verifica, si el frente y el posterior no han sido movidos de su
		// posicion inicial, el buffer esta vacio y regresa 0
		if ((fren == -1 && post == -1))
			return 0;
		// Regresa 1, en caso de que no este vacio
		return 1;
	}
}
