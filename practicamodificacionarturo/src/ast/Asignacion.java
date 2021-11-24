package ast;

public class Asignacion extends Subprogramas {
	private E expresion1, expresion2;
	private int fila, columna;
	public Asignacion(E expresion1, E expresion2, int fila, int columna) {
		super();
		this.expresion1 = expresion1;
		this.expresion2 = expresion2;
		this.fila = fila;
		this.columna = columna;
	}
	public E getExpresion1() {
		return expresion1;
	}
	public E getExpresion2() {
		return expresion2;
	}
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
	@Override
	public String toString() {
		return expresion1.toString() + "=" + expresion2.toString();
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.ASIGNACION;
	}
}
