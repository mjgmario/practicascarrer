package analizadorsemantica;
import java.util.List;

import ast.*;

public class ComprobadordeTipos {
	public  boolean  compruebaTipos(Nodo nodo){
		switch(nodo.tipoGeneral()){
		case A:
			A programa = (A) nodo;
			boolean ok = true;
			for (Subprogramas subprograma :programa.getProgramas()){
				ok = compruebaTipos(subprograma) && ok;
			}
			return ok;
		
		
		case SUBPROGRAMAS: 
			Subprogramas subprograma = (Subprogramas)nodo;
			switch(subprograma.getTipo()){
			case FOR1:
				boolean ok1 = true;
				buclefor1 for1 = (buclefor1)subprograma;
				if(enumtipos.BOOL != comprobacionaux(for1.getCondicion()).getTipo() ){
					// error;
					ok1 =  false;
				}
				ok1 = compruebaTipos(for1.getExprPaso()) && ok1;
				return compruebaTipos(for1.getProg()) && ok1;
			
			case FOR2:
				boolean ok2 = true;
				buclefor2 for2 = (buclefor2)subprograma; // solo vectores de una dim
				if(enumtipos.VECTOR != comprobacionaux(for2.getConjunto()).getTipo() || ((decTipoVector)comprobacionaux(for2.getConjunto())).getT() != comprobacionaux(for2.getVariable())){
					// error en la expresion ;
					ok2 = false;
				}
				else 
					
				return compruebaTipos(for2.getProg()) && ok2;
			case WHILE:
				BucleWhile buclewhile = (BucleWhile)subprograma; 
				boolean ok11 = true;
				if(comprobacionaux(buclewhile.getCondicion()).getTipo() !=enumtipos.BOOL ){
					// error
					ok11 = false;
				}
				ok11 =  compruebaTipos(buclewhile.getProg()) && ok11;
				return ok11;
				
				
		case DECVAR:// meter vectores
			Declaracion dec = (Declaracion) subprograma;
			switch(dec.getTipoDeclaracion()){
			case DECLARACIONCONVI:
				decconVi decconvi = (decconVi) dec;
				if (decconvi.getT() == comprobacionaux(decconvi.getExpresion2())){
					return true; // VER ESTO
				}
				else{}
					// error
				break;
			case DECLARACIONSINVI:
		 		return true;
			}
			break;
		case ASIGNACION:
			Asignacion asignacion = (Asignacion) subprograma; 
			if(compruebaaux2(comprobacionaux(asignacion.getExpresion1()),comprobacionaux(asignacion.getExpresion2()) )){
				// error
			}else{
				return true;
			}
		break;
		case SWITCH:
			boolean comprSwitch = true;
			Switch switc = (Switch) subprograma;
			Type type = comprobacionaux(switc.getExpresion());
			switc.settExpresion(type); // fija el tipo de la expresion, ahora en case cokmprobaremos
			// solo permtimos el switch para tipos basicos, como c
			if(type.getTipo()!= enumtipos.BOOL && type.getTipo()!= enumtipos.CHAR && type.getTipo()!= enumtipos.INT && type.getTipo()!= enumtipos.DOUBLE){
				// ERROR
			}
			else{
				for(ElementosCase cases: switc.getList()){
					comprSwitch = compruebaTipos(cases) && comprSwitch;
					
				}
			}
			return comprSwitch;
		case DECFUNCIONCONRETURN:
			decFuncionConReturn decfuncion = (decFuncionConReturn) subprograma;
			if(compruebaaux2(comprobacionaux(decfuncion.getValueReturn()), decfuncion.getTipoRetorno())){
				return compruebaTipos( decfuncion.getCuerpo()); // ver 
			}
			else // error
			
			break; 
		case DECFUNCIONSINRETURN:
			decFuncionSinReturn decfuncionSinRet = (decFuncionSinReturn) subprograma;
			return compruebaTipos( decfuncionSinRet.getCuerpo());
		case DECTIPO:
			return true;
		case LLAMADAFUNCIONVOID:
			boolean comprLlamadavoid = true;
			LlamadaFuncVoid llamada = (LlamadaFuncVoid) subprograma;
			List<ParametroFuncion> lista = ((decFuncionSinReturn) llamada.getVinculacionresul()).getListaArgumentos();
			if(llamada.getArgumentos().size()!= lista.size()){
				// error
				comprLlamadavoid = false;
			}
			else{
				int i = 0; 
				while (i< llamada.getArgumentos().size()){
					
					if(compruebaaux2(lista.get(i).getTipo(),comprobacionaux(llamada.getArgumentos().get(i)))) {
						comprLlamadavoid = false;
						}
					++i;
				}
			}
			return comprLlamadavoid;

		case DECSTRUCT:
			decStruct decStruc =  (decStruct) subprograma;
			boolean comprStruct = true;
			for(Subprogramas declaracion :decStruc.getDeclaraciones() ){
				if(!compruebaTipos(declaracion)){
					comprStruct = false;
				}
			}
			return comprStruct;
		case IFSINELSE:  
			ifSinElse ifsinelse = (ifSinElse) subprograma;
			boolean comprIfSinElse = true;
			if(comprobacionaux(ifsinelse.getCondicion()).getTipo() != enumtipos.BOOL){ 
				// error
				comprIfSinElse = false;
			}
			else{
				if(!compruebaTipos(ifsinelse.getCuerpo())){
					// error
					comprIfSinElse = false;
				}
				}
			
			return comprIfSinElse;
		case IFCONELSE:
			ifConElse ifconelse = (ifConElse) subprograma;
			boolean comprIfConElse = true;
			if(comprobacionaux(ifconelse.getCondicion()).getTipo() != enumtipos.BOOL){ 
				// error
				comprIfConElse = false;
			}
			else{
				if(!compruebaTipos(ifconelse.getCuerpo1())){
					// error
					comprIfConElse = false;
				}
				for(ElseIf elses : ifconelse.getList() ){
					if(comprobacionaux(elses.getCondicion()).getTipo() != enumtipos.BOOL){ 
						// error
						comprIfConElse = false;
					}
					else{
						if(!compruebaTipos(elses.getCuerpo())){
							// error
							comprIfConElse = false;
						}
					}
					}
				}
				if(!compruebaTipos(ifconelse.getCuerpo2())){
					// error
					comprIfConElse = false;
				}
						
			return comprIfConElse;
			default: return true;
			}break;
		case ELEMENTOSCASE:
			ElementosCase elemcase = (ElementosCase) nodo;
			if(elemcase.getExpresion().getTipo() == tipoExpresion.NOMBRE && ((nombre)elemcase.getExpresion()).getNom() == "default"){
				return compruebaTipos(elemcase.getPrograma());
			}
			else if(comprobacionaux(elemcase.getExpresion()).getTipo() == ((Switch) elemcase.getVinculacion()).gettExpresion().getTipo()){
				return compruebaTipos(elemcase.getPrograma());
			}
			else 
				// error
				break;
		default: return true;
		
	}
	return true; //???
	}
	private boolean compruebaaux2(Type comprobacionaux, Type comprobacionaux2) {
		if(comprobacionaux.getTipo() == enumtipos.PUNTERO  &&comprobacionaux2.getTipo() == enumtipos.PUNTERO  ){
			return compruebaaux2(((decTipoPuntero)comprobacionaux ).getT() , ((decTipoPuntero)comprobacionaux ).getT());
		}
		else if(comprobacionaux.getTipo() == enumtipos.VECTOR  &&comprobacionaux2.getTipo() == enumtipos.VECTOR){
			return compruebaaux2(((decTipoVector)comprobacionaux ).getT() , ((decTipoVector)comprobacionaux ).getT());

		}
		else // ver esto	
		return comprobacionaux.getTipo() == comprobacionaux2.getTipo();
		}
	
	private Type comprobacionaux(Nodo nodo){
		switch(nodo.tipoGeneral()){
		case EUNARIA:
			EUn eunaria = (EUn) nodo;
			Type tipoaux = comprobacionaux(eunaria.opnd());
			switch(eunaria.getTipo()){
			case NOT:
				if (tipoaux.getTipo() == enumtipos.BOOL)
					return new decTipoBool();
				else
					//error
				break;
			
			case OPSUMA:
				if (tipoaux.getTipo() == enumtipos.INT || tipoaux.getTipo() == enumtipos.DOUBLE  )
					return tipoaux;
				else
					//error
				break;
			case OPRESTA:
				if (tipoaux.getTipo() == enumtipos.INT || tipoaux.getTipo() == enumtipos.DOUBLE  )
					return tipoaux;
				else
					//error
				break;
			case ACCESOPUNTERO: // ver esto
				if (tipoaux.getTipo() == enumtipos.PUNTERO  )
					return ((decTipoPuntero)tipoaux).getT();
				else
					//error
				break;
				default:
					break;
			}
			break;
		case EBINARIA:
			EBin ebinaria = (EBin) nodo;
			Type tipoaux1 = comprobacionaux(ebinaria.opnd1());
			Type tipoaux2 = comprobacionaux(ebinaria.opnd2());
			
			switch(ebinaria.getTipo()){
			case SUMA: // ver casting
				if(tipoaux1.getTipo() == enumtipos.INT && tipoaux1.getTipo() == enumtipos.INT ){
					return new decTipoInt();
				}
				else if(tipoaux1.getTipo() == enumtipos.DOUBLE && tipoaux1.getTipo() == enumtipos.DOUBLE ){
					return new decTipoDouble();
				}
				else{
					// error
				}
				break;
			case RESTA: // ver casting
				if(tipoaux1.getTipo() == enumtipos.INT && tipoaux1.getTipo() == enumtipos.INT ){
					return new decTipoInt();
				}
				else if(tipoaux1.getTipo() == enumtipos.DOUBLE && tipoaux1.getTipo() == enumtipos.DOUBLE ){
					return new decTipoDouble();
				}
				else{
					// error
				}
				break;
			case MUL: // ver casting
				if(tipoaux1.getTipo() == enumtipos.INT && tipoaux1.getTipo() == enumtipos.INT ){
					return new decTipoInt();
				}
				else if(tipoaux1.getTipo() == enumtipos.DOUBLE && tipoaux1.getTipo() == enumtipos.DOUBLE ){
					return new decTipoDouble();
				}
				else{
					// error
				}
				break;
			case MOD: // ver casting
				if(tipoaux1.getTipo() == enumtipos.INT && tipoaux1.getTipo() == enumtipos.INT ){
					return new decTipoInt();
				}
				else {
					// error
				}
				break;
			case DIV: // ver casting
				if(tipoaux1.getTipo() == enumtipos.INT && tipoaux1.getTipo() == enumtipos.INT ){
					return new decTipoInt();
				}
				else if(tipoaux1.getTipo() == enumtipos.DOUBLE && tipoaux1.getTipo() == enumtipos.DOUBLE ){
					return new decTipoDouble();
				}
				else{
					// error
				}
				break;
			case MAYOR: // ver casting
				if(tipoaux1.getTipo() == tipoaux2.getTipo() && (tipoaux1.getTipo() == enumtipos.INT || 
						tipoaux1.getTipo() == enumtipos.DOUBLE ||tipoaux1.getTipo() == enumtipos.BOOL) ){
					return new decTipoBool();
				}
				else{
					// error
				}
				break;
			case MAYORIGUAL: // ver casting
					if(tipoaux1.getTipo() == tipoaux2.getTipo() && (tipoaux1.getTipo() == enumtipos.INT || 
							tipoaux1.getTipo() == enumtipos.DOUBLE ||tipoaux1.getTipo() == enumtipos.BOOL )){
						return new decTipoBool();
					}
					else{
						// error
					}
					break;
			case MENOR: // ver casting
				if(tipoaux1.getTipo() == tipoaux2.getTipo() && (tipoaux1.getTipo() == enumtipos.INT || 
						tipoaux1.getTipo() == enumtipos.DOUBLE ||tipoaux1.getTipo() == enumtipos.BOOL )){
					return new decTipoBool();
				}
				else{
					// error
				}
				break;
			case MENORIGUAL: // ver casting
				if(tipoaux1.getTipo() == tipoaux2.getTipo() && (tipoaux1.getTipo() == enumtipos.INT || 
						tipoaux1.getTipo() == enumtipos.DOUBLE ||tipoaux1.getTipo() == enumtipos.BOOL )){
					return new decTipoBool();
				}
				else{
					// error
				}
				break;
			case IGUALIGUAL: // ver casting
				if(tipoaux1.getTipo() == tipoaux2.getTipo() && (tipoaux1.getTipo() == enumtipos.INT || 
						tipoaux1.getTipo() == enumtipos.DOUBLE ||tipoaux1.getTipo() == enumtipos.BOOL )){
					return new decTipoBool();
				}
				else{
					// error
				}
				break;
			case OR: // ver casting
				if(tipoaux1.getTipo() == tipoaux2.getTipo() && tipoaux1.getTipo() == enumtipos.BOOL   ){
					return new decTipoBool();
				}
				else{
					// error
				}
				break;
			case AND: // ver casting
				if(tipoaux1.getTipo() == tipoaux2.getTipo() && tipoaux1.getTipo() == enumtipos.BOOL   ){
					return new decTipoBool();
				}
				else{
					// error
				}
				break;
			case ACCESOSTRUCT:// VERLO
				accesoStruct accesostruct = (accesoStruct) nodo;
				Type tipoauxstruct1 = comprobacionaux(accesostruct.opnd1());
				Type tipoauxstruct2 = comprobacionaux(accesostruct.opnd2());
				nombre paramacceso = ((nombre) accesostruct.opnd1());
				if(tipoauxstruct1.getTipo() != enumtipos.TIPOUSUARIO) {// para usar struct tiene que ser tipo usuario
					// error
				}
				else{
					accesostruct.setT(tipoauxstruct1);
					List<Subprogramas> l= ((decStruct) ((TipoUsuario) tipoauxstruct1).getVinculacion()).getDeclaraciones();
					for(Subprogramas dec: l){
						if(((nombre) ((Declaracion) dec).nombre()).getNom() == ((nombre)accesostruct.opnd2()).getNom() ){
							return ((Declaracion) dec).getTipoAux();
						};
					}
				}
				break;
			case ACCESOSTRUCTPUNTERO:// VERLO
				accesoStructPuntero accesostructPuntero = (accesoStructPuntero) nodo;
				Type tipoauxstructpunt1 = comprobacionaux(accesostructPuntero.opnd1());
				Type tipoauxstructpunt2 = comprobacionaux(accesostructPuntero.opnd2());
				nombre paramaccesopunt = ((nombre) accesostructPuntero.opnd1());
				if(tipoauxstructpunt1.getTipo() != enumtipos.PUNTERO ||  ((decTipoPuntero) tipoauxstructpunt1).getT().getTipo() != enumtipos.TIPOUSUARIO ) {// para usar struct tiene que ser tipo usuario y para flecha puntero
					// error
				}
				else{
					List<Subprogramas> l= ((decStruct) ((TipoUsuario) tipoauxstructpunt1).getVinculacion()).getDeclaraciones();
					for(Subprogramas dec: l){
						if(((nombre) ((Declaracion) dec).nombre()).getNom() == ((nombre)accesostructPuntero.opnd2()).getNom() ){
							return ((Declaracion) dec).getTipoAux();
						};
					}
				}
				break;
				default:
					break;
			
			}
			break;
			case E:
				E expresion = (E)nodo;
				switch(expresion.getTipo()){
				
				case LLAMADAFUNCRETURN:
					boolean comprLlamadaReturn = true;
					LlamadaFuncReturn llamadaaux = (LlamadaFuncReturn) expresion;
					if(((Subprogramas) llamadaaux.getVinculacion()).getTipo() != tipoSubprograma.DECFUNCIONCONRETURN ){
						
						// error, no es una funcion
						comprLlamadaReturn = true;
					}
					else{
					List<ParametroFuncion> listaparam = ((decFuncionConReturn)llamadaaux.getVinculacion()).getListaArgumentos();
					if( llamadaaux.getArgumentos().size() != listaparam.size()){
						// error distinto tamano
						comprLlamadaReturn = true;
					}
					else {
						for(int i = 0; i < listaparam.size(); ++i){ // ver lo de referencia
							comprLlamadaReturn = comprLlamadaReturn &&  comprobacionaux(llamadaaux.getArgumentos().get(i)).getTipo() == listaparam.get(i).getTipo().getTipo();
						}
						if(!comprLlamadaReturn) {
						// error
						}
						else return ((decFuncionConReturn) llamadaaux.getVinculacion()).getTipoRetorno();
					}
				}
				case INT:
					return new decTipoInt();
					
				case DOUBLE:
					return new decTipoDouble();
					
				case ACCESOVECTOR:
					accesoVector acceso = (accesoVector)expresion;
					Type auxiliar = comprobacionaux(acceso.getNombre());
					if(auxiliar.getTipo()!=enumtipos.VECTOR ){
						// error
					}
					else{
						for (AccesoVectorParam param : acceso.getList()){
							Type aux = comprobacionaux(param);// hacer caso param
							if(aux.getTipo()!=enumtipos.INT){
								//error
							}
						}
						// comprobar dimensiones
					}				
					
					break;
				case NOMBRE:
					 nombre nom = (nombre)expresion;
					 return comprobacionaux(nom.getTipoVinculacion());
				case TRUE: 
					return new decTipoBool();
				case FALSE: 
					return new decTipoBool();
				case CARACT:
					return new decTipoChar();
				case EXPRNEW:
					exprNew exprnew = (exprNew) expresion;
					return new decTipoPuntero(exprnew.getType() ,exprnew.getFila(), exprnew.getColumna() );
					
				case CREACIONVECTOR:
					creacionVector cvector = (creacionVector) expresion;
					Type tipoauxvect = comprobacionaux(cvector.getExp1());
					Type tipoauxvect2 = comprobacionaux(cvector.getExp2());

					if(tipoauxvect.getTipo() != enumtipos.INT){
						// error
					}
					else{
						boolean ok=  cvector.getVinculacionTipo().getTipo() ==  tipoauxvect2.getTipo();
						if(ok){
						return new decTipoVector(tipoauxvect2, cvector.getFila(), cvector.getColumna());
					}
					}
					// VER ESTE CASO
				//case CREACIONVECTOR2:;
				
				}
				
			default:
			break;
				
		}
		return null;
		

	}
	
}