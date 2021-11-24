package generacioncodigo;

import java.util.ArrayList;
import java.util.List;

import ast.*;

/**
 * @author usuario
 *
 */
public class asignadirEstaticas {
	private static List<Block> listaDeBloques = new ArrayList<>();
	private Block actualBloque;
	
	public static List<Block> getListaDeBloques() {
		return listaDeBloques;
	}
	public Block getActualBloque() {
		return actualBloque;
	}
	public void asignadireccionesaux(Nodo nodo){
		switch(nodo.tipoGeneral()){
		case A:
			A programa = (A) nodo;
			abrirAmbito();
			
			for(Subprogramas subprograma: programa.getProgramas()){
				asignadireccionesaux(subprograma);
				
			}
			cerrarAmbito();
			break;
		case PARAMETROFUNCION:
			
			break;
		case ELEMENTOSCASE:
			ElementosCase elemcase = (ElementosCase) nodo;
			asignadireccionesaux(elemcase.getPrograma());
			break;
		case SUBPROGRAMAS: 
			Subprogramas subprograma = (Subprogramas) nodo;
			switch (subprograma.getTipo()){
			case FOR1 :
				buclefor1 buclefor1 = (buclefor1) subprograma;
				abrirAmbito();
				asignadireccionesaux(buclefor1.getDecVi());
				asignadireccionesaux(buclefor1.getProg());
				cerrarAmbito();
			 break;
		
			case FOR2 : 
				buclefor2 buclefor2 = (buclefor2) subprograma;
				abrirAmbito();

				asignadireccionesaux(buclefor2.getVariable());
				asignadireccionesaux(buclefor2.getProg());
				cerrarAmbito();

				break;
			
			case WHILE:
				BucleWhile bucleWhile = (BucleWhile) subprograma;
				asignadireccionesaux(bucleWhile.getProg());
				

				break;
			case DECVAR: 
				Declaracion dec = (Declaracion) subprograma;
				switch(dec.getTipoDeclaracion()){
				case DECLARACIONCONVI:
					decconVi decconvi = (decconVi) dec;
					switch(decconvi.getT().getTipo()){
					case PUNTERO:
						if(decconvi.getExpresion2().getTipo() == tipoExpresion.EXPRNEW){ 
							insertarId(4, ((nombre)decconvi.nombre()).getNom()); 
							exprNew nuevo = ((exprNew)decconvi.getExpresion2());
							if(((decTipoPuntero)decconvi.getT()).getT().getTipo() == enumtipos.VECTOR){
								// ver caso vector
							}
							else if(((decTipoPuntero)decconvi.getT()).getT().getTipo() == enumtipos.TIPOUSUARIO){ // ver lo del nombre
								nuevo.setTam(tamTipo(((TipoUsuario) ((decTipoPuntero)decconvi.getT()).getT()).getValor() ));
							}
							else{
								nuevo.setTam(4);
							}
						}
						else{
							
						}
						break;
					case TIPOUSUARIO: // funcion insertadir incompleta
						insertarId(tamTipo(((TipoUsuario)decconvi.getT()).getValor()), (((nombre) decconvi.nombre()).getNom()));
						insertaDirEnStruct(actualBloque.direccionVariable((((nombre) decconvi.nombre()).getNom())), decconvi);
						break;
					case VECTOR: // calculodim es una funcion no realizada que devuelve en un vector de integers
						// las respectivas dimension de dicho vector
						// actualBloque.insertDimVector(((nombre)decconvi.nombre()).getNom(), calculodimVector(decconvi.getT(), decconvi.getExpresion2()));
						// int tamano =calcTamanoVector(decconvi.getT(), decconvi.getExpresion2());
						// insertType(tamano, ((nombre)decconvi.nombre()).getNom());
						// insertarId(tamano, ((nombre)decconvi.nombre()).getNom() );
						break;
							
						}
					break;
				case DECLARACIONSINVI:
			 		decsinVi decsinvi = (decsinVi) dec;
			 		switch(decsinvi.getTipoAux().getTipo()){
					
					case TIPOUSUARIO:
						insertarId(tamTipo(((TipoUsuario)decsinvi.getTipoAux()).getValor()), decsinvi.getTipoAux().toString());
					default:
						break;
						
										
							
						}
					break; 
				}
			case ASIGNACION:
				
				break;
					
			
			case SWITCH:
			// convertir switch
				break;
				
				
			case DECFUNCIONCONRETURN:
				decFuncionConReturn decfuncion = (decFuncionConReturn) subprograma;
				abrirAmbito();
				// ver lo de pa 
				for(ParametroFuncion param:decfuncion.getListaArgumentos() ){
					
				
					asignadireccionesaux(param);
				}
				asignadireccionesaux(decfuncion.getCuerpo());
				cerrarAmbito();
				break;
			case DECFUNCIONSINRETURN:
				decFuncionSinReturn decfuncionsinreturn = (decFuncionSinReturn) subprograma;
				// ver pa
				abrirAmbito();

				for(ParametroFuncion param:decfuncionsinreturn.getListaArgumentos() ){
					asignadireccionesaux(param);
				}
				asignadireccionesaux(decfuncionsinreturn.getCuerpo());
				cerrarAmbito();
				break;
			
			case DECTIPO:
				break;
				
		
			case DECSTRUCT:
				decStruct decStruc =  (decStruct) subprograma;
				int calculotamano = 0;
				for ( Subprogramas dec1 :decStruc.getDeclaraciones()){
					Declaracion declaracionStr = (Declaracion) dec1;
					switch(declaracionStr.getTipoDeclaracion()){
					case DECLARACIONCONVI:
						decconVi declaracionStrVI = (decconVi) dec1;
						switch(declaracionStrVI.getT().getTipo()){
						case PUNTERO:
							if(declaracionStrVI.getExpresion2().getTipo() == tipoExpresion.EXPRNEW){ 
								insertarId(4, ((nombre)declaracionStrVI.nombre()).getNom()); 
								exprNew nuevo = ((exprNew)declaracionStrVI.getExpresion2());
								if(((decTipoPuntero)declaracionStrVI.getT()).getT().getTipo() == enumtipos.VECTOR){
									// ver caso vector
								}
								else if(((decTipoPuntero)declaracionStrVI.getT()).getT().getTipo() == enumtipos.TIPOUSUARIO){ // ver lo del nombre
									nuevo.setTam(tamTipo(((TipoUsuario) ((decTipoPuntero)declaracionStrVI.getT()).getT()).getValor() ));
								}
								else{
									nuevo.setTam(4);
								}
							}
							else{
								calculotamano +=4;
							}
							break;
						case TIPOUSUARIO:
							calculotamano += tamTipo(((TipoUsuario) declaracionStrVI.getTipoAux()).getValor());
							break;
						case VECTOR:
							// funcion no realizado el calcTamanoVector
								
							// int caculovectoraux = calcTamanoVector( declaracionStrVI.getT(), declaracionStrVI.getExpresion2() ) ;
							// calculotamano += caculovectoraux;
							
							// insertType(caculovectoraux, ((nombre)decStruc.nombre()).getNom() + "."+((nombre)declaracionStrVI.nombre()).getNom() );
							break;
								
							}
						break;
					case DECLARACIONSINVI:
				 		decsinVi declaracionStrSinVI = (decsinVi) dec1;
				 		switch(declaracionStrSinVI.getTipoAux().getTipo()){
						
						case TIPOUSUARIO:
							calculotamano += tamTipo(((TipoUsuario) declaracionStrSinVI.getTipoAux()).getValor());
							break;
											
								
							}
						break; 
					}				
					
				}
				insertType(calculotamano,  decStruc.getNom().toString());
				
				
				break;
			
			case LLAMADAFUNCIONVOID:
				break;
			case IFSINELSE: 
				ifSinElse ifsinelse = (ifSinElse) subprograma;
				asignadireccionesaux(ifsinelse.getCuerpo());
				break;
			case IFCONELSE:
				ifConElse ifconelse = (ifConElse) subprograma;
				asignadireccionesaux(ifconelse.getCuerpo1());
				for(ElseIf elseif: ifconelse.getList()){
					asignadireccionesaux(elseif.getCuerpo());
				}
				asignadireccionesaux(ifconelse.getCuerpo2());

				break;
			
			}
			
		}
	}

		// funcion en incompleta que inserta va insertando en la declaracion 
		// los parametros de una declaraccion de struct, asi asigna la memoria 
		// que ocumpa dicho struct, sirve de auxiliar para cuando utilicemos struct
		// saber de anytemano su tamano
	private void insertaDirEnStruct(int direccionVariable, Declaracion declaracion) {
		nombre nombretipoaux= ((nombre) declaracion.nombre());
		switch(declaracion.getTipoAux().getTipo()){
		case VECTOR:
			
			break;
		case TIPOUSUARIO:
			for(Subprogramas dec: ((decStruct) ((TipoUsuario) declaracion.getTipoAux()).getVinculacion()).getDeclaraciones()){
				Declaracion decaux = (Declaracion) dec;
				// codigo para meter tamano y volver a llamar a a funcion por si no es un caso basico
			}
			actualBloque.insertType(tamTipo(((TipoUsuario)declaracion.getTipoAux()).getValor()), ((nombre)declaracion.nombre()).getNom());

			break;
		
		default:
			actualBloque.insertParamStruct(direccionVariable, nombretipoaux.getNom() );
		}
		// TODO Auto-generated method stub
		
	}
	private void abrirAmbito() {
		// TODO Auto-generated method stub
		Block bloque = new Block( listaDeBloques.size(),actualBloque);
		listaDeBloques.add(bloque);
		actualBloque = bloque;
	}
	private void cerrarAmbito(){
		
		actualBloque = actualBloque.getPredecesor();
		
	}
	private void insertType(int tam, String nombre){
		actualBloque.insertType(tam, nombre);
	}
	private int tamTipo(String type){
		return actualBloque.tamTipo(type);
	}
	private void insertarId(int tamano, String id){
		actualBloque.insertId(tamano, id);
	}
	public int calculomemEstatica(){
		int resul = 0;
		for(int i =0 ; i < listaDeBloques.size(); ++i){
			resul +=listaDeBloques.get(i).getTamBloque();
		}
		return resul;
	}

}
