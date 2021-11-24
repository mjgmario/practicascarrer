package ast;

import java.util.ArrayList;
import alex.*;

public class AS {
  public A A ()
    {return new A();}
  public E mul(E opnd1, E opnd2, int fila, int columna)
  	{return new Mul(opnd1,opnd2, fila, columna);}  
  public E Suma(E opnd1, E opnd2, int fila, int columna)
	{ return new Suma(opnd1,opnd2, fila, columna);}
  public E And(E opnd1, E opnd2, int fila, int columna)
	{ return new And(opnd1,opnd2, fila, columna);}
  public E Distinto(E opnd1, E opnd2, int fila, int columna) 
	{return new Distinto(opnd1,opnd2, fila, columna);}
  public E Div(E opnd1, E opnd2, int fila, int columna) 
	{return new Div(opnd1,opnd2, fila, columna);}
  public E igualIgual(E opnd1, E opnd2, int fila, int columna) 
	{return new igualigual(opnd1,opnd2, fila, columna);}
   public E or(E opnd1, E opnd2, int fila, int columna) 
	{return new or(opnd1,opnd2, fila, columna);}
  public E mayorigual(E opnd1, E opnd2, int fila, int columna) 
	{return new mayorigual(opnd1,opnd2, fila, columna);}
  public E not(E opnd1, int fila, int columna) 
	{return new not(opnd1, fila, columna);}
  public E mod(E opnd1, E opnd2, int fila, int columna) 
 	{return new mod(opnd1,opnd2, fila, columna);}
  public E resta(E opnd1, E opnd2, int fila, int columna) 
  	{return new resta(opnd1,opnd2, fila, columna);}
  public E accesoPuntero(E opnd, int fila, int columna) 
	{return new accesoPuntero(opnd,  fila, columna);}
  public E caract(String opnd, int fila, int columna) 
 	{return new caract( opnd, fila, columna);}
  public E entero(String opnd, int fila, int columna) 
 	{return new entero(opnd, fila, columna);}
  public E nombre(String opnd, int fila, int columna) 
 	{return new nombre(opnd, fila, columna);}
   public Type decTipoINT() 
	{return new decTipoInt();}
  public Type decTipoBool() 
 	{return new decTipoBool();}
  public Type decTipoCHAR() 
 	{return new decTipoChar();}
  public Type decTipoDOUBLE() 
 	{return new decTipoDouble();}
  public Type decTipoString()
	{return new decTipoString();}
  public Type decTipoPuntero(Type tipo, int fila, int columna) 
	{return new decTipoPuntero(tipo, fila, columna);}
  public Subprogramas decTipoUsuario(E exp, Type tipo, int fila, int columna) 
	{return new decTipoUsuario(exp,tipo, fila, columna);}
  public Type TipoUsuario(String valor,int fila,int columna)
  {return new TipoUsuario(valor, fila, columna);}
  public Type decTipoVector(Type tipo, int fila, int columna) 
	{return new decTipoVector(tipo, fila, columna);}
  public E LlamadaFuncReturn(ArrayList<E> argumentos, E nombre, int fila, int columna) 
	{return new LlamadaFuncReturn(argumentos,nombre,  fila, columna);}
	public Subprogramas LlamadaFuncVoid(ArrayList<E> argumentos, E nombre, int fila, int columna) 
	  {return new LlamadaFuncVoid(argumentos,nombre,  fila, columna);}
  public E True(int fila, int columna) 
	{return new True( fila, columna);}
  public E False(int fila, int columna) 
	{return new False( fila, columna);}
	public E exprNew(boolean esNull, Type type,  int fila,int columna)
	{return new exprNew(esNull, type,  fila, columna);}
	public Subprogramas ifSinElse(A cuerpo, E condicion, int fila, int columna)
	{return new ifSinElse(cuerpo, condicion, fila, columna);}
	public Subprogramas ifConElse(A cuerpo1, A cuerpo2, ArrayList<ElseIf> list, E condicion,
			int fila, int columna)	{return new ifConElse(cuerpo1,cuerpo2,list, condicion, fila, columna);}
	public Subprogramas buclefor1(A prog, Subprogramas decVi, E condicion, Subprogramas exprPaso,int fila, int columna)
	{return new buclefor1(prog, decVi,condicion,exprPaso, fila, columna);}
	public Subprogramas buclefor2(A prog, E variable, E conjunto, int fila, int columna) 
	{return new buclefor2(prog, variable,conjunto, fila, columna);}
	public Subprogramas BucleWhile(A prog, E condicion, int fila, int columna) 
	{return new BucleWhile(prog, condicion, fila, columna);}
	public Subprogramas decsinVi( Type tipo, E nombre, int fila, int columna) 
	{return new decsinVi( tipo, nombre, fila, columna);}
	public Subprogramas decconVi(Type t, E expresion1, E expresion2, int fila, int columna)
	{return new decconVi( t, expresion1,expresion2, fila, columna);}
	public Subprogramas Asignacion(E expresion1, E expresion2, int fila, int columna) 
	{return new Asignacion(expresion1, expresion2, fila, columna);}
	public Subprogramas decFuncionConReturn(E nombre,ArrayList<ParametroFuncion> listaArgumentos, retornoFuncion cuerpo, int fila,int columna, Type tipoRetorno)
	{return new decFuncionConReturn(nombre, listaArgumentos,cuerpo, fila, columna, tipoRetorno);}
	public Subprogramas decFuncionSinReturn(E nombre,ArrayList<ParametroFuncion> listaArgumentos, A cuerpo, int fila,int columna)
	{return new decFuncionSinReturn(nombre, listaArgumentos,cuerpo, fila, columna);}
	public retornoFuncion retornoFuncion(A cuerpo, E expresionRetorno, int fila, int columna)
	{return new retornoFuncion(cuerpo, expresionRetorno, fila, columna);}
	public ParametroFuncion ParametroFuncion(boolean porReferencia, Type tipo, E nombre, int fila, int columna)
	{return new ParametroFuncion(porReferencia, tipo,nombre,  fila, columna);}
	public Subprogramas decEnum(E nombre, int fila, int columna, ArrayList<E> valores) 
	{return new decEnum(nombre, fila, columna, valores);}
	public Subprogramas decStruct(ArrayList<Subprogramas> declaraciones, Type nom, int fila,int columna)
	{return new decStruct(declaraciones,nom, fila, columna);}
	public Subprogramas Switch(E expresion, ArrayList<ElementosCase> list, int fila, int columna)
	{return new Switch(expresion,list, fila, columna);}
	public ElementosCase ElementosCase(A programa, E expresion, int fila, int columna)
	{return new ElementosCase(programa,expresion, fila, columna);}
	public E creacionVector( E tam, E inicializando, int fila, int columna)
	{return new creacionVector(tam, inicializando, fila, columna);}
	public E creacionVector2(ArrayList<E>vec,int fila, int columna)
	{return new creacionVector2(vec,fila,columna);}
	 public E mayor(E opnd1, E opnd2, int fila, int columna) 
	{return new mayor(opnd1,opnd2, fila, columna);}
	 public E menor(E opnd1, E opnd2, int fila, int columna) 	
		{return new menor(opnd1,opnd2, fila, columna);}
	 public E menorIgual(E opnd1, E opnd2, int fila, int columna) 
		{return new menorIgual(opnd1,opnd2, fila, columna);}
	public E entreparentesis(E opnd, int fila, int columna) 
	{return new entreparentesis(opnd,fila, columna);}
	public E accesoStruct( E nombre, E expr, int fila, int columna) 
	{return new accesoStruct(nombre,expr, fila, columna);}
	public accesoStructParam accesoStructParam(E nom, int fila, int columna) 
	{return new accesoStructParam(nom, fila, columna);}
	public E accesoVector( E nombre,ArrayList<AccesoVectorParam> list, int fila, int columna) 
	{return new accesoVector(nombre,list,  fila, columna);}
	public AccesoVectorParam AccesoVectorParam(E expr, int fila, int columna) 
		{return new AccesoVectorParam(expr, fila, columna);}
	public E accesoStructPuntero( E nombre, E campo,int fila, int columna)
	{return new accesoStructPuntero(nombre, campo,fila, columna);}
	public opSuma opSuma(E expr,int fila, int columna)
	{return new opSuma(expr,fila,columna);}
	public opResta opResta(E expr,int fila, int columna)
	{return new opResta(expr,fila,columna);}
	public decimal decimal (String value, int fila, int columna)
	{return new decimal(value,fila,columna);}
    public ElseIf ElseIf(E expr, A prog, int fila, int columna)
	{return new ElseIf(expr,prog,fila,columna);}
    public corchetesVector corchetesVector(E exp1, E exp2, int fila, int columna)
	{return new corchetesVector(exp1,exp2,fila,columna);}
 

	
}
	
