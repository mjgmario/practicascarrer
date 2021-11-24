package constructorast;

import java.io.FileInputStream;
import errors.*;
import analizadorsemantica.*;
import ast.*;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 ConstructorASTExp constructorast = new ConstructorASTExp(alex);
	 AnalizadorSem  a = new AnalizadorSem((Nodo)constructorast.parse().value);
	 a.analizaSem();
	 
 }
}   
   
