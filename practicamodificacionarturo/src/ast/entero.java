package ast;

public class entero extends E {
	private String valor;
	public entero( String valor, int fila, int columna) {
		super(fila,columna);
		this.valor = valor;
	}

	public String toString() {
		return valor;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.ENTERO;
	}
}
