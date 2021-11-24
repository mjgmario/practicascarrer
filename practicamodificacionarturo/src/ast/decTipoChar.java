package ast;

public class decTipoChar extends Type{
	private char valor;
	@Override
	public String toString() {
		return Character.toString(valor);
	}
	public enumtipos getTipo() {
		return enumtipos.CHAR;
	}

}
