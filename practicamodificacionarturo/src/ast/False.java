package ast;

public class False extends E{

	public False(int fila, int columna) {
		super(fila, columna);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "False";
	}
	
	public tipoExpresion getTipo() {
		
		return tipoExpresion.FALSE;
	}
	
}
