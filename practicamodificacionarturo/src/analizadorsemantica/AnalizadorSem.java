package analizadorsemantica;

import java.util.List;

import ast.*;
public class AnalizadorSem {

	private Nodo raiz;
	private tablaSimbolos tablasimbolos;
	
	public AnalizadorSem(Nodo raiz){
		this.raiz = raiz;
		tablasimbolos = new tablaSimbolos();
	}
	
	public void analizaSem(){
		tablasimbolos.nuevoBloque();
		vinculacion(raiz);
		tablasimbolos.cierraBloque();
		ComprobadordeTipos comprobador = new ComprobadordeTipos();
		if (comprobador.compruebaTipos(raiz)){
			System.out.println("ok");
		}
		else{
			System.out.println("ha habido errores semanticos");

		}
	}

	private void vinculacion(Nodo nodo) {
		switch(nodo.tipoGeneral()){
		case A:
			A programa = (A) nodo;
			for (Subprogramas subprograma :programa.getProgramas()){
				vinculacion(subprograma);
			}
			break;
		
		case SUBPROGRAMAS:
			Subprogramas subprograma = (Subprogramas) nodo;
			switch (subprograma.getTipo()){
			// ver lo de setvarbucle
			case FOR1 : 
					buclefor1 buclefor1 = (buclefor1) subprograma;
					tablasimbolos.nuevoBloque();
					vinculacion(buclefor1.getDecVi()); // ver variable del bucle
					vinculacion(buclefor1.getCondicion());
					vinculacion(buclefor1.getExprPaso());
					vinculacion(buclefor1.getProg());
					tablasimbolos.cierraBloque();
					break;
		
			case FOR2 : 
				buclefor2 buclefor2 = (buclefor2) subprograma;
				tablasimbolos.nuevoBloque();
				// vinculacion(buclefor2.getVariable());
				tablasimbolos.anadeId(buclefor2.getVariable(), ((nombre)buclefor2.getVariable()).getNom());
				nombre identificador = (nombre) buclefor2.getConjunto();
				Nodo for2resul = tablasimbolos.comprobarId(identificador.getNom());
				if( for2resul == null){
					// errortinysemantico;
				}
					// exc
				else{ // ver referencia del conjunto
				identificador.vincula(for2resul);
				vinculacion(buclefor2.getProg());
				tablasimbolos.cierraBloque();
				}
				break;

			
			case WHILE:
				BucleWhile bucleWhile = (BucleWhile) subprograma;
				vinculacion(bucleWhile.getCondicion());
				tablasimbolos.nuevoBloque();
				vinculacion(bucleWhile.getProg());
				tablasimbolos.cierraBloque();

				break;
			case DECVAR: 
				Declaracion dec = (Declaracion) subprograma;
				switch(dec.getTipoDeclaracion()){
				case DECLARACIONCONVI:
					decconVi decconvi = (decconVi) dec;
					vinculacion(decconvi.getTipoAux());
					tablasimbolos.anadeId(decconvi, ((nombre)decconvi.nombre()).getNom() );
					 decconvi.setTipo(tipoPrimitivo(decconvi.getTipoAux())); 
					vinculacion(decconvi.getExpresion2());
					if(((E)decconvi.getExpresion2()).getTipo() ==tipoExpresion.CREACIONVECTOR){
						((creacionVector)decconvi.getExpresion2()).vinculaTipo(tipoBasicoVector((Type)decconvi.getTipoAux()));
					}
					break;
				case DECLARACIONSINVI:
			 		decsinVi decsinvi = (decsinVi) dec;
					vinculacion(decsinvi.getTipoAux());
					tablasimbolos.anadeId(decsinvi, ((nombre)decsinvi.nombre()).getNom() );
					decsinvi.setTipo(tipoPrimitivo(decsinvi.getTipoAux()));
					break; 
				}
				break;
			case ASIGNACION:
				Asignacion asignacion = (Asignacion) subprograma;
				vinculacion(asignacion.getExpresion1());
				vinculacion(asignacion.getExpresion2());
				break;
					
			
			case SWITCH:
				Switch switc = (Switch) subprograma;
				vinculacion(switc.getExpresion());
				// ver variable del switch
				for (ElementosCase cas : switc.getList() ){
					tablasimbolos.nuevoBloque();
					cas.vincula(switc);
					vinculacion(cas);
					tablasimbolos.cierraBloque();
				}
				break;
				
				
			case DECFUNCIONCONRETURN:
				decFuncionConReturn decfuncion = (decFuncionConReturn) subprograma;
				vinculacion(decfuncion.getTipoRetorno());
				decfuncion.setTipoRetorno(tipoPrimitivo(decfuncion.getTipoRetorno()));
				tablasimbolos.anadeId(decfuncion, ((nombre)decfuncion.getNombre()).getNom());
				tablasimbolos.nuevoBloque();
				for (ParametroFuncion param : decfuncion.getListaArgumentos()){
					vinculacion(param);
				} // VER VALOR RETURN fijarlo para comprobacion de tipo
				vinculacion(decfuncion.getCuerpo());
				tablasimbolos.cierraBloque();
				break;
			case DECFUNCIONSINRETURN:
				decFuncionSinReturn decfuncionsinreturn = (decFuncionSinReturn) subprograma;
				tablasimbolos.anadeId(decfuncionsinreturn, ((nombre)decfuncionsinreturn.getNombre()).getNom());
				tablasimbolos.nuevoBloque();
				for (ParametroFuncion param : decfuncionsinreturn.getListaArgumentos()){
					vinculacion(param);
				}
				vinculacion(decfuncionsinreturn.getCuerpo());
				tablasimbolos.cierraBloque();
				break;
			
			case DECTIPO:
				decTipo dectipo = (decTipo) subprograma;
				vinculacion(dectipo.getType());
				tablasimbolos.anadeId(dectipo, ((nombre)dectipo.getNom()).getNom());
				break;
				
		
			case DECSTRUCT:
				decStruct decStruc =  (decStruct) subprograma;
				tablasimbolos.anadeId(decStruc, ((TipoUsuario)decStruc.getNom()).getValor());
				tablasimbolos.nuevoBloque();
				for(Subprogramas declaracion: decStruc.getDeclaraciones()){
					vinculacion(declaracion);
				}
				tablasimbolos.cierraBloque();
				break;
			
			case LLAMADAFUNCIONVOID:
				LlamadaFuncVoid llamada = (LlamadaFuncVoid) subprograma;
				Nodo comprobacion = tablasimbolos.comprobarId(((nombre)llamada.getNombre()).getNom());
				if (comprobacion == null){}// error
				else if( tipoSubprograma.LLAMADAFUNCIONVOID ==((Subprogramas)comprobacion).getTipo()){
					llamada.vincula(comprobacion);
					for(E argumento : llamada.getArgumentos()){
						vinculacion(argumento);
					}
				}					
				else ; // error no es una funcion comprobacion
				break;
			case IFSINELSE: 
				ifSinElse ifsinelse = (ifSinElse) subprograma;
				vinculacion(ifsinelse.getCondicion());
				tablasimbolos.nuevoBloque();
				vinculacion(ifsinelse.getCuerpo());
				tablasimbolos.cierraBloque();
				break;
			case IFCONELSE:
				ifConElse ifconelse = (ifConElse) subprograma;
				vinculacion(ifconelse.getCondicion());
				tablasimbolos.nuevoBloque();
				vinculacion(ifconelse.getCuerpo1());
				tablasimbolos.cierraBloque();
				for(ElseIf elseif: ifconelse.getList()){
					tablasimbolos.nuevoBloque();
					vinculacion(elseif);
					tablasimbolos.cierraBloque();
				}
				tablasimbolos.nuevoBloque();
				vinculacion(ifconelse.getCuerpo2());
				tablasimbolos.cierraBloque();

				break;
			}
		break;
		case ELSEIF:
			ElseIf elseif = (ElseIf) nodo;
			vinculacion(elseif.getCondicion());
			vinculacion(elseif.getCuerpo());
			break;

		case TYPE:
			Type type = (Type) nodo;
			switch(type.getTipo()){
			case VECTOR:
				vinculacion(((decTipoVector)type).getT());
				break;
			case PUNTERO:
				vinculacion(((decTipoPuntero)type).getT());
				break;
			case TIPOUSUARIO:
				TipoUsuario typeuser = (TipoUsuario) type;
				Nodo tipocomprobacion = tablasimbolos.comprobarId(typeuser.getValor());
				if(tipocomprobacion == null){} // error
				else if(tipocomprobacion.tipoGeneral() == TiposGenerales.SUBPROGRAMAS && ((Subprogramas)tipocomprobacion).getTipo() == tipoSubprograma.DECTIPO){
					typeuser.setTipoAntiguo(((decTipo) tipocomprobacion).getType());
					typeuser.vincula(tipocomprobacion);
				}
				break;
			default: break;
			}
			break;
		case PARAMETROFUNCION: 
			ParametroFuncion parametro = (ParametroFuncion) nodo;
			vinculacion(parametro.getTipo());
			// param.setTipo();
			tablasimbolos.anadeId(parametro, ((nombre) parametro.getNombre()).getNom());
			break;
		case EUNARIA:// ver enums
			EUn eunaria = (EUn) nodo;
			vinculacion(eunaria.opnd());
			break;
		

		case ELEMENTOSCASE:	// ver otra condicion para no vincular la expresion
			ElementosCase elemcase = (ElementosCase) nodo;
			if(((nombre)elemcase.getExpresion()).getNom() != "default"){
				vinculacion(elemcase.getExpresion());
			}
			tablasimbolos.nuevoBloque();
			vinculacion(elemcase.getPrograma());
			tablasimbolos.cierraBloque();
			break;
			
		case EBINARIA: // distinguir si el oerador1 es el punto
			EBin ebinaria = (EBin) nodo;
			vinculacion(ebinaria.opnd1());
			vinculacion(ebinaria.opnd2());// ver lo de los enums
			break;
		
		case E:
			E expresion = (E) nodo;
			switch(expresion.getTipo()){			
			case NOMBRE:
			nombre nom = (nombre)expresion;
			Nodo nodonom =tablasimbolos.comprobarId(nom.getNom());
			if( nodonom == null){
				// error
			}
			else{
				nom.vincula(nodonom);
				if(nodonom.tipoGeneral() == TiposGenerales.SUBPROGRAMAS){
					if(((Subprogramas)nodonom).getTipo() == tipoSubprograma.DECVAR){
						nom.setTipoVinculacion(((Declaracion) nodonom).getTipoAux());
					}
				}
				else if(nodonom.tipoGeneral() == TiposGenerales.PARAMETROFUNCION ){
					nom.setTipoVinculacion(((ParametroFuncion) nodonom).getTipo());
				}
				
			}
			break;
			case LLAMADAFUNCRETURN: 
				LlamadaFuncReturn llamada = (LlamadaFuncReturn) expresion;
				Nodo nodofun = tablasimbolos.comprobarId(((nombre)llamada.getNombre()).getNom());
				if(nodofun == null){
					// error
				}
				else if(((decFuncionConReturn) nodofun).getTipo() == tipoSubprograma.DECFUNCIONCONRETURN){ 
					llamada.vincula(nodofun);
					// ver tipo de return llamada.setTipo(((LlamadaFuncReturn) nodollamada).());
					for(E argumentos: llamada.getArgumentos()){
						vinculacion(argumentos);
					}
				}
				else{ // error de tipos
				}
				
			break;
			
			case CREACIONVECTOR :
				creacionVector cvector = (creacionVector) expresion;
				vinculacion(cvector.getExp1());
			
				vinculacion(cvector.getExp2());

			break;
			case CREACIONVECTOR2 :
				creacionVector2 cvector2 = (creacionVector2) expresion;
				List<E> l = cvector2.getVec();
				for(E expr: l){
					vinculacion(expr);
				}
			
			break;
			default: // error
				
			}
		break;
		
		
		}
	}

	
	private Type tipoBasicoVector(Type tipoAux) {
		if(tipoAux.getTipo() == enumtipos.VECTOR){
			tipoBasicoVector(((decTipoVector) tipoAux).getT());
			
		}
		else if(tipoAux.getTipo() == enumtipos.PUNTERO){
			tipoBasicoVector(((decTipoPuntero) tipoAux).getT());
		}
		else{
			return tipoAux;
		}
		return tipoAux;
	}

	private Type tipoPrimitivo(Type type) {// ver esto
		if(type.getTipo() == enumtipos.VECTOR){
			((decTipoVector) type).setT(tipoPrimitivo(((decTipoVector)type).getT()));
			return type;
	
		}
		else if(type.getTipo() == enumtipos.PUNTERO){
			((decTipoPuntero) type).setT(tipoPrimitivo(((decTipoPuntero)type).getT()));
			return type;

		}
		else if(type.getTipo() == enumtipos.TIPOUSUARIO&& ((TipoUsuario)type).getTipoAntiguo() != null) {
			
			return conversionTipo(tipoPrimitivo(((TipoUsuario)type).getTipoAntiguo()));
		}
			else{
		
			return type;
		}
	}

	
	private Type conversionTipo(Type type){
		if(type.getTipo()==enumtipos.TIPOUSUARIO &&((TipoUsuario)type).getTipoAntiguo() != null ){
			return tipoPrimitivo(((TipoUsuario)type).getTipoAntiguo() );
		}
		
		else{
			return type;
		}
	}

}

		
			
		
