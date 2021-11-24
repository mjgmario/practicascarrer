package ast;

public class ifSinElse extends Subprogramas{
	
	private A cuerpo;
	private E condicion;
	private int fila, columna;
	public ifSinElse(A cuerpo, E condicion, int fila, int columna) {
		super();
		this.cuerpo = cuerpo;
		this.condicion = condicion;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		return "if " + condicion.toString() + ":" + cuerpo.toString();
	}
	public A getCuerpo() {
		return cuerpo;
	}
	public E getCondicion() {
		return condicion;
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.IFSINELSE;
	}
	
}
