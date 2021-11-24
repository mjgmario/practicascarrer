package ast;

import java.util.ArrayList;

public class ifConElse extends Subprogramas {
	private A cuerpo1, cuerpo2;
	private ArrayList<ElseIf> list;
	private E condicion;
	private int fila, columna;
	public ifConElse(A cuerpo1, A cuerpo2, ArrayList<ElseIf> list, E condicion, int fila, int columna) {
		super();
		this.cuerpo1 = cuerpo1;
		this.cuerpo2 = cuerpo2;
		this.list = list;
		this.condicion = condicion;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		String result = "if  " + condicion.toString() + ": " + cuerpo1.toString() + "\n";
		for(int i = list.size() -1 ; i >= 0 ; --i)
			result = result + list.get(i).toString() + "\n";
		result = result + "else " + ": " + cuerpo2.toString();
		return result;
	}
	public E getCondicion() {
		return condicion;
	}
	public A getCuerpo1() {
		return cuerpo1;
	}
	public A getCuerpo2() {
		return cuerpo2;
	}
	public ArrayList<ElseIf> getList() {
		return list;
	}
	public int getColumna() {
		return columna;
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.IFCONELSE;
	}
	
}
