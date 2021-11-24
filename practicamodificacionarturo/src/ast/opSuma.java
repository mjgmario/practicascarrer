package ast;

public class opSuma extends EUn {

	public opSuma(E opnd, int fila, int columna) {
		super(opnd, fila, columna);
	}

	public String toString() {
			return " + " + opnd().toString();
	   }
	public tipoExpresion getTipo() {
		return tipoExpresion.OPSUMA;
	}

}
