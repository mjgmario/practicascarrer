package ast;

public class or extends EBin {
   public or(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
	@Override
	public String toString() {
		// TODO Auto-generated method stub
			return opnd1().toString() + " OR " + opnd2().toString();
	   }
	public tipoExpresion getTipo() {
		return tipoExpresion.OR;
	}
}
