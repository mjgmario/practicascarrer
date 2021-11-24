package generacioncodigo;

import java.util.ArrayList;

public class calculodesignadorauxiliar {
	private int dirVector;
	private ArrayList<Integer> listaDim;
	private int indice;
	private int calcauxdirVector;
	public int getCalcauxdirVector() {
		return calcauxdirVector;
	}
	public void setCalcauxdirVector(int calcauxdirVector) {
		this.calcauxdirVector = calcauxdirVector;
	}
	public void inicalcauxdirvector(){
		int resul =1; 
		for(int i = 0; i < listaDim.size(); ++i){
			resul *= listaDim.get(i).intValue();
		}
		calcauxdirVector =resul;
	}
	public calculodesignadorauxiliar() {
		super();
	}
	public calculodesignadorauxiliar(int dirVector,
			ArrayList<Integer> listaDim, int indice) {
		super();
		this.dirVector = dirVector;
		this.listaDim = listaDim;
		this.indice = indice;
	}
	
	public int getDirVector() {
		return dirVector;
	}
	public void setDirVector(int dirVector) {
		this.dirVector = dirVector;
	}
	public ArrayList<Integer> getListaDim() {
		return listaDim;
	}
	public void setListaDim(ArrayList<Integer> listaDim) {
		this.listaDim = listaDim;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
}
