package ast;

public class ElseIf extends Nodo{
	private E condicion;
	private A cuerpo;
	private int fila, columna;
	public ElseIf(E condicion, A cuerpo, int fila, int columna) {
		super();
		this.condicion = condicion;
		this.cuerpo = cuerpo;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		// TODO Auto-generated method stub
		  return  "elseif " + condicion.toString() + ": " + cuerpo.toString();
	}
	public TiposGenerales tipoGeneral() {
		return TiposGenerales.ELSEIF;
	}
	public E getCondicion() {
		return condicion;
	}
	public void setCondicion(E condicion) {
		this.condicion = condicion;
	}
	public A getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(A cuerpo) {
		this.cuerpo = cuerpo;
	}
}
