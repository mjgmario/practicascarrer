package ast;

public class decconVi extends Declaracion {
	private E  expresion;
	private Type t;
	public decconVi(Type tipo, E expresion, E nombre, int fila, int columna){
		super(tipo, nombre, fila, columna);
		this.expresion = expresion;
	}
	
	public String toString() {
		return nombre().toString() + " = " + expresion.toString();
	}

	public E getExpresion2() {
		return expresion;
	}

	public tipoDeclaracion getTipoDeclaracion() {
		
		return tipoDeclaracion.DECLARACIONCONVI;
	}

	public Type getT() {
		return t;
	}
}
