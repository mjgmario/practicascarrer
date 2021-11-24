package ast;

public class buclefor1 extends Subprogramas{
	private A prog;
	private Subprogramas decVi,exprPaso;
	private E condicion;
	private int fila, columna;
	
	public buclefor1(A prog, Subprogramas decVi, E condicion, Subprogramas exprPaso,
			int fila, int columna) {
		super();
		this.prog = prog;
		this.decVi = decVi;
		this.condicion = condicion;
		this.exprPaso = exprPaso;
		this.fila = fila;
		this.columna = columna;
	}

	public A getProg() {
		return prog;
	}

	public Subprogramas getDecVi() {
		return decVi;
	}

	public E getCondicion() {
		return condicion;
	}

	public Subprogramas getExprPaso() {
		return exprPaso;
	}

	@Override
	public String toString() {
		return  "for\n" + decVi.toString() + "\n" + condicion.toString() + "\n" + exprPaso.toString() + "\n" + prog.toString();
	}

	public tipoSubprograma getTipo() {
		return tipoSubprograma.FOR1;
	}
	
}
