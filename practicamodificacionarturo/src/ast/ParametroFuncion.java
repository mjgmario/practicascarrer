package ast;

public class ParametroFuncion extends Nodo{
	private Boolean porReferencia;
	private Type tipo;
	private E nombre;
	private int fila, columna;
	public ParametroFuncion(Boolean porReferencia, Type tipo, E nombre,
			int fila, int columna) {
		super();
		this.porReferencia = porReferencia;
		this.tipo = tipo;
		this.nombre = nombre;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		if(porReferencia)
			return "&" + nombre.toString() ;
		else return nombre.toString() ;
	   }
	public Boolean getPorReferencia() {
		return porReferencia;
	}
	public Type getTipo() {
		return tipo;
	}
	public E getNombre() {
		return nombre;
	}
	public void setPorReferencia(Boolean porReferencia) {
		this.porReferencia = porReferencia;
	}
	public void setTipo(Type tipo) {
		this.tipo = tipo;
	}
	public void setNombre(E nombre) {
		this.nombre = nombre;
	}
	public TiposGenerales tipoGeneral() {
		return TiposGenerales.PARAMETROFUNCION;
	}
}
