package ast;

public class decsinVi extends Declaracion {
	
	public decsinVi(Type tipo, E nombre, int fila, int columna) {
		super(tipo, nombre, fila, columna);
		
	}
	public String toString() {
		return nombre().toString();
	}
	public tipoDeclaracion getTipoDeclaracion() {
		
		return tipoDeclaracion.DECLARACIONSINVI;
	}
}
