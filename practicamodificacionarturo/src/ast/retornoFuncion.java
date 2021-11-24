package ast;

public class retornoFuncion extends A{
	private A cuerpo;
	private E expresionRetorno;
	int fila, columna;
	public retornoFuncion(A cuerpo, E expresionRetorno, int fila, int columna) {
		super();
		this.cuerpo = cuerpo;
		this.expresionRetorno = expresionRetorno;
		this.fila = fila;
		this.columna = columna;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return expresionRetorno.toString() + "\n" + cuerpo.toString();
	}

	public E getExpresionRetorno(){
		return expresionRetorno;
	}
}
