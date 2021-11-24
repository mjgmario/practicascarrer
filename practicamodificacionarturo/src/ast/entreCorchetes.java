package ast;

public class entreCorchetes extends EUn {

	public entreCorchetes(E opnd, int fila, int columna) {
		super(opnd, fila, columna);
	}

	@Override
	public String toString() {
		return "(" + opnd().toString() + ")";
	}

	public tipoExpresion getTipo() {
		return tipoExpresion.CORCHETESVECTOR;
	}
}
