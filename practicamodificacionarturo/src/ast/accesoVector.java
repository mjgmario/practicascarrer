package ast;

import java.util.List;

public class accesoVector extends E {
	private E nombre;
	private List<AccesoVectorParam> list;
	public accesoVector( E nombre,List<AccesoVectorParam> list, int fila, int columna) {
		super(fila, columna);
		this.nombre = nombre;
		this.list = list;
		
	}
	@Override
	public String toString() {
		String str = "";
		for(int i = list.size()-1; i >= 0; --i)
			str = str + list.get(i).toString();
		str = nombre.toString() + str;
		return str;
	}
	public E getNombre() {
		return nombre;
	}
	public List<AccesoVectorParam> getList() {
		return list;
	}
	public tipoExpresion getTipo() {
		return tipoExpresion.ACCESOVECTOR;
	}
	public void setList(List<AccesoVectorParam> list) {
		this.list = list;
	}
}
