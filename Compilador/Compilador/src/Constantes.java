/**
 * COMPILADORES Toral Maldonado Rosa Guadalupe. 2153045948
 */
public class Constantes {

	public static final int NINGUNO = -1;

	public static final char EOS = '\0';

	// Tokens lexicográficos
	public static final int PROGRAMA = 256;
	public static final int COMIENZA = 257;
	public static final int TERMINA = 258;
	public static final int HECHO = 259;
	public static final int ASIGNACION = 260;
	public static final int ID = 261;
	public static final int NUM_ENT = 262;
	public static final int CADENA = 263;
	public static final int SI = 264;
	public static final int ENTONCES = 265;
	public static final int OTRO = 266;
	public static final int MIENTRAS = 267;
	public static final int HAZ = 268;
	public static final int IMPRIME = 269;
	public static final int DUMP = 270;
	public static final int HALT = 271;
	public static final int REPITE = 272;
	public static final int HASTA = 273;
	public static final int PARA = 274;
	public static final int A = 275;

	// Tokens lexicográficos y valores para operadores
	public static final int RELOP = 300;
	public static final int LT = 301;
	public static final int LE = 302;
	public static final int EQ = 303;
	public static final int GE = 304;
	public static final int GT = 305;
	public static final int NE = 306;
	public static final int ADDOP = 400;
	public static final int MAS = 401;
	public static final int MENOS = 402;
	public static final int OR = 403;
	public static final int MULOP = 500;
	public static final int MULT = 501;
	public static final int DIV = 502;
	public static final int MODULO = 503;
	public static final int AND = 504;

	// Símbolos utilizados por el emisor
	public static final int VALOR_I = 901;
	public static final int VALOR_D = 902;
	public static final int PUSH = 903;
	public static final int ASIGN = 904;
	public static final int LEE = 905;
	public static final int ESCRIBE = 906;
	public static final int ETIQUETA = 908;
	public static final int VE_A = 909;
	public static final int SI_V_VE_A = 910;
	public static final int SI_F_VE_A = 911;

	/** Metodo que indica qué tpo de palabra reservada es el numero que se le pasa por parametro
	 * @param constante
	 * @return una cadena con el valor correspondiente al numero recibido
	 */
	public static String regresaValor(int constante) {
		switch (constante) {
		case NINGUNO: 	   return "NINGUNO";
		case PROGRAMA: 	   return "PROGRAMA";
		case COMIENZA: 	   return "COMIENZA";
		case TERMINA: 	   return "TERMINA";
		case HECHO:  	   return "HECHO";
		case ASIGNACION:   return "ASIGNACION";
		case ID:  		   return "ID";
		case NUM_ENT: 	   return "NUM_ENT";
		case CADENA:   	   return "CADENA";
		case SI: 		   return "SI";
		case ENTONCES:     return "ENTONCES";
		case OTRO: 		   return "OTRO";
		case MIENTRAS:     return "MIENTRAS";
		case HAZ:  		   return "HAZ";
		case IMPRIME:      return "IMPRIME";
		case DUMP: 		   return "DUMP";
		case HALT:   	   return "HALT";
		case REPITE:  	   return "REPITE";
		case HASTA: 	   return "HASTA";
		case PARA:  	   return "PARA";
		case A:  		   return "A";
		case RELOP: 	   return "RELOP";
		case LT: 		   return "LT";
		case LE: 		   return "LE";
		case EQ: 		   return "EQ";
		case GE:  		   return "GE";
		case GT:  		   return "GT";
		case NE: 		   return "NE";
		case ADDOP:  	   return "ADDOP";
		case MAS: 		   return "MAS";
		case MENOS:  	   return "MENOS";
		case OR:  		   return "OR";
		case MULOP: 	   return "MULOP";
		case MULT:  	   return "MULT";
		case DIV:  		   return "DIV";
		case MODULO: 	   return "MODULO";
		case AND:  	 	   return "AND";
		}
		return "";
	}
}
