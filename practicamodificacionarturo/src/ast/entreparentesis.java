package ast;

public class entreparentesis extends EUn {

	public entreparentesis(E opnd, int fila, int columna) {
		super(opnd, fila, columna);
	}

	@Override
	public String toString() {
		return "(" + opnd().toString() + ")";
	}

	public tipoExpresion getTipo(){
		return tipoExpresion.ENTREPARENTESIS;
	}
}
