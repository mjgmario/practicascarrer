package ast;

public class opResta extends EUn{

	public opResta(E opnd, int fila, int columna) {
		super(opnd, fila, columna);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
			return " - " +opnd().toString();
	   }

	   public tipoExpresion getTipo() {
		
		return tipoExpresion.OPRESTA;
	}
}
