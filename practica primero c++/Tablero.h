// Mario Jiménez Gutiérrez
#ifndef Tablero_h


#define Tablero_h
#include <iostream>
#include <vector>
#include <sstream>
#include <string>
#include <algorithm>
#include <fstream>
#include <conio.h>
#include <Windows.h>
const std::vector<int> incF{ -1, 0, 1, 0 };
const std::vector<int> incC{ 0, 1, 0, -1 };
const int NUMDIRECCIONES = 4;
enum tDir { UP, RIGHT, DOWN, LEFT };
enum testadocasilla { VACIA, HIELO, MURO, CAJA, JOYA, TORTUGA };

struct ttortuga {
	int identificador;
	tDir move;
};
struct tcasilla {
	testadocasilla contenido;
	ttortuga tortuga;
};
struct tCoordenada {
	int x, y;
};
using tMatriz = std::vector <std::vector <tcasilla>>;
using tMano = std::vector <int>;
const int DIMENSION = 8, MAX_JUGADORES = 4;
bool movposible(tMatriz const & matriz, tDir const & direccion, tCoordenada  & coor);
tcasilla  chartocasilla(char car, int ident);
void casillatostring(tcasilla const & casilla, std::string  & simbolos);
void mostrarcasilla(tcasilla const & casilla);
bool cargar(tMatriz & matriz, int numjugadores, std::string const & fichero);

void colorFondo(int color);
const std::vector <int> PALETA = { 1, 11, 7, 4, 12, 5, 13, 9, 10, 3 };
bool comprobarcoordenada(tCoordenada const & coor);
bool avanzar(tMatriz  & matriz, tDir const & direccion, tCoordenada  & coor);
void laser(tMatriz  & tablero, tCoordenada const  & coor, tDir const & direccion);
tDir operator++(tDir & dir);
tDir operator--(tDir & dir);
void mostrartablero(tMatriz const & matriz);
#endif