package alex;

import errors.GestionErroresExp;

%%
%char
%cup
%line
%class AnalizadorLexicoExp
%type  UnidadLexica
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  private int charline = 0;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yychar +1 - charline;}
  private void refrescaColumna() {
    charline = yychar + yytext().length();
  }public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{

  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
letraM = [A-Z]
letram = [a-z]
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}*|0)
parteDecimal = {digito}* {digitoPositivo}
newline = [\r\n]
separador = [ \t\b]
comentario = #[^\n]* 
nombrev = {letram}({letra}|{digito})*
nombret = {letraM}({letra}|{digito})*
numeroEntero = {parteEntera}
numeroReal = {parteEntera}\.{parteDecimal}
flecha = \-> 
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = /
mod = mod
or = \|\|
and = &&
coma  = ,
for = for
case = case
while = while 
break = break
if = if
elseif = elseif
else = else 
apertparentesis = \(
cierreparentesis = \)
puntoycoma = \;
igualigual = \=\=
igual = \=
mayorigual  = >\=
menorigual = <\=
menor = <
mayor = >
distinto = !\=
not = !
apertllaves = \{
cierrellaves = \}
porreferencia = &
struct = struct
enum = enum
typedef = typedef
new = new
in = in
dospuntos = \:
punto = \.
default = default
bool = bool
char = char
int = int
double = double
void = void 
vector = vector
string = string
return = return
switch = switch
true = true
false = false
abrecorchete = \[
cierracorchete = \]
null = null                  


%%
{separador}               {}
{newline}                 {refrescaColumna();}
{comentario}              {refrescaColumna();}
{flecha}                  {return ops.unidadFlecha();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{mod}                     {return ops.unidadMod();}
{or}			                {return ops.unidadOr();}
{and}			                {return ops.unidadAnd();}
{coma}                    {return ops.unidadComa();}
{for}                     {return ops.unidadFor();}
{case}                    {return ops.unidadCase();}
{while}                   {return ops.unidadWhile();}
{break}                   {return ops.unidadBreak();}
{if}                      {return ops.unidadIf();}
{elseif}                  {return ops.unidadElseif();}
{else}                    {return ops.unidadElse();}
{apertparentesis}         {return ops.unidadPAp();}
{cierreparentesis}        {return ops.unidadPCierre();} 
{puntoycoma}              {return ops.unidadPuntoycoma();}
{igualigual}              {return ops.unidadIgualigual();} 
{igual}                   {return ops.unidadIgual();}
{mayorigual}              {return ops.unidadMayorigual();}
{menorigual}              {return ops.unidadMenorigual();}
{menor}                   {return ops.unidadMenor();}
{mayor}                   {return ops.unidadMayor();}
{distinto}                {return ops.unidadDistinto();}
{not}                     {return ops.unidadNot();}
{apertllaves}             {return ops.unidadApertllaves();}
{cierrellaves}            {return ops.unidadCierrellaves();}
{porreferencia}           {return ops.unidadPorreferencia();}
{struct}                  {return ops.unidadStruct();}
{enum}                    {return ops.unidadEnum();}
{typedef}                 {return ops.unidadTypedef();}
{new}                     {return ops.unidadNew();}
{in}                      {return ops.unidadIn();}
{dospuntos}               {return ops.unidadDospuntos();}
{punto}                   {return ops.unidadPunto();}
{default}                 {return ops.unidadDefault();}
{bool}                    {return ops.unidadBool();}
{char}                    {return ops.unidadChar();}
{int}                     {return ops.unidadInt();}
{double}                  {return ops.unidadDouble();}
{void}                    {return ops.unidadVoid();}
{vector}                  {return ops.unidadVector();}
{string}                  {return ops.unidadString();}
{return}                  {return ops.unidadReturn();}
{switch}                  {return ops.unidadSwitch();}
{true}                    {return ops.unidadTrue();}
{false}                   {return ops.unidadFalse();}
{abrecorchete}            {return ops.unidadAcorch();}
{cierracorchete}          {return ops.unidadCcorch();}
{null}                    {return ops.unidadNull();}
{nombret}                 {return ops.unidadNombret();}
{nombrev}                 {return ops.unidadNombrev();}
{letra}                   {return ops.unidadCaracter();}
{numeroReal}              {return ops.unidadReal();}
{numeroEntero}            {return ops.unidadEnt();}
[^]                       {ops.error();}  
