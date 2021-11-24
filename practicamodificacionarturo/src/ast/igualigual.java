package ast;

public class igualigual extends EBin {
   public igualigual(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.AND;}
   public String toString() {
      return opnd1().toString() + " == " + opnd2().toString();
    }
    public tipoExpresion getTipo() {
      return tipoExpresion.IGUALIGUAL;
    }
}
