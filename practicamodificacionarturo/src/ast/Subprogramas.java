package ast;

public abstract class Subprogramas extends Nodo{
	public abstract String toString();
	public TiposGenerales tipoGeneral(){
		return TiposGenerales.SUBPROGRAMAS;
	}
	public abstract tipoSubprograma getTipo();
}