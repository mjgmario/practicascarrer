package ast;

public  class not extends EUn {
  
   public not(E opnd, int fila, int columna) {
    super(opnd, fila, columna);
     
   }
   public String toString() {
      return "! " + opnd().toString();
   }
   public tipoExpresion getTipo() {
      
      return tipoExpresion.NOT;
   }
   
}
