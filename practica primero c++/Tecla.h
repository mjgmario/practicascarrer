// Mario Jim�nez Guti�rrez
//Tecla.h
//M�dulo relacinado con el tipo ttecla que incluye cartas para la conversion de ttecla a carta
#ifndef tecla_h
#define tecla_h
#include "Cartas.h"
#include <conio.h>
enum tTecla { TAVANZA, TDERECHA, TIZQUIERDA, TLASER, TSALIR };
tTecla leertecla();
#endif 
