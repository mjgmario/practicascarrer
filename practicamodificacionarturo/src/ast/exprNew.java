package ast;

public class exprNew extends E {
	private boolean esNull;
	private Type type;
	private int tam;
	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public exprNew(boolean esNull, Type type,  int fila,int columna){
		super(fila, columna);
		this.esNull = esNull;
		this.type = type;
		
	}

	public boolean isEsNull() {
		return esNull;
	}

	public void setEsNull(boolean esNull) {
		this.esNull = esNull;
	}
	
	public String toString() {
		if(esNull)
			return "NULL";
		else return "NEW " + type.toString(); 
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.EXPRNEW;
	}

}
	
		
	

