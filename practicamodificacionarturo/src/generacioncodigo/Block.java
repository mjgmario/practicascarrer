package generacioncodigo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Block {
	private Map<String, Integer> tamanoTipos = new HashMap<>();
	private Map<String, Integer> nombres = new HashMap<>();
	private Map<String, List<Integer>> dimensionesVect = new HashMap<>();
	private int posicion;
	private Block predecesor; 
	private int  siguienteDireccion ; // nos indica la delta que llevamos 
	private int tamBloque;
	public Block ( int posicion, Block predecesor){
		this.posicion = posicion;
		this.predecesor = predecesor;
		this.siguienteDireccion = predecesor.siguienteDireccion;
	
	}
	public Map<String, Integer> getTamanoTipos() {
		return tamanoTipos;
	}
	
	public int getTamBloque() {
		return tamBloque;
	}

	
	public Map<String, Integer> getNombres() {
		return nombres;
	}
	public Map<String, List<Integer>> getDimensionesVect() {
		return dimensionesVect;
	}
	public int getPosicion() {
		return posicion;
	}
	public Block getPredecesor() {
		return predecesor;
	}
	
	public int getSiguienteDireccion() {
		return siguienteDireccion;
	}
	public void insertId(int tamano, String id){
		nombres.put(id, siguienteDireccion);
		tamBloque += tamano;
		siguienteDireccion += tamano;
		
	}
	public void insertType(int tamano, String type){
		nombres.put(type, tamano);
	}
	public int tamTipo(String id){
		if(tamanoTipos.containsKey(id)) return tamanoTipos.get(id);
		else if(predecesor != null)return predecesor.tamTipo(id);
		else return -5;
	}
	public int direccionVariable(String id){
		if(nombres.containsKey(id)) return nombres.get(id);
		else if(predecesor != null){
			return predecesor.direccionVariable(id);
		}
		else return -5;
	}
	public void insertParamStruct(int direccionVariable, String nom) {
		nombres.put(nom, direccionVariable);

		
	}
}




