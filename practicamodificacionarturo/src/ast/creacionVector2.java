package ast;

import java.util.ArrayList;

public class creacionVector2 extends E {
	private ArrayList<E> vec;
	
	public creacionVector2(ArrayList<E> vec,int fila, int columna) {
        super(fila,columna);
		this.vec = vec;
	}
	@Override
	public String toString() {
		return vec.toString();
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.CREACIONVECTOR2;
	}
	public ArrayList<E> getVec() {
		return vec;
	}
	public void setVec(ArrayList<E> vec) {
		this.vec = vec;
	}
}
