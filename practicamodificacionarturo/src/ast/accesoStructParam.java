package ast;

public class accesoStructParam extends Nodo {
	private E nom;
	private int fila, columna;
	public accesoStructParam(E nom, int fila, int columna) {
		super();
		this.nom = nom;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString(){
		return "*" + nom.toString();
	}
	public TiposGenerales tipoGeneral(){
		return TiposGenerales.ACCESOSTRUCTPARAM;
	}
}
