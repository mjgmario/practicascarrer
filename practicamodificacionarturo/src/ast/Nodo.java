package ast;

public  abstract class Nodo {
	protected int fila, columna;
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
	public abstract String toString();
	public abstract TiposGenerales tipoGeneral() ;
	
}