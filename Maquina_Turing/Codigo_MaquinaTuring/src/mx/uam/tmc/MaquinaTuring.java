package mx.uam.tmc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * Tellez Campos Luis Felipe
 * 2113044743
 * Toral Maldonado Rosa Guadalupe
 * 2153045948
 *
 */
public class MaquinaTuring {
	// Una máquina de Turing es una Tupla (S,Sigma,Gama,delta,q0,h)

	// Conjunto de estados, la información se guarda en delta asi
	// que solo necesitamos el número
	static int estados = 0;
	// Sigma como una cadena de símbolos
	static String sigma = "";
	// Gama como una cadena de símbolos
	static String gama = "";
	// Función de transición, como un HashMap (no es posible tener dos
	// llaves que me lleven a elementos distindos
	static HashMap<String, String> delta = null;
	// El estado inicial es simplemente el estado actual
	static int edoActual;
	// El estado de paro será simplemente -1

	static ArrayList<String> pila = new ArrayList<String>();
	static HashMap<String, String> edos = new HashMap<>();
	static String edo0 = "";
	static Cinta c = new Cinta();
	static boolean flag1 = false;

	public MaquinaTuring() {
		// TODO Crear un constructor a partir de una descripción

	}

	public MaquinaTuring(int N, String S, String G, HashMap<String, String> d, int q0) {
		estados = N;
		sigma = S;
		gama = G;
		delta = d;
		// delta es la transici�n, recibe puras cadenas y hay que convertirlas
		edoActual = q0;
	}

	// Dada el siguiente caracter de la cinta haz un paso
	public static void paso(char C, Cinta cinta) {

		String aux1 = edoActual + "|" + C;
		if (delta.containsKey(aux1)) {
			StringTokenizer tikens = new StringTokenizer(delta.get(aux1), "|");
			ArrayList<String> pila = new ArrayList<String>();
			while (tikens.hasMoreTokens()) {
				String aux = tikens.nextToken();
				pila.add(aux);

			}

			cinta.cambia(pila.get(1));
			cinta.mueve(pila.get(2));
			edoActual = Integer.parseInt(pila.get(0));
			flag1 = false;

		} else
			flag1 = true;

	}

	public static boolean paro() {
		return edoActual == -1;
	}

	/**
	 * 
	 * CLASE PRINCIPAL.
	 * 
	 * @throws IOException
	 */

	// Inicializa la maquina, la cinta y delta
	public static void Proceso(String direccion) throws IOException {
		// Lee un archivo de texto
		FileReader text = new FileReader(direccion);
		String cadena = "", aux = "";
		// Lee el archivo por renglones
		BufferedReader b = new BufferedReader(text);
		while ((cadena = b.readLine()) != null) {
			aux = cadena;
		}
		b.close();
		// Crea tokens del archivo
		StringTokenizer tikens = new StringTokenizer(aux, "|");

		while (tikens.hasMoreTokens()) {
			String aux1 = tikens.nextToken();
			StringTokenizer tikens1 = new StringTokenizer(aux1, ",");
			while (tikens1.hasMoreTokens()) {
				String aux2 = tikens1.nextToken();
				pila.add(aux2);

			}

		}
		try {
			edo0 = pila.get(pila.size() - 2).charAt(1) + "";
			c = new Cinta(pila.get(pila.size() - 6), Integer.parseInt(pila.get(pila.size() - 1)));
			new MaquinaTuring(Integer.parseInt(pila.get(pila.size() - 5)), pila.get(pila.size() - 4),
					pila.get(pila.size() - 3), edos, Integer.parseUnsignedInt(edo0));

			// Crea el hashmap para las transiciones
			while (pila.size() != 6) {
				String valor;
				String key = pila.get(0).charAt(1) + "|" + pila.get(1);
				if (pila.get(2).equals("h"))
					valor = "-1" + "|" + pila.get(3) + "|" + pila.get(4);
				else
					valor = pila.get(2).charAt(1) + "|" + pila.get(3) + "|" + pila.get(4);
				edos.put(key, valor);
				for (int j = 0; j < 5; j++)
					pila.remove(0);

			}
		} catch (Exception e) {
			c = new Cinta(pila.get(pila.size() - 6), -1);
		}

	}

	// Realiza un paso
	public static void creaMaquina() {
		MaquinaTuring.paso(c.getChar(), c);
	}

	// Muestra la cinta inicialmente
	public static String getCadena() {
		return pila.get(pila.size() - 6);
	}

	// Devuelve el estado en el que se encuentra
	public static int getEstado() {
		return edoActual;
	}

	// Devuelve la cinta modificada
	public static String getCinta() {
		// TODO Auto-generated method stub
		return c + "";
	}

	// Devuelve la posicion de la cinta
	public static int getPosi() {
		return c.getPos();
	}

	// Muestra error si en la cadena hay un s�mbolo que no es parte del alfabeto
	// Signma
	public static boolean flag1() {
		return flag1;
	}

}
