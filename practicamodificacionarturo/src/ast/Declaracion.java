package ast;

public abstract class Declaracion extends Subprogramas {
	private Type tipo;
	private E nombre;
	private int fila, columna;
	public Declaracion(Type tipo,E nombre, int fila, int columna) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.fila = fila;
		this.columna = columna;
	}
	public void setTipo(Type tipo) {
		this.tipo = tipo;
	}
	
	
	public int getFila() {
		return fila;
	}
	public abstract String toString();
	public E nombre(){return nombre;}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.DECVAR;
	}
	public Type getTipoAux() {
		return tipo;
	}
	public abstract tipoDeclaracion getTipoDeclaracion() ;
}
