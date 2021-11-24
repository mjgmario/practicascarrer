package ast;

public class decimal extends E {
	private String valor;
	public decimal( String valor, int fila, int columna) {
		super(fila,columna);
		this.valor = valor;
	}

	public String toString() {
		return valor;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.DECIMAL;
	}
}
