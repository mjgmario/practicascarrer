package ast;

public class accesoPuntero extends EUn{
	
	public accesoPuntero(E opnd, int fila, int columna) {
		super(opnd, fila, columna);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "*" + opnd().toString();
	}
	
	public tipoExpresion getTipo() {
		
		return tipoExpresion.ACCESOPUNTERO;
	}
	
}
