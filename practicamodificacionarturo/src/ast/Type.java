package ast;

public abstract class Type extends Nodo{
	
	public abstract String toString();
	public TiposGenerales tipoGeneral(){
		return TiposGenerales.TYPE;
	}
	public abstract enumtipos getTipo();
}