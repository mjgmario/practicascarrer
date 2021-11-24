package ast;

public class decTipoBool extends Type{
	boolean valor;
	@Override
	public String toString() {
		
		if (valor == true)
			return "True";
		else return "False";
	}
	public enumtipos getTipo() {
		return enumtipos.BOOL;
	}

}
