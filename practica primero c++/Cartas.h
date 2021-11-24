// Mario Jiménez Gutiérrez
// Cartas.h
/*Módulo que define las funciones relacinadas con el
tipo tcartas
*/
#ifndef Cartas_h
#define Cartas_h
#include <iomanip>
#include <fstream>
#include<vector>
#include <algorithm>
#include <utility>
#include <iostream>
#include <string>
#include <cstdlib>
#include <stdio.h>     

const int Numdirecciones = 4, cartasAvanza = 18, cartasGI = 8, cartasGD = 8, cartasPL = 4, NUM_CARTAS = 38;
// Número de direcciones, número de cartas de cada tipo y la longitud del mazo establecida
enum tCarta { AVANZAR, GIROIZQUIERDA, GIRODERECHA, LASER };
using tArrayPtrCartas = tCarta * [NUM_CARTAS];
struct tMazo {
	int inicio, fin, numElem;
	tArrayPtrCartas lista;
};
using tVCartas = std::vector <tCarta>;
void devolvercartas(tMazo & mazo, tVCartas & cartas);
void liberarmazo(tMazo & mazo);
std::string cartastostring(tCarta const & carta);
std::ostream & operator<<(std::ostream & flujo, tCarta const & carta);
std::ostream & operator<<(std::ostream & flujo, tMazo const & mazo);
void inicializar(tMazo & mazo);
tMazo crearMazoAleatorio();
bool  cogerCarta(tMazo & mazo, tCarta & carta);
//void insertarCarta(tMazo & mazo, tCarta const & carta);
#endif