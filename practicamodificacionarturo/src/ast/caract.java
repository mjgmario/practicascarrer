package ast;

public class caract extends E {
	private String valor;
	public caract( String valor, int fila, int columna) {
		super(fila,columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.CARACT;
	}
}
