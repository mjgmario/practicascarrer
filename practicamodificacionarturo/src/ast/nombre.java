package ast;


public class nombre extends E {
	private int fila, columna;
	private String nom;
	private Nodo vinculacion;

	private Type tipoVinculacion;
	public nombre( String nom,int fila, int columna) {
		super(fila,columna);
		this.nom = nom;
		this.vinculacion = null;
	}
	public String toString() {
		return nom;
	}public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
	public Nodo getVinculacion() {
		return vinculacion;
	}
	public void setVinculacion(Nodo vinculacion) {
		this.vinculacion = vinculacion;
	}
	public String getNom() {
		return nom;
	}
	
	public void vincula(Nodo nodo) {
		this.vinculacion = nodo;
	}
	public void setTipoVinculacion(Type tipoVinculacion) {
		this.tipoVinculacion = tipoVinculacion;
	}
	public Type getTipoVinculacion() {
		return tipoVinculacion;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.NOMBRE;
	}

}
