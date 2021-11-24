package ast;

import java.util.List;

public class decEnum extends Subprogramas{
	
	private E nombre;
	int fila, columna;
	private List<E> valores;

	public decEnum(E nombre, int fila, int columna, List<E> valores) {
		super();
		this.nombre = nombre;
		this.fila = fila;
		this.columna = columna;
		this.valores = valores;
	}

	public E getNombre() {
		return nombre;
	}

	public List<E> getValores() {
		return valores;
	}

	@Override
	public String toString() {
		return nombre.toString();
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.DECENUM;
	}
	
}
