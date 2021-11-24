package ast;

public class True extends E{
	
private int fila, columna;

public True(int fila, int columna) {
	super(fila, columna);
	
}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "True";
	}
	public tipoExpresion getTipo() {
	
		return tipoExpresion.TRUE;
	}

}
