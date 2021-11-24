package ast;

public class buclefor2 extends Subprogramas{
	private A prog;
	private E variable, conjunto;
	private int fila, columna;

	public buclefor2(A prog, E variable, E conjunto, int fila, int columna) {
		super();
		this.prog = prog;
		this.variable = variable;
		this.conjunto = conjunto;
		this.fila = fila;
		this.columna = columna;
	}

	public A getProg() {
		return prog;
	}

	public E getVariable() {
		return variable;
	}

	public E getConjunto() {
		return conjunto;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "for\n" + variable.toString()+" in "+conjunto.toString()+"\n"+prog.toString();
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.FOR2;
	}
	
}
