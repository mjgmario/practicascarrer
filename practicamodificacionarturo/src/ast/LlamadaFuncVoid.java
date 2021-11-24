package ast;

import java.util.ArrayList;

public class LlamadaFuncVoid extends Subprogramas{
	private int fila;
	private int columna;
	private ArrayList<E> argumentos;
	private E nombre;
	private Nodo vinculacionresul;
	public LlamadaFuncVoid(ArrayList<E> argumentos, E nombre, int fila, int columna) {
		this.fila=fila;
		this.columna = columna;
		this.argumentos = argumentos;
		this.nombre = nombre;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = nombre.toString()+" (";
		for(int i = argumentos.size()-1; i >= 0 ; ++i)
			result = result + argumentos.get(i).toString() + ", ";
		return result + ")";
	}
	public ArrayList<E> getArgumentos() {
		return argumentos;
	}
	public E getNombre() {
		return nombre;
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.LLAMADAFUNCIONVOID;
	}
	public void vincula(Nodo comprobacion) {
		this.vinculacionresul = comprobacion;
	}
	public Nodo getVinculacionresul() {
		return vinculacionresul;
	}

}
