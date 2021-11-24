package generacioncodigo;
import java.util.ArrayList;
import java.util.List;

import ast.*;
public class GeneradorDeCodigo {
	int nivel = 0; // numero de ambito por el que vamos, usado a la hora de acceder a los bloques y
	// sus datos precalculados
	int nivelambitos = 0; // numero de ambitos totales
	String resul = "";
	
	public Block bloqueActual(){
		return asignadirEstaticas.getListaDeBloques().get(nivel);
	}
	public calculodesignadorauxiliar generaCodigoDesignador(E expresion){
		calculodesignadorauxiliar calculo = new calculodesignadorauxiliar();
		switch(expresion.getTipo()){
		case ACCESOPUNTERO:
			calculo = generaCodigoDesignador(((accesoPuntero)expresion).opnd());
			anadeInstruccion("i32.load");
			break;
		case ACCESOSTRUCT:
			accesoStruct accesostruct = (accesoStruct) expresion;
			calculo = generaCodigoDesignador(accesostruct.opnd1());
			 
			anadeInstruccion( "i32.const" + (bloqueActual().direccionVariable(accesostruct.getT() +"." +((nombre)accesostruct.opnd2()).getNom() +2)*4) +"\nget_global $MP" +
					"\ni32.add\n"); 
			// en caso de que sea vector hay que hacer mas 
			// no completo: la idea es recorrer en las declaraaciones del struct pertinente
			// y en caso de que sea vector usaremos la clase auxiliar usada para acceder a dicho vector
			/*
			for (Subprogramas declaracion : ((decStruct)accesostruct).getDeclaraciones() ){
				Declaracion dec = (Declaracion) declaracion;
				/*if(((nombre)accesostruct.opnd2()).getNom() == ((nombre)declaracion.getNom()).getNom()
						&& dec.getTipoAux().getTipo() == enumtipos.VECTOR){
					// ArrayList<Integer> list = calculodimVector(dec.getTipoAux(), ((decconVi)dec).getExpresion2());
					// calculo.setListaDim(list);
					calculo.inicalcauxdirvector();
					calculo.setIndice(0); // lo colocamos al inciio
					calculo.setDirVector(bloqueActual().direccionVariable(((nombre)dec.getNom()).getNom());
					
				}
			}
			*/
			break;
		
			
		case ACCESOVECTOR:
			accesoVector accesovector = (accesoVector) expresion;
			calculo = generaCodigoDesignador(accesovector.getNombre());
			anadeInstruccion( "i32.const" + calculo.getCalcauxdirVector()* bloqueActual().tamTipo(((nombre)accesovector.getNombre()).getTipoVinculacion().toString()) +"\nget_global $MP" +
					"\ni32.add\n");
			calculo.setCalcauxdirVector(calculo.getCalcauxdirVector() /calculo.getListaDim().get(calculo.getIndice())); // calculo auxiliar eficiente de direcciones
			calculo.setIndice(calculo.getIndice()+1);
		break;
		case NOMBRE: // no completa, falta ver el caso de si es parametro de una funcion
			nombre nom = (nombre) expresion;
			Declaracion decnom = (Declaracion)nom.getVinculacion(); 
			// vemos si es vector, si no lo es, es decir la listdim es vacia accedemos de manera normal
			// ArrayList<Integer> listdim = bloqueActual().getDimensionesVect(((nombre)decnom.getNom()).getNom());
			// anadeInstruccion() // ver como meter direccion del ambito
			anadeInstruccion("i32.const "+ bloqueActual().direccionVariable(nom.getNom()) +"\nget_global $MP" +"\ni32.add\n");
		break;
		}
		return calculo;
		
	}
	public void generacodigoInstrucciones(Subprogramas subprograma){
		switch (subprograma.getTipo()){
		// ver lo de setvarbucle
		case FOR1 : 
				++nivelambitos;
				nivel = nivelambitos;
				buclefor1 buclefor1 = (buclefor1) subprograma;
				generacodigoInstrucciones (buclefor1.getDecVi());
				anadeInstruccion("block");
				anadeInstruccion("loop");
				generacodigoExpresiones (buclefor1.getCondicion());
				anadeInstruccion("i32.eqz");
				anadeInstruccion("i32.br_if 1");
				generacodigomain(buclefor1.getProg());
				generacodigoInstrucciones(buclefor1.getExprPaso());
				anadeInstruccion("br 0");
				anadeInstruccion("end");
				nivel = bloqueActual().getPredecesor().getPosicion();

	
		case FOR2 : // no realizado, lo hariamos por medio 
			// de transformar a lista la expresion 2 y transformar a while igual 
			// por medio de un new con la misma lista de instrucciones
			// y el while ya esta realizado
			++nivelambitos;
			nivel = nivelambitos;
			buclefor2 buclefor2 = (buclefor2) subprograma;
			
			nivel = bloqueActual().getPredecesor().getPosicion();


			break;

		
		case WHILE:
			++nivelambitos;
			nivel = nivelambitos;
			BucleWhile bucleWhile = (BucleWhile) subprograma;
			anadeInstruccion("block");
			anadeInstruccion("loop");
			generacodigoExpresiones (bucleWhile.getCondicion());
			anadeInstruccion("i32.eqz");
			anadeInstruccion("i32.br_if 1");
			generacodigomain(bucleWhile.getProg());
			anadeInstruccion("br 0");
			anadeInstruccion("end");
			nivel = bloqueActual().getPredecesor().getPosicion();
			
			


			break;
		case DECVAR: 
			Declaracion dec = (Declaracion) subprograma;
					
				
			switch(dec.getTipoDeclaracion()){
			case DECLARACIONCONVI:
				decconVi decconvi = (decconVi) dec;
				anadeInstruccion("i32.const" + (bloqueActual().direccionVariable(((nombre)decconvi.nombre()).getNom()) +2) *4 +"\nget_global $MP" +
						"\ni32.add\n");
					
				switch(decconvi.getTipoAux().getTipo()){
				case TIPOUSUARIO:
					// Hay que ver asignacion multiple, que se hara
					// como la normal pero viendo direcciones

				case VECTOR:
					// ver asignacion multiple......
				
				case PUNTERO:
					// meter una direccion y aumentar el sp para la variable
					// generarcodigovariable
					generacodigoExpresiones(decconvi.getExpresion2());
				
				
				default:
					generacodigoExpresiones(decconvi.getExpresion2());
					anadeInstruccion("i32.store");
				}
				
				
				break;
			case DECLARACIONSINVI:
		 		decsinVi decsinvi = (decsinVi) dec;
				break; 
			}
			case ASIGNACION:
			Asignacion asignacion = (Asignacion) subprograma;
			generaCodigoDesignador(asignacion.getExpresion1());
			anadeInstruccion("i32.add");
			generacodigoExpresiones(asignacion.getExpresion2());
			anadeInstruccion("i32.store");

			
			break;
				
		
		case SWITCH:// la idea es convertilo en un A (programa) en el que tengamos varios elseifs juntos
			A programaSwitchAux = new A();
			Switch switc = (Switch) subprograma;
			generacodigoExpresiones(switc.getExpresion());
			for (ElementosCase elem: switc.getList() ){
				programaSwitchAux.codigo(new ifSinElse(elem.getPrograma(), new igualigual(elem.getExpresion(), switc.getExpresion(), 0, 0),0,0 ));
				// lo convierto en if
			}
			generacodigomain(programaSwitchAux);

			
			
		case DECFUNCIONCONRETURN:
			++nivelambitos;
			nivel = nivelambitos;
			decFuncionConReturn decfuncion = (decFuncionConReturn) subprograma;
			// ver memoria y todo, no completa
			generacodigomain(decfuncion.getCuerpo());
			
			
			
			anadeInstruccion("local $localsStart i32");
			anadeInstruccion("\ni32.const" + (bloqueActual().getTamBloque() +2)*4);
			anadeInstruccion("\ncall $reserveStack "+"\nget_global $MP" + "\ni32.store" + "\nget_global $MP" +
			"\nget_global $SP" +"\ni32.store offset=4" + "\nget_global $MP" + "\ni32.const 8" + "\ni32.add" +
			"\nset_local $\nlocalsStart");
			anadeInstruccion(" i32.const 0 ");
			generacodigomain(decfuncion.getCuerpo());
			anadeInstruccion(" call $freeStack ");
			
			generacodigoExpresiones(decfuncion.getValueReturn());// evalua expresiones
			anadeInstruccion("i32.store\n ");
			
			anadeInstruccion( "\nget_global $MP\n" +  "\nget_global $MP\n"+  "\ni32.load " +" set_global $MP\r\n" +
					"set_global $SP\r\n)\r\n"); 
			   
			 

			nivel = bloqueActual().getPredecesor().getPosicion();

			break;
		case DECFUNCIONSINRETURN:
			++nivelambitos;
			nivel = nivelambitos;
			decFuncionSinReturn decfuncionsinreturn = (decFuncionSinReturn) subprograma;
			
			anadeInstruccion("local $localsStart i32");
			anadeInstruccion("\ni32.const" + (bloqueActual().getTamBloque() +2)*4);
			anadeInstruccion("\ncall $reserveStack "+"\nget_global $MP" + "\ni32.store" + "\nget_global $MP" +
			"\nget_global $SP" +"\ni32.store offset=4" + "\nget_global $MP" + "\ni32.const 8" + "\ni32.add" +
			"\nset_local $\nlocalsStart");
			anadeInstruccion(" i32.const 0 ");
			generacodigomain(decfuncionsinreturn.getCuerpo());
			anadeInstruccion(" call $freeStack ");
			
			nivel = bloqueActual().getPredecesor().getPosicion();

			break;
		
		case DECTIPO:
			break;
			
	
		case LLAMADAFUNCIONVOID:
			// caso no realizado
			LlamadaFuncVoid llamada = (LlamadaFuncVoid) subprograma;
			
				break;
		case IFSINELSE: 
			++nivelambitos;
			nivel = nivelambitos;
			ifSinElse ifsinelse = (ifSinElse) subprograma;
			generacodigoExpresiones(ifsinelse.getCondicion());
			anadeInstruccion("if");
			generacodigomain(ifsinelse.getCuerpo());
			anadeInstruccion("end");
			nivel = bloqueActual().getPredecesor().getPosicion();

			break;
		case IFCONELSE:
			++nivelambitos;
			nivel = nivelambitos;
			ifConElse ifconelse = (ifConElse) subprograma;
			generacodigoExpresiones(ifconelse.getCondicion());
			anadeInstruccion("if");
			generacodigomain(ifconelse.getCuerpo1());
			nivel = bloqueActual().getPredecesor().getPosicion();
			for(ElseIf elseif: ifconelse.getList() ){
				++nivelambitos;
				nivel = nivelambitos;
				anadeInstruccion("else");
				generacodigoExpresiones(elseif.getCondicion());
				anadeInstruccion("if");
				generacodigomain(elseif.getCuerpo());
				nivel = bloqueActual().getPredecesor().getPosicion();

			}
			++nivelambitos;
			nivel = nivelambitos;
			anadeInstruccion("else");
			generacodigomain(ifconelse.getCuerpo2());
			anadeInstruccion("end");
			nivel = bloqueActual().getPredecesor().getPosicion();

			

			break;
		}
	}
	
	private void anadeInstruccion(String s) {
		resul += s;
	}
	public void generacodigoExpresiones(E expresion){
		switch(expresion.getTipo()){
			case NOT:
				EUn eunot =  (EUn)expresion;
				generacodigoExpresiones(eunot.opnd());
				anadeInstruccion("i32.neg");
				break;
			case OPSUMA:
				EUn eunsuma =  (EUn)expresion;
				generacodigoExpresiones(eunsuma.opnd());
				anadeInstruccion("i32.add");
			case OPRESTA:
				EUn eunresta =  (EUn)expresion;
				generacodigoExpresiones(eunresta.opnd());
				anadeInstruccion("i32.sub");
				break;
			case ACCESOPUNTERO: // ver esto
				EUn eunpuntero =  (EUn)expresion;
				generaCodigoDesignador(eunpuntero.opnd());
				anadeInstruccion("i32.load");
				break;
			
		
			case RESTA: 
				EBin ebinresta = (EBin) expresion;
				generacodigoExpresiones(ebinresta.opnd1());
				generacodigoExpresiones(ebinresta.opnd2());

				anadeInstruccion("i32.sub");
				break;
			case MUL: // ver casting
				EBin ebinmul = (EBin) expresion;
				generaCodigoDesignador(ebinmul.opnd1());
				generaCodigoDesignador(ebinmul.opnd2());

				anadeInstruccion("i32.mul");
				break;
			case MOD: // ver casting
				EBin ebinmod = (EBin) expresion;
				generaCodigoDesignador(ebinmod.opnd1());
				generaCodigoDesignador(ebinmod.opnd2());

				anadeInstruccion("i32.rem_s");
				break;
			case DIV: // ver casting
				EBin ebindiv = (EBin) expresion;
				generacodigoExpresiones(ebindiv.opnd1());
				generacodigoExpresiones(ebindiv.opnd2());

				anadeInstruccion("i32.div_u");
				break;
			case MAYOR: // ver casting
				EBin ebinmayor = (EBin) expresion;
				generacodigoExpresiones(ebinmayor.opnd1());
				generacodigoExpresiones(ebinmayor.opnd2());

				anadeInstruccion("i32.gt_s");
				break;
			case MAYORIGUAL: // ver casting 
				EBin ebimayorigual = (EBin) expresion;
				generacodigoExpresiones(ebimayorigual.opnd1());
				generacodigoExpresiones(ebimayorigual.opnd2());

				anadeInstruccion("i32.ge_s");	
					break;
			case MENOR: // ver casting
				EBin ebinmenor = (EBin) expresion;
				generacodigoExpresiones(ebinmenor.opnd1());
				generacodigoExpresiones(ebinmenor.opnd2());

				anadeInstruccion("i32.lt_s");	
				break;
			case MENORIGUAL: // ver casting
				EBin ebinmenorigual = (EBin) expresion;
				generacodigoExpresiones(ebinmenorigual.opnd1());
				generacodigoExpresiones(ebinmenorigual.opnd2());

				anadeInstruccion("i32.lt_s");	
				break;
			case IGUALIGUAL:
				EBin ebinigualigual = (EBin) expresion;
				generacodigoExpresiones(ebinigualigual.opnd1());
				generacodigoExpresiones(ebinigualigual.opnd2());

				anadeInstruccion("i32.eq");	
				break;
			case OR: 
				EBin ebinor = (EBin) expresion;
				generacodigoExpresiones(ebinor.opnd1());
				generacodigoExpresiones(ebinor.opnd2());

				anadeInstruccion("i32.or");	
				break;
			case AND: // ver casting
				EBin ebinand = (EBin) expresion;
				generacodigoExpresiones(ebinand.opnd1());
				generacodigoExpresiones(ebinand.opnd2());

				anadeInstruccion("i32.and");	
				break;
			case ACCESOSTRUCT:// VERLO
				generaCodigoDesignador(expresion);

				break;
		
			
			
				case LLAMADAFUNCRETURN: // caso no realizado
					
				
				case ACCESOVECTOR:
					generaCodigoDesignador(expresion);

					break;
				case NOMBRE:
				case TRUE: 
				case FALSE: 
				case CARACT:
				case EXPRNEW:
					
				case CREACIONVECTOR:
		}
	}
	public void generacodigomain(A programa){
		for(int i = programa.getProgramas().size(); i >=0; --i){
			generacodigoInstrucciones(programa.getProgramas().get(i));
		}
	}
	public void generadorcodigo(Nodo nodo){
		asignadirEstaticas asignadirecciones = new asignadirEstaticas();
		asignadirecciones.asignadireccionesaux(nodo);
		int memestatica = asignadirecciones.calculomemEstatica();
		
		anadeInstruccion("(local $localsStart i32)");
		anadeInstruccion("\ni32.const" + (memestatica+2)*4);
		anadeInstruccion("\ncall $reserveStack "+"\nget_global $MP" + "\ni32.store" + "\nget_global $MP" +
		"\nget_global $SP" +"\ni32.store offset=4" + "\nget_global $MP" + "\ni32.const 8" + "\ni32.add" +
		"\nset_local $\nlocalsStart");
		anadeInstruccion(" i32.const 0 ");
		generacodigomain((A)nodo);
		anadeInstruccion(" call $freeStack ");
		imprimir(resul);
	}
	public void imprimir(String resul){
		System.out.println(resul);
	}
}
