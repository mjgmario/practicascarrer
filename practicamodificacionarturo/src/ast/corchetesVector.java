package ast;

public class corchetesVector extends EBin{

	public corchetesVector(E opnd1, E opnd2, int fila, int columna) {
		super(opnd1, opnd2, fila, columna);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return opnd1().toString() + "[" + opnd2().toString() + "]";
	}

	public tipoExpresion getTipo(){
		return tipoExpresion.CORCHETESVECTOR;
	}

}
