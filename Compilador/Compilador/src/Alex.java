/**
 * COMPILADORES Toral Maldonado Rosa Guadalupe. 2153045948
 */
public class Alex {

	// Se crea una instancia del buffer de entrada. Se declara estática para que
	// sea solamente un buffer
	private BufferDeEntrada buffer;
	private Tabla_de_Simbolos tabla;
	private int num_linea = 1;
	private int tokenVal;
	private String valor = "";
	private int posicion = 0;

	/** Constructor de la clase Alex
	 * @param buffer
	 * @param tabla
	 */
	public Alex(BufferDeEntrada buffer, Tabla_de_Simbolos tabla) {
		this.buffer = buffer;
		this.tabla = tabla;
	}

	/** Metodo que regresa el tokenVal Regresa un entero con eel valor del
	 * tokenVal
	 */
	public int getTokenVal() {
		return tokenVal;
	}

	/** Metodo que regresa el numero de lineas leido Regresa un entero con el
	 * valor del numero de lineas contado
	 */
	public int getNoLinea() {
		return num_linea;
	}

	/** Funcion alex. Es el metodo que se manda a llamar para analizar un archivo
	 * Regresa un entero con el tipo de la palabra encontrada
	 */
	public int alex() {
		// Verifica, si el buffer de entrada esta vacio o el contenido ya fue
		// analizado se vuelve a cargar
		if (buffer.esVacio() == 0)
			buffer.cargaBE();
		char c = buffer.lee();

		// Verifica, si es un delimitador o comentario <eb>
		while (esMetapalabraOComentario(c) == true) {
			// Verifica, si llega una llave que abre es un comentario. Lee todo
			// lo que le sigue hasta que encuentre un salto de linea
			if (c == '{')
				while (c != Constantes.EOS && c != '\n')
					c = buffer.lee();
			// Verifica, si es un salto de linea aumenta el numero de lineas
			// analizado
			if (c == '\n')
				num_linea++;
			c = buffer.lee();
		}

		// Verifica si es un numero entero <N>
		if (Character.isDigit(c)) {
			return esN(c);
		}

		// Verifica si es un id <id>
		if (Character.isLetter(c))
			return esID(c);

		// Verifica si es una cadena <cad>
		if (c == '"') {
			return esCadena(c);
		}

		// Verifica si es un logico <logic_ampeer_pleca>
		if (c == '&' || c == '|') {
			return esLogic_amper_pleca(c);
		}

		// Verifica si es un relacional <relac_admin_asig>
		if (c == '>' || c == '!' || c == '<' || c == '=') {
			return esRelac_admin_asig(c);
		}

		// Verifica si es un aritmetico <arit>
		if (c == '+' || c == '*' || c == '-' || c == '/' || c == '%') {
			return esAritmetico(c);
		}

		// Verifica si es el fin de archivo <>
		if (c == Constantes.EOS) {
			tokenVal = Constantes.NINGUNO;
			return Constantes.HECHO;
		}

		// Si no pertenece a ninguno de los anteriores se regresa el codigo
		// ASCII del caracter
		tokenVal = Constantes.NINGUNO;
		return c;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * METODOS MODULARES USADOS PARA VERIFICAR LA REGLA DE PRODUCCION A USAR   *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	/** Metodo que indica si un caracter es una metapalabra. Regla de produccion
	 * <eb> Regresa true si c es una metapalabra, false de lo contrario
	 */
	private boolean esMetapalabraOComentario(char c) {
		if (c == ' ' || c == '\t' || c == '\n' || c == '{')
			return true;
		return false;
	}

	/** Metodo que identifica ID's. Regla de produccion <id>
	 */
	private int esID(char c) {
		valor = "";
		while (Character.isLetterOrDigit(c) || c == '_') {
			valor = valor + c;
			c = buffer.lee();
		}
		// Deslee al ultimo valor valido
		buffer.deslee();
		// Se busca en la tabla de simbolos
		posicion = tabla.buscaValor(valor);
		// Si regresa un -1, entonces el elmento no exiiste en la tabla de
		// simbolos
		// Se agrega
		if (posicion == -1)
			posicion = tabla.inserta(valor, Constantes.ID);
		tokenVal = posicion;
		return tabla.indicaTipo(posicion);
	}

	/** Metodo que indentifica numeros enteros numeros enteros. Regla de
	 * produccion <N>
	 */
	private int esN(char c) {
		valor = "";
		while (Character.isDigit(c)) {
			valor = valor + c;
			c = buffer.lee();
		}
		// Deslee al ultimo valor valido
		buffer.deslee();
		tokenVal = Integer.parseInt(valor);
		return Constantes.NUM_ENT;
	}

	/** Metodo que identifica si hay simbolos relacionales, signos de admiracion
	 * o de asignacion. Regla de produccion <relac_admir_asig>. Regresa true si
	 * pertenece al grupo
	 */
	private int esRelac_admin_asig(char c) {
		// Se guarda el valor actual del caracter
		char aux = c;
		if (c == '>' || c == '!' || c == '=') {
			if (c == '>') {
				c = buffer.lee();
				if (c == '=') {
					// Lo concatena al caracter anterior para que sea un mayor o
					// igual que
					valor = valor + c;
					tokenVal = Constantes.GE;
				} else {

					// Deslee alultimo valor valido
					buffer.deslee();
					c = aux;
					tokenVal = Constantes.GT;
				}
				return Constantes.RELOP;
			}
			if (c == '!') {
				c = buffer.lee();
				if (c == '=') {
					// Lo concatena al caracter anterior para que sea un
					// distinto
					valor = valor + c;
					tokenVal = Constantes.NE;
					return Constantes.RELOP;
				}
				// Deslee al ultimo valor valido
				buffer.deslee();
				// Se carga el valor inicial antes de analizar la palabra
				c = aux;
				tokenVal = Constantes.NINGUNO;
				// Como el signo de admiracion no esta contemplado dentro de
				// las constantes globales, el tokenVal va a ser
				// NINGUNO y el tipo va a ser su equivalente en ASCII
			}
			if (c == '=') {
				c = buffer.lee();
				if (c == '=') {
					// Lo concatena al caracter anterior para que sea un igual a
					// (doble)
					valor = valor + c;
					tokenVal = Constantes.EQ;
					return Constantes.RELOP;
				}
				// Deslee al ultimo valor valido
				buffer.deslee();
				// Se carga el valor inicial antes de analizar la palabra
				c = aux;
				tokenVal = Constantes.NINGUNO;
				return Constantes.ASIGNACION;
			}
		}
		if (c == '<') {
			c = buffer.lee();
			// Lo concatena al caracter anterior para que sea un menor o igual
			// que, o un distinto de
			valor = valor + c;
			if (c == '=')
				tokenVal = Constantes.LE;
			if (c == '>')
				tokenVal = Constantes.NE;
			if (c != '>' && c != '=')
				tokenVal = Constantes.LT;
			return Constantes.RELOP;
		}
		return c;
	}

	/** Metodo que identifica si hay simbolos logicos, ampersands (&) o plecas
	 * (|) solos. Regla de produccion <logic_amper_pleca>. Rgresa true si
	 * pertenece al grupo
	 */
	private int esLogic_amper_pleca(char c) {
		// Se guarda el valor actual de c
		char aux = c;
		if (c == '&') {
			c = buffer.lee();
			if (c == '&') {
				// Lo concatena al caracter anterior para que sea un AND
				valor = valor + c;
				tokenVal = Constantes.AND;
				return Constantes.MULOP;
				// Como el & no está definido dentro de las palabras
				// reservadas usadas, entonces el tokenVal va a ser igual a
				// NINGUNO y el tipo va a ser el codigo ASCII de &
			}
			// Deslee al ultimo valor valido
			buffer.deslee();
			// Se carga el valor inicial de c antes de analizar la variable
			c = aux;
		}
		if (c == '|') {
			c = buffer.lee();
			if (c == '|') {
				// Lo concatena al caracter anterior para que sea un OR
				valor = valor + c;
				tokenVal = Constantes.OR;
				return Constantes.ADDOP;
				// Como el | no está definido dentro de las palabras
				// reservadas usadas, entonces el tokenVal va a ser igual a
				// NINGUNO y el tipo va a regresar el codigo ASCII de |
			}
			// Deslee al ultimo valor valido
			buffer.deslee();
			// Se carga el valor inicial de c antes de analizar la variable
			c = aux;
		}
		tokenVal = Constantes.NINGUNO;
		return c;
	}

	/** Metodo que identifica si hay cadenas. Regla de producion <cad>
	 */
	private int esCadena(char c) {
		valor = "";
		// Concatena las primeras comillas
		valor = valor + c;
		c = buffer.lee();
		while (c != '"') {
			if (c == Constantes.EOS)
				break;
			else {
				valor = valor + c;
				c = buffer.lee();
			}
		}
		if (c == Constantes.EOS)
			return Constantes.HECHO;
		else {
			// Concatena la ultimas comillas
			valor = valor + c;
			// Se mete a la tabal de simbolos
			posicion = tabla.buscaValor(valor);
			if (posicion == -1)
				posicion = tabla.inserta(valor, Constantes.CADENA);
			tokenVal = posicion;
			return tabla.indicaTipo(posicion);
		}
	}

	/** Metodo que analiza si el caracter leido es un operador aritmetico Regresa
	 * un entero con el tipo de la palabra encontrada
	 */
	private int esAritmetico(char c) {
		// Si c es un + o un -, regresa el tipo ADDOP
		if (c == '+' || c == '-') {
			if (c == '-')
				tokenVal = Constantes.MENOS;
			if (c == '+')
				tokenVal = Constantes.MAS;
			return Constantes.ADDOP;
		} else {
			// Si c es un *, o / o % regresa el tipo MULOP
			if (c == '*')
				tokenVal = Constantes.MULT;
			if (c == '/')
				tokenVal = Constantes.DIV;
			if (c == '%')
				tokenVal = Constantes.MODULO;
			return Constantes.MULOP;
		}
	}
}
