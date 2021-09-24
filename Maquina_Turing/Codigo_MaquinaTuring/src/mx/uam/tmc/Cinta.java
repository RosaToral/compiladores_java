package mx.uam.tmc;

public class Cinta {
	// La cinta se representa como un cadena y una posición en la cinta
	private String cinta;
	private int pos;
	
	public Cinta() {
		// Constructor vacio
		cinta = "";
		pos = 0;
	}
	
	public Cinta(String c, int p) {
		cinta = c;
		pos = p;
	}
	
	public int getPos() {
		return pos;
	}
	
	public char getChar() {
		try {
		return cinta.charAt(pos);
		} catch (StringIndexOutOfBoundsException e) {
			return '@';
		}
	}
	
	public void cambia(String c) {
		// Si la posición es negativa agrega tantos espacios como sea necesario
		if (pos<0) {
			cinta = String.format("%"+(-pos)+"s", " ") + cinta;
			pos = 0;
		}
		// Cambia la cinta
		cinta = cinta.substring(0, pos) + c + cinta.substring(pos+1);
	}
	
	public void mueve(String d) {
		switch(d) {
		case "L": pos--;
			break;
		case "R": pos++;
			break;
		default: // No te mueves
		}
	}
	
	@Override
	public String toString() {
		// Regresa la cinta
		return cinta;
	}
}
