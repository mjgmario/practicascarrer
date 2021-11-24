package ast;

import java.util.ArrayList;

public class Switch extends Subprogramas {
	private E expresion;
	private ArrayList<ElementosCase> list;
	private int fila, columna;
	private Type tExpresion;
	public Switch(E expresion, ArrayList<ElementosCase> list, int fila, int columna) {
		super();
		this.expresion = expresion;
		this.list = list;
		this.fila = fila;
		this.columna = columna;
	}
	public E getExpresion() {
		return expresion;
	}
	public ArrayList<ElementosCase> getList() {
		return list;
	}
	public String toString() {
		String result = "Switch\n" + expresion.toString()+"\n";
		for(int i = list.size()-1; i >= 0; --i)
			result += " " +list.get(i).toString();
		return result;
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.SWITCH;
	}
	public Type gettExpresion() {
		return tExpresion;
	}
	public void settExpresion(Type tExpresion) {
		this.tExpresion = tExpresion;
	}
	
}
