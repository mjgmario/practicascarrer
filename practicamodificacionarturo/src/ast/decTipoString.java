package ast;

public class decTipoString extends Type{
	private String valor;
	@Override
	public String toString() {
		return valor;
	}
	public enumtipos getTipo() {
		return enumtipos.STRING;
	}

}
