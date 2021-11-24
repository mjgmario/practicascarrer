package ast;

public class AccesoVectorParam extends Nodo{
	private E expr;
	private int fila, columna;
	public AccesoVectorParam(E expr, int fila, int columna) {
		super();
		this.expr = expr;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString(){
		return "[" + expr.toString() + "]";
	}
	public TiposGenerales tipoGeneral(){
		return TiposGenerales.ACCESOVECTORPARAM;
	}
}
