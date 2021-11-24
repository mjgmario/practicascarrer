package ast;

import java.util.ArrayList;

public class LlamadaFuncReturn extends E{
	private ArrayList<E> argumentos;
	private E nombre;
	private Nodo vinculacion;
	public LlamadaFuncReturn(ArrayList<E> argumentos, E nombre, int fila, int columna) {
		super(fila, columna);
		this.argumentos = argumentos;
		this.nombre = nombre;
		
	}
	public String toString() {
		String result = nombre.toString()+" (";
		for(int i = argumentos.size()-1; i >= 0 ; --i)
			result = result + argumentos.get(i).toString() + ", ";
		return result + ")";
	}
	public ArrayList<E> getArgumentos() {
		return argumentos;
	}
	public void setArgumentos(ArrayList<E> argumentos) {
		this.argumentos = argumentos;
	}
	public E getNombre() {
		return nombre;
	}
	public void setNombre(E nombre) {
		this.nombre = nombre;
	}
	public void vincula(Nodo nodofun) {
		this.vinculacion = nodofun;
	}
	
	public Nodo getVinculacion() {
		return vinculacion;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.LLAMADAFUNCRETURN;
	}

}
