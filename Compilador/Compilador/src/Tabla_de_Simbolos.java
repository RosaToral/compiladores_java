
/**
 * Toral Maldonado Rosqa Guadalupe
 * 2153045948
 * Compiladores
 */

import java.util.LinkedList;

public class Tabla_de_Simbolos {

	// Se crea una clase Simbolos que contenga como parametros el valor y el
	// tipo de la tabla de simbolos
	private class Simbolo {

		// Atributos de la clase Simbolos
		private int tipo;
		private final String valor;

		/** Constructor de la clase Simbolo
		 * @param tipo
		 * @param valor
		 */
		Simbolo(int tipo, String valor) {
			this.tipo = tipo;
			this.valor = valor;
		}

		/** Método que regresa los datos guardados en tipo y valor
		 */
		@Override
		public String toString() {
			return "[" + valor + ", " + tipo + "]";
		}
	}

	// Se crea un LinkedList Para simular la tabla
	private final LinkedList<Simbolo> tablaSimb;

	/** Constructor de la clase Tabla_De_Simbolos que inicializa la tabla
	 * Reemplaza al metodo inicializa.
	 */
	public Tabla_de_Simbolos() {
		// Se incia la tabla de simbolos
		tablaSimb = new LinkedList<>();

		// Se llena con las palabras reservadas
		Simbolo palabrasReserv = new Simbolo(Constantes.PROGRAMA, "programa");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.COMIENZA, "comienza");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.TERMINA, "termina");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.SI, "si");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.ENTONCES, "entonces");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.OTRO, "otro");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.MIENTRAS, "mientras");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.HAZ, "haz");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.IMPRIME, "imprime");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.REPITE, "repite");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.PARA, "para");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.HASTA, "hasta");
		tablaSimb.add(palabrasReserv);
		palabrasReserv = new Simbolo(Constantes.A, "a");
		tablaSimb.add(palabrasReserv);
	}

	/** Metodo que busca un valor en la lista
	 *  @param v
	 * @return la posicion del valor en la lista
	 */
	public Integer buscaValor(String v) {
		// indice que sirve para recorrer el arreglo
		int i = 0;
		// Se recorre el arreglo, si el valor contenido de la tabla es igual al
		// que esta buscando, deja de recorrer el arreglo
		for (i = 0; i < tablaSimb.size(); i++)
			if (tablaSimb.get(i).valor.compareTo(v) == 0)
				break;
		
		// Verifica si el indice es igual al tamaño del arreglo, regresa un -1
		if (i == tablaSimb.size())
			return -1;
		return i;
	}

	/** Metodo que inserta un nuevo simbolo a la tabla
	 * @param v
	 * @param t
	 * @return la posicion en donde se inserto el nuevo elemento
	 */
	public Integer inserta(String v, int t) {
		// Se crea un nuevo simbolo y se agrega a la tabla de simbolos
		Simbolo simbol = new Simbolo(t, v);
		tablaSimb.add(simbol);
		
		// Se regresa la posicion donde se inserto. Se usa el tamaño de la
		// lista, ya que se inserta en la última posicion
		return tablaSimb.size() - 1;
	}

	/** Metodo que permite cambiar el tipo de un simbolo
	 * @param pos
	 * @param n_t
	 */
	public void fijaTipo(int pos, int n_t) {
		// Verifica, si la el entero "pos" se sale de la ultima posicion dentro
		// de la tabla de simbolos manda un error
		if (pos >= tablaSimb.size())
			System.out.println("Error");

		// Se cambia el tipo en la posición "pos" en la tabla de simbolos
		else 
			tablaSimb.get(pos).tipo = n_t;
	}

	/** Metodo que regresa el tipo de una posicion dada en la tabla
	 * @param pos
	 * @return el tipo gurdado en la posicion indicada
	 */
	public Integer indicaTipo(int pos) {
		// Verifica, si el entero "pos" se sale del arreglo, regresa un -1
		if (pos >= tablaSimb.size() || pos < 0)
			return -1;

		// Regresa el tipo indicado en esa posicion
		return tablaSimb.get(pos).tipo;
	}

	/** Metodo que regresa el valor de una posicion en la tabla
	 * @param pos
	 * @return el valor guardado en la posicion indicada
	 */
	public String indicaValor(int pos) {
		// Verifica, si el entero "pos" se sale del arreglo, regresa la cadena
		// vacia
		if (pos >= tablaSimb.size() || pos < 0)
			return "";

		// Regresa el valor indicado en esa posicion
		return tablaSimb.get(pos).valor;
	}

	/** Metodo que imprime la tabla
	 */
	public void imrpimeTS() {
		// Imprime el contenido de la tabla de simbolos
		System.out.println("Contenido de la tabla de simbolos: \n");
		for (int i = 0; i < tablaSimb.size(); i++)
			System.out.println(tablaSimb.get(i).toString());
	}
}
