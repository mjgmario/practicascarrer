package analizadorsemantica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Nodo;
public class tablaSimbolos {

	private List<Map<String, Nodo>> lista;
	public tablaSimbolos(){
		this.lista = new ArrayList<>();
	}
	public void anadeId(Nodo nodo, String nombre){
		boolean estaya = lista.get(lista.size()-1).containsKey(nombre);
		if(estaya){
			lista.get(lista.size()-1).remove(nombre); // actualiza con la ultima declaracion
			lista.get(lista.size()-1).put(nombre, nodo);
			
		}
		else{
			lista.get(lista.size()-1).put(nombre, nodo);
		}
	}
	public void nuevoBloque(){
		Map mapa = new HashMap<>();
		lista.add(mapa);
	}
	public void cierraBloque(){
		lista.remove(lista.size() -1);
	}
	public Nodo comprobarId(String nombre){
		int i = lista.size() -1;
		while (i >= 0){
			if(lista.get(i).containsKey(nombre)) return lista.get(i).get(nombre);
			--i;
		}
		return null;
	}
}
	