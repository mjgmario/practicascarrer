package ast;

public class accesoStructPuntero extends EBin {
	
	
	public accesoStructPuntero(E opnd1, E opnd2, int fila, int columna) {
		super(opnd1, opnd2, fila, columna);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return opnd1().toString()+"->"+opnd2().toString();
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.ACCESOSTRUCTPUNTERO;
	}
}
