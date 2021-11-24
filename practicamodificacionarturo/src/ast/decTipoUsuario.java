package ast;

public class decTipoUsuario extends Subprogramas {
	private E exp;
	private Type tipo;
	private int fila, columna;
	private String nombre;
	public decTipoUsuario(E exp,Type tipo, int fila, int columna) {
		super();
		this.exp = exp;
		this.tipo = tipo;
		this.fila = fila;
		this.columna = columna;
	}
	@Override
	public String toString() {
		
		return tipo.toString() + " =: " + exp.toString();
	}
	
	public tipoSubprograma getTipo() {
		return tipoSubprograma.DECTIPO;
	}
	public String getStringTipo(){
		return nombre;
	}
	
}
