package ast;

import java.util.List;

public class creacionVector extends E {
	private E exp1;
	private E exp2;
	private Type vinculacionTipo;
	
	public creacionVector( E exp1, E exp2, int fila, int columna) {
		super(fila, columna);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public String toString() {
		return "(" + exp1.toString() + " " + exp2.toString() + ")";
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.CREACIONVECTOR;
	}
	public E getExp1() {
		return exp1;
	}
	public E getExp2() {
		return exp2;
	}
	public void vinculaTipo(Type tipoBasicoVector) {
		this.vinculacionTipo = tipoBasicoVector;
	}
	public Type getVinculacionTipo() {
		return vinculacionTipo;
	}
	
}
