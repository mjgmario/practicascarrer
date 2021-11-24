package ast;

public class Suma extends EBin {
	
   public Suma(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public String toString() {return opnd1().toString() + " + " + opnd2().toString();}
   public tipoExpresion getTipo() {
      return tipoExpresion.SUMA;
  }
}
