package ast;

import java.util.ArrayList;

public class decFuncionConReturn extends Subprogramas{
	private Type tipoRetorno;
	private E nombre;
	private ArrayList<ParametroFuncion>listaArgumentos;
	private retornoFuncion cuerpo;
	int fila, columna;
	public decFuncionConReturn(E nombre, ArrayList<ParametroFuncion> listaArgumentos,
	retornoFuncion cuerpo, int fila, int columna, Type tipoRetorno) {
		this.nombre = nombre;
		this.listaArgumentos = listaArgumentos;
		this.cuerpo = cuerpo;
		this.fila = fila;
		this.columna = columna;
		this.tipoRetorno = tipoRetorno;
	}
	public void setTipoRetorno(Type tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
	public Type getTipoRetorno() {
		return tipoRetorno;
	}
	public E getNombre() {
		return nombre;
	}
	public ArrayList<ParametroFuncion> getListaArgumentos() {
		return listaArgumentos;
	}
	public A getCuerpo() {
		return cuerpo;
	}
	public E getValueReturn(){
		return cuerpo.getExpresionRetorno();
	}
	public String toString() {
		String result = nombre.toString() + "(";
		for(int i = listaArgumentos.size()-1; i >= 0 ; --i)
			result = result +" " + listaArgumentos.get(i).toString();
		return result + ")" + cuerpo.toString();
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.DECFUNCIONCONRETURN;
	}
	
	
}
