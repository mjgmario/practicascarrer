package ast;

public class And extends EBin {
   public And(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.AND;}
@Override
   public String toString() {
	// TODO Auto-generated method stub
	  return opnd1().toString() + " AND " + opnd2().toString(); 
   }
   public tipoExpresion getTipo() {
      // TODO Auto-generated method stub
      return tipoExpresion.AND;
   }
}
