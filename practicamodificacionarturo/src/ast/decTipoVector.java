package ast;

public class decTipoVector extends Type {
	
	Type t;
	int fila, columna;
	public decTipoVector(Type t, int fila, int columna) {
		super();
		this.t = t;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		return "Vector " + t.toString();
	}
	public enumtipos getTipo() {
		return enumtipos.VECTOR;
	}
	public Type getT() {
		return t;
	}
	public void setT(Type t) {
		this.t = t;
	}
}
	
