package ast;

public abstract class EUn extends E {
   private E opnd;
  
   public EUn(E opnd, int fila, int columna) {
    super(fila, columna);
	this.opnd = opnd;
     
   }
   public E opnd() {return opnd;}
   public void setOpnd(E opnd) {
      this.opnd = opnd;
   }
   public TiposGenerales tipoGeneral(){
      return TiposGenerales.EUNARIA;
   }
   
}
