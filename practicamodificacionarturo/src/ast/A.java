package ast;

import java.util.ArrayList;
import java.util.List;

public class A extends Nodo{
	ArrayList<Subprogramas>programas;

	public A() {
		this.programas = new ArrayList<Subprogramas>();
	}
	public void codigo(Subprogramas prog){
		programas.add(prog);
	}
	public String toString(){
		String result = "{\n";
		for(int i = programas.size()-1; i >=0; --i)
			result = result +  programas.get(i).toString() +" \n";
		result = result + "}\n";
		return result;
	}

	public ArrayList<Subprogramas> getProgramas() {
		return programas;
	}
	public TiposGenerales tipoGeneral() {
		return TiposGenerales.A ;
	}

}