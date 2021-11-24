package errors;

import alex.*;

public class GestionErroresExp {
  int eSintac=0;

   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR fila "+fila+": Caracter inexperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     ++eSintac;
     if (eSintac > 10){
      System.out.println("Demasiados errores para recuperar");
     System.exit(1);
     }
     else{
      System.out.print("ERROR SINTACTICO: fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado "+((TokenValue)unidadLexica.value).lexema+"\n");
     }
   }
}
