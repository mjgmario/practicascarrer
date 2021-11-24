package ast;

public class decTipoInt extends Type{
	private int valor;
	@Override
	public String toString() {
		return Integer.toString(valor);
	}
	public enumtipos getTipo() {
		return enumtipos.INT;
	}

}
