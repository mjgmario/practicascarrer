package ast;

public class ElementosCase extends Nodo {
	private A programa;
	private E expresion;
	private int fila, columna;
	private Nodo vinculacion;
	public ElementosCase(A programa, E expresion, int fila, int columna)  {
		super();
		this.programa = programa;
		this.expresion = expresion;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		  return "- " + expresion.toString() + " : " + programa.toString();
	}
	public E getExpresion() {
		return expresion;
	}
	public void setExpresion(E expresion) {
		this.expresion = expresion;
	}
	public A getPrograma() {
		return programa;
	}
	public TiposGenerales tipoGeneral() {
		return TiposGenerales.ELEMENTOSCASE;
	}
	public void vincula(Switch switc) {
		this.vinculacion = switc;
	}
	public Nodo getVinculacion() {
		return vinculacion;
	}
}
