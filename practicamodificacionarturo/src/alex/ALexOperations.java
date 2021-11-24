   package alex;

import constructorast.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoExp alex;
  public ALexOperations(AnalizadorLexicoExp alex) {
   this.alex = alex;   
  }
  

  public UnidadLexica unidadNombrev() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NOMBREV,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadNombret() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NOMBRET,
                                       alex.lexema()); 
} 
  public UnidadLexica unidadCaracter() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CARACTER,
     alex.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema()); 
  } 
  public UnidadLexica unidadReal() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DECIMAL,alex.lexema()); 
  }  
  public UnidadLexica unidadFlecha() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FLECHA,
     "->"); 
  } 
  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SUMA,
     "+"); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RESTA,
     "-"); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MUL,
     "*"); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV,
     "/"); 
  } 
  public UnidadLexica unidadMod() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MODULO,
     "mod"); 
  }
  public UnidadLexica unidadOr() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OR,
     "or"); 
  }
  public UnidadLexica unidadAnd() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AND,
     "and"); 
  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA,
     ","); 
  } 
  public UnidadLexica unidadFor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FOR,
     "for"); 
  } 
  public UnidadLexica unidadCase() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CASE,
     "case"); 
  }
 
  public UnidadLexica unidadWhile() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.WHILE,
     "while"); 
  }
 
  public UnidadLexica unidadBreak() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BREAK,
     "break"); 
  }
  public UnidadLexica unidadIf() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IF,
     "if"); 
  }
  public UnidadLexica unidadElseif() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSEIF,
     "elseif"); 
  }
  public UnidadLexica unidadElse() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSE,
     "else"); 
  }
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.APERTPARENTESIS,
     "("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CIERREPARENTESIS,
     ")"); 
  } 
  public UnidadLexica unidadPuntoycoma() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTOYCOMA,
     ";"); 
  }
  public UnidadLexica unidadIgualigual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUALIGUAL,
     "=="); 
  }
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL,
     "="); 
  }
  public UnidadLexica unidadMayorigual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORIGUAL,
     ">="); 
  }
  public UnidadLexica unidadMenorigual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORIGUAL,
     "<="); 
  }
  public UnidadLexica unidadMenor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR,
     "<"); 
  }
  public UnidadLexica unidadMayor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR,
     ">"); 
  }
  public UnidadLexica unidadDistinto() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DISTINTO,
     "!="); 
  }
  public UnidadLexica unidadNot() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NOT,
     "!"); 
  }
  public UnidadLexica unidadApertllaves() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.APERTLLAVES,
     "{"); 
  }
  public UnidadLexica unidadCierrellaves() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CIERRELLAVES,
     "}"); 
  }
  public UnidadLexica unidadPorreferencia() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PORREFERENCIA,
    "&"); 
  }
  public UnidadLexica unidadStruct() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STRUCT,
     "struct"); 
  }
  public UnidadLexica unidadEnum() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENUM,
     "enum"); 
  }
  public UnidadLexica unidadTypedef() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TYPEDEF,
     "typedef"); 
  }
  public UnidadLexica unidadNew() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEW,
     "new"); 
  }
  public UnidadLexica unidadIn() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IN,
     "in"); 
  }
  public UnidadLexica unidadDospuntos() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DOSPUNTOS,
     ":"); 
  }
  public UnidadLexica unidadPunto() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTO,
     "."); 
  }
  public UnidadLexica unidadDefault() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DEFAULT,
     "default"); 
  }
  public UnidadLexica unidadBool() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOL,
     "bool"); 
  }
  public UnidadLexica unidadChar() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CHAR,
     "char"); 
  }
  public UnidadLexica unidadInt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.INT,
     "int"); 
  }
  public UnidadLexica unidadDouble() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DOUBLE,
     "double"); 
  }
  public UnidadLexica unidadVoid() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VOID,
     "void"); 
  }
  public UnidadLexica unidadVector() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VECTOR,
   "vector"); 
}
  public UnidadLexica unidadString() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STRING,
     "string"); 
  }
  public UnidadLexica unidadReturn() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RETURN,
     "return"); 
  }
  public UnidadLexica unidadSwitch() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SWITCH,
     "switch"); 
  }
  public UnidadLexica unidadTrue() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TRUE,
     "true"); 
  }
  public UnidadLexica unidadFalse() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FALSE,
     "false"); 
  }
  public UnidadLexica unidadAcorch() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ABRECORCHETE,
     "["); 
  }
  public UnidadLexica unidadCcorch() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CIERRACORCHETE,
     "]"); 
  }
  public UnidadLexica unidadNull() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NULL,
     "null"); 
  }
  public UnidadLexica unidadEof() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF,"<EOF>"); 
}
  public void error() {
     System.err.println("***"+alex.fila()+", "+alex.columna()+" Caracter inexperado: "+alex.lexema());
  }

}

