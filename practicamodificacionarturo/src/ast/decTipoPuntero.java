package ast;

public class decTipoPuntero extends Type{
	private Type t;
	private int fila, columna;
	@Override
	public String toString() {
		return "*" + t.toString();
	}
	public decTipoPuntero(Type t, int fila, int columna) {
		super();
		this.t = t;
		this.fila = fila;
		this.columna = columna;
	}
	public enumtipos getTipo() {
		return enumtipos.PUNTERO;
	}
	public Type getT() {
		return t;
	}
	public void setT(Type t) {
		this.t = t;
	}

}
