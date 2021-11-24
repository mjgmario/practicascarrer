package ast;

import java.util.ArrayList;

public class decFuncionSinReturn extends Subprogramas{
	private E nombre;
	private ArrayList<ParametroFuncion>listaArgumentos;
	private A cuerpo;
	int fila, columna;
	public decFuncionSinReturn(E nombre, ArrayList<ParametroFuncion> listaArgumentos,
			A cuerpo, int fila, int columna) {
		super();
		this.nombre = nombre;
		this.listaArgumentos = listaArgumentos;
		this.cuerpo = cuerpo;
		this.fila = fila;
		this.columna = columna;
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
	public String toString() {
		String result = nombre.toString() + "(";
		for(int i = listaArgumentos.size()-1; i >= 0 ; --i)
			result = result +" " + listaArgumentos.get(i).toString();
		return result + ")" + cuerpo.toString();
	}
	public tipoSubprograma getTipo() {
		return tipoSubprograma.DECFUNCIONSINRETURN;
	}
	
}
