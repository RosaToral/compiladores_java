import java.io.IOException;

/**
 * Toral Maldonado Rosa Guadalupe 2153045948 Compiladores Analizador Sintactico
 * (parser)
 *
 */
public class AnalizadorSintactico {

	private int preanalisis;
	private Alex lexer;
	private ManejadorDeErrores manejador;
	private Tabla_de_Simbolos tabla;
	private int etiq = 0;

	/** Constructor de la clase AnalizadorSintactico que inicializa al buffer de
	 * entrada y la tabla de simbolos (los cuales son usados por el Alex)
	 * @param buffer
	 * @param tabla
	 */
	public AnalizadorSintactico(BufferDeEntrada buffer, Tabla_de_Simbolos tabla) {
		this.tabla = tabla;
		lexer = new Alex(buffer, this.tabla);
		manejador = new ManejadorDeErrores(tabla, lexer);
	}

	/** Metodo que compara si lo que contiene preanalisis es igual a lo que la
	 * regla dice que valla despues.
	 * @param seEspera
	 */
	private void parea(int seEspera) {
		if (preanalisis == seEspera)
			preanalisis = lexer.alex();
		else {
			manejador.error(lexer.getNoLinea(), seEspera);
			preanalisis = lexer.alex();
		}
	}

	/** Metodo que crea el codigo
	 * @param demonico
	 * @param valor
	 */
	private void emite(int demonico, int valor) {
		try {
			switch (demonico) {
				case Constantes.PUSH:	Principal.bwObj.write("push " + valor + "\r\n");
				break;
			case Constantes.VALOR_I:	Principal.bwObj.write("valor_i " + tabla.indicaValor(valor) + "\r\n");
				break;
			case Constantes.VALOR_D:	Principal.bwObj.write("valor_d " + tabla.indicaValor(valor) + "\r\n");
				break;
			case Constantes.ASIGN:	Principal.bwObj.write(":=\r\n");
				break;
			case Constantes.ETIQUETA:	Principal.bwObj.write("etiqueta " + valor + "\r\n");
				break;
			case Constantes.VE_A:	Principal.bwObj.write("ve_a " + valor + "\r\n");
				break;
			case Constantes.SI_V_VE_A:	Principal.bwObj.write("si_v_ve_a " + valor + "\r\n");
				break;
			case Constantes.SI_F_VE_A:	Principal.bwObj.write("si_f_ve_a " + valor + "\r\n");
				break;
			// Caso de que sean operadores
			case Constantes.ADDOP:
			case Constantes.RELOP:
			case Constantes.MULOP:
				switch (valor) {
				case Constantes.LT:   Principal.bwObj.write("<\r\n");
					break;
				case Constantes.LE:   Principal.bwObj.write("<=\r\n");
					break;
				case Constantes.EQ:   Principal.bwObj.write("==\r\n");
					break;
				case Constantes.GE:   Principal.bwObj.write(">=\r\n");
					break;
				case Constantes.GT:   Principal.bwObj.write(">" + "\r\n");
					break;
				case Constantes.NE:   Principal.bwObj.write("<>\r\n");
					break;
				case Constantes.MAS:   Principal.bwObj.write("+\r\n");
					break;
				case Constantes.MENOS:   Principal.bwObj.write("-\r\n");
					break;
				case Constantes.MULT:   Principal.bwObj.write("*\r\n");
					break;
				case Constantes.DIV:   Principal.bwObj.write("div\r\n");
					break;
				case Constantes.MODULO:   Principal.bwObj.write("mod\r\n");
					break;
				case Constantes.AND:  Principal.bwObj.write("and\r\n");
					break;
				case Constantes.OR:   Principal.bwObj.write("or\r\n");
					break;
				default:   Principal.bwObj.write("OPERADOR " + demonico + " " + valor + "\r\n");
					break;
				}
				break;
			case Constantes.LEE:   Principal.bwObj.write("lee\r\n");
				break;
			case Constantes.ESCRIBE:   Principal.bwObj.write("escribe\r\n");
				break;
			case Constantes.IMPRIME:   Principal.bwObj.write("imprime " + valor + "\r\n");
				break;
			case Constantes.HALT:   Principal.bwObj.write("halt\r\n");
				break;
			case Constantes.DUMP:   Principal.bwObj.write("dump\r\n");
				break;
			default:   Principal.bwObj.write("token " + demonico + "tokenval " + valor + "\r\n");
				break;
			}
		} catch (IOException err) {
			System.out.println("No se puede escribir");
		}
	}

	/** Procedimiento correspondiente a la variable <programa>
	 */
	public void programa() {
		preanalisis = lexer.alex();
		encabezado();
		enunc_comp();
		emite(Constantes.HALT, Constantes.HECHO);
		parea(Constantes.HECHO);
		System.out.println("Programa Terminado");
	}

	/** Procedimiento correspondiente a la variable <encabezado>
	 */
	private void encabezado() {
		parea(Constantes.PROGRAMA);
		parea(Constantes.ID);
		parea(';');
	}

	/** Procedimiento correspondiente a la variable <enunc_comp>
	 */
	private void enunc_comp() {
		parea(Constantes.COMIENZA);
		while (preanalisis != Constantes.TERMINA) {
			if (preanalisis == Constantes.HECHO)
				break;
			enunciado();
		}
		parea(Constantes.TERMINA);

	}

	/** Procedimiento correspondiente a la variable <enunciado>
	 */
	private void enunciado() {
		switch (preanalisis) {
		case Constantes.COMIENZA: enunc_comp();
			break;
		case Constantes.ID:   asignacion();
			break;
		case Constantes.SI:   enunc_condicional();
			break;
		case Constantes.MIENTRAS:   enunc_mientras();
			break;
		case Constantes.IMPRIME:   enunc_impresion();
			break;
		case Constantes.REPITE:   enunc_repite();
			break;
		case Constantes.PARA:   enunc_para();
			break;
		case ';':   parea(';');
			break;
		default:  manejador.error(lexer.getNoLinea(), preanalisis); //preanalisis = lexer.alex();
			break;
		}
	}

	/** Procedimiento correspondiente a la variable <asignacion>
	 */
	private void asignacion() {
		emite(Constantes.VALOR_I, lexer.getTokenVal());
		parea(Constantes.ID);
		int aux = lexer.getTokenVal();
		parea(Constantes.ASIGNACION);
		expresion();
		parea(';');
		emite(Constantes.ASIGN, aux);// Igual
	}

	/** Procedimiento correspondiente a la variable <enunc_condicional>
	 */
	private void enunc_condicional() {
		int cond1 = 0, cond2 = 0, salida = 0;
		parea(Constantes.SI);
		expresion();
		parea(Constantes.ENTONCES);
		emite(Constantes.SI_V_VE_A, cond1 = etiq++);
		emite(Constantes.VE_A, cond2 = etiq++);
		emite(Constantes.ETIQUETA, cond1);
		enunciado();
		emite(Constantes.VE_A, salida = etiq++);
		if (preanalisis == Constantes.OTRO) {
			parea(Constantes.OTRO);
			emite(Constantes.ETIQUETA, cond2);
			enunciado();
		}
		emite(Constantes.ETIQUETA, salida);
		
	}

	/** Procedimiento correspondiente a la variable <enunc_mientras>
	 */
	private void enunc_mientras() {
		int cond = 0, salida = 0;
		parea(Constantes.MIENTRAS);
		emite(Constantes.ETIQUETA, cond = etiq++);
		expresion();
		parea(Constantes.HAZ);
		emite(Constantes.SI_F_VE_A, salida = etiq++);
		enunciado();
		emite(Constantes.VE_A, cond);
		emite(Constantes.ETIQUETA, salida);
	}

	/** Procedimiento correspondiente a la variable <enunc_impresion>  
	 */
	private void enunc_impresion() {
		parea(Constantes.IMPRIME);
		parea('(');
		emite(Constantes.IMPRIME, lexer.getTokenVal());
		parea(Constantes.CADENA);
		while (preanalisis == ',') {
			if(preanalisis == Constantes.HECHO)
				break;
			parea(',');
			expresion();
		}
		parea(')');
		parea(';');
	}

	/** Procedimiento correspondiente a la variable <enunc_repite>
	 */
	private void enunc_repite() {
		int cond = 0, salida = 0;
		parea(Constantes.REPITE);
		do {
			if (preanalisis == Constantes.HECHO)
				break;
			emite(Constantes.ETIQUETA, cond = etiq++);
			enunciado();
		} while (preanalisis != Constantes.HASTA);
		parea(Constantes.HASTA);
		expresion();
		emite(Constantes.SI_V_VE_A, salida = etiq++);
		emite(Constantes.VE_A, cond);
		emite(Constantes.ETIQUETA, salida);
		parea(';');
	}

	/** Procedimiento correspondiente a la variable <enunc_para>
	 */
	private void enunc_para() {
		int contador = 0, cond = 0, salida = 0, aux = 0;
		parea(Constantes.PARA);
			// Asignacion
		emite(Constantes.VALOR_I, contador = lexer.getTokenVal());
		parea(Constantes.ID);
		aux = lexer.getTokenVal();
		parea(Constantes.ASIGNACION);
		expresion();
		emite(Constantes.ASIGN, aux);
		parea(Constantes.A);
		emite(Constantes.ETIQUETA, cond = etiq++);
			// Condicion
		emite(Constantes.VALOR_I, contador);
		expresion();
		emite(Constantes.RELOP, Constantes.LE);
		parea(Constantes.HAZ);
		emite(Constantes.SI_F_VE_A, salida = etiq++);
		enunciado();
			// Incremento
		emite(Constantes.VALOR_I, contador);
		emite(Constantes.VALOR_D, contador);
		emite(Constantes.PUSH, 1);
		emite(Constantes.ADDOP, Constantes.MAS);
		emite(Constantes.ASIGN, aux);
		emite(Constantes.VE_A, cond);
		emite(Constantes.ETIQUETA, salida);
	}

	/** Procedimiento correspondiente a la variable <expresion>
	 */
	private void expresion() {
		expr_simple();
		int aux = lexer.getTokenVal();
		if (preanalisis == Constantes.RELOP) {
			parea(Constantes.RELOP);
			expr_simple();
			emite(Constantes.RELOP, aux);
		}

	}

	/** Procedimiento correspondiente a la variable <expr_simple>
	 */
	private void expr_simple() {
		termino();
		int aux = lexer.getTokenVal();
		while (preanalisis == Constantes.ADDOP) {
			if (preanalisis == Constantes.HECHO)
				break;
			parea(Constantes.ADDOP);
			termino();
			emite(Constantes.ADDOP, aux);
		}

	}

	/** Procedimiento correspondiente a la variable <termino>
	 */
	private void termino() {
		factor();
		int aux = lexer.getTokenVal();
		while (preanalisis == Constantes.MULOP) {
			 if(preanalisis == Constantes.HECHO)
				 break;
			parea(Constantes.MULOP);
			termino();
			emite(Constantes.MULOP, aux);
		}
	}

	/** Procedimiento correspondiente a la variable <factor>
	 */
	private void factor() {
		switch (preanalisis) {
		case '(': {
			parea('(');
			expresion();
			parea(')');
			break;
		}
		case Constantes.NUM_ENT: {
			emite(Constantes.PUSH, lexer.getTokenVal());
			parea(Constantes.NUM_ENT);
			break;
		}
		case Constantes.ID: {
			emite(Constantes.VALOR_D, lexer.getTokenVal());
			parea(Constantes.ID);
			break;
		}
		default: manejador.error(lexer.getNoLinea(), preanalisis); //preanalisis = lexer.alex();
			break;
		}
	}
}
