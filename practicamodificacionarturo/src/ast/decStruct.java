package ast;

import java.util.ArrayList;

public class decStruct extends Subprogramas {
	
	private ArrayList<Subprogramas> declaraciones;
	private Type nom;
	private int fila, columna;
	public decStruct(ArrayList<Subprogramas> declaraciones, Type nom, int fila,
			int columna) {
		super();
		this.declaraciones = declaraciones;
		this.nom = nom;
		this.fila = fila;
		this.columna = columna;
	}
	public String toString() {
		String result = "Struct " + nom.toString() + "\n";
		for(int i = declaraciones.size()-1; i >= 0 ; --i)
			result = result + " " +declaraciones.get(i).toString();
		return result;
	}
	public tipoSubprograma getTipo() {
		
		return tipoSubprograma.DECSTRUCT;
	}
	public ArrayList<Subprogramas> getDeclaraciones() {
		return declaraciones;
	}
	public Type getNom() {
		return nom;
	}
	
}
