package ast;

public class decTipoDouble extends Type{
	private double valor;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Double.toString(valor);
	}
	public enumtipos getTipo() {
		return enumtipos.DOUBLE;
	}

}
