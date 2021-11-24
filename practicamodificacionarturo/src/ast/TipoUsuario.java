package ast;

public class TipoUsuario extends Type{
	private String valor;
    private int fila;
    private int columna;    
	private Nodo vinculacion;
	private Type tipoAntiguo;
    public TipoUsuario (String valor,int fila,int columna){
        this.valor = valor;
        this.fila=fila;
        this.columna=columna;
    }
	public String toString() {
		return valor;
	}
    public Nodo getVinculacion() {
		return vinculacion;
	}
	public String getValor() {
		return valor;
	}
	public enumtipos getTipo() {
		return enumtipos.TIPOUSUARIO;
	}
	public void vincula(Nodo tipocomprobacion) {
		this.vinculacion = tipocomprobacion;
	}
	 public Type getTipoAntiguo() {
			return tipoAntiguo;
	}
	public void setTipoAntiguo(Type tipoAntiguo) {
		this.tipoAntiguo = tipoAntiguo;
	}
}