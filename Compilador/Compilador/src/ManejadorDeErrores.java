import java.io.IOException;

/** COMPILADORES
 * Toral Maldonado Rosa Guadalupe.
 * 2153045948
 */

public class ManejadorDeErrores {

	// Instancias de la tabla de Simbolos y el Buffer de entrada
	Tabla_de_Simbolos tabla;
	Alex lexer;
	
	/** Constructor de la clase ManejadorDeErrores
	 * @param tabla
	 * @param lexer
	 */
	public ManejadorDeErrores(Tabla_de_Simbolos tabla, Alex lexer) {
		this.tabla = tabla;
		this.lexer = lexer;
	}

	/** Metodo que imprime el error encontrado y el numero de linea donde se encuantra
	 * @param noLinea
	 * @param seEspera
	 */
	public void error(int noLinea, int seEspera) {
		try{
		switch(seEspera){
		case '(':	Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + (char)seEspera + "\r\n");
			break;
		case ')':	Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + (char)seEspera + "\r\n");
			break;
		case ',':	Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + (char)seEspera + "\r\n");
			break;
		case ';':	Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + (char)seEspera + "\r\n");
			break;
		case '"':   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + (char)seEspera + "\r\n");
			break;
		case Constantes.COMIENZA:	Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.TERMINA:	Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.A:    Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.NUM_ENT:    Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.ASIGNACION:	  Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: '=' " + "\r\n");
			break;
		case Constantes.CADENA:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.ID:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.PROGRAMA:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.ENTONCES:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.OTRO:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.HAZ:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.IMPRIME:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		case Constantes.HASTA:   Principal.bwError.write("Error " + noLinea + ":" + ". Se esperaba: " + Constantes.regresaValor(seEspera) + "\r\n");
			break;
		default: Principal.bwError.write("Error " + seEspera + "\r\n");
			break;
		}
		}catch(IOException err){
			System.out.println("Error al escribir en el archivo");
		}
	}
}
