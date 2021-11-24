package ast;

public class decTipo extends Subprogramas{
	private E nom;
	private Type type;
	private int fila, columna;
	public decTipo(E nom, Type type, int fila, int columna) {
		super();
		this.nom = nom;
		this.type = type;
		this.fila = fila;
		this.columna = columna;
	}
	public E getNom() {
		return nom;
	}
	public Type getType() {
		return type;
	}
	public String toString() {
		return type.toString() + " = " + nom.toString();
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.DECTIPO;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
}
