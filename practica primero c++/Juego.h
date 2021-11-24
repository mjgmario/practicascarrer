// Mario Jiménez Gutiérrez
// Juego.h
/*Módulo que articula el juego, incluyendo los diferentes módulos más secundarios;
tiene la función jugar, la principal, además de la definición de tJugador,
tJuego, etc.
*/
#ifndef Juego_h
#define Juego_h
#include "Cartas.h"
#include "Tecla.h"
#include "Tablero.h"

const int NUMCARTASINI = 3;
const int NUMCARTAS = 4;

struct tJugador {
	std::string nombre;
	tMazo mazo;
	tMano mano;
	tCoordenada coordenada;
};
struct tJuego {
	int numini;
	int turno;
	std::vector <tJugador> lista;
	tMatriz tablero;
};

// void inicializarmanoyarray(tJuego & juego, int numjugadores);
void cargarJuego(tJuego & juego, int numJugadores);
void mostrarJuego(tJuego const & juego);
void mostrarmano(tJuego const & juego);
void mostrartablero(tMatriz const & matriz);
bool ejecutarturno(tJuego & juego);
void seleccioncartas(tVCartas & cartas, tJuego const & juego);
bool realizarMovimiento(tMatriz & tablero, tCoordenada &  coor, tVCartas const & vCartas);
#endif