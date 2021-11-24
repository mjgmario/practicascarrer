package ast;

public class BucleWhile extends Subprogramas {
	 private A prog;
	 private E condicion;
	 private int fila, columna;
		
	 public BucleWhile(A prog, E condicion, int fila, int columna) {
		super();
		this.prog = prog;
		this.condicion = condicion;
		this.fila = fila;
		this.columna = columna;
	 }

	
	 public A getProg() {
		return prog;
	}


	public E getCondicion() {
		return condicion;
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
		return "While\n" + condicion.toString() +"\n" + prog.toString();
	 }
	public tipoSubprograma getTipo() {
		return tipoSubprograma.WHILE;
	}
		
}
