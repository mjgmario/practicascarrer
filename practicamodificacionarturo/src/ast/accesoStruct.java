package ast;

import java.util.List;
import alex.*;

public class accesoStruct extends EBin {
	private Type t;
	

	public accesoStruct(E nombre,E expr, int fila, int columna) {
		super(nombre, expr, fila, columna);
		
		
	}
	
	public String toString() {
		return opnd2().toString() + "."+ ((nombre)opnd1()).toString();
	}
	public tipoExpresion getTipo() {
		
		return tipoExpresion.ACCESOSTRUCT;
	}

	public Type getT() {
		return t;
	}

	public void setT(Type t) {
		this.t = t;
	}
	
}
