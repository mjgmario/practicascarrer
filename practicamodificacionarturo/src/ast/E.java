package ast;

public abstract class E extends Nodo{
   private int fila;
   private int columna;
   public int getFila(){return fila;}
   public int getColumna(){return columna;}
   public E(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
   public abstract tipoExpresion getTipo() ;
   public abstract String toString();
   public TiposGenerales tipoGeneral(){
		return TiposGenerales.E;
	}
}
