package alex;

public class TokenValue {
	public String lexema;
	public int fila;
	public int columna;
    public TokenValue(String lexema, int fila) {
    	this.lexema = lexema;
    	this.fila = fila;
    }
	public TokenValue(String lexema, int fila, int columna) {
		this.lexema = lexema;
		this.fila = fila;
		this.columna = columna;
	}
	public String getLexema() {
		return lexema;
	}
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
}

