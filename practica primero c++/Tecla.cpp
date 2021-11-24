// Mario Jiménez Gutiérrez
#include "tecla.h"
#include "checkML.h"


tTecla leertecla() { // Lee a partir de teclado la jugada
	tTecla tecla;
	bool incorrecta = true;
	std::cin.sync();
	while (incorrecta) {
		int dir = _getch(); // dir: tipo int
		if (dir == 0xe0) {
			dir = _getch();
			switch (dir) {
			case(72): tecla = TAVANZA; 			incorrecta = false; break;
			case(77): tecla = TDERECHA; 			incorrecta = false; break;
			case(75): tecla = TIZQUIERDA; 			incorrecta = false;
			}
		}
		else if (dir == 13) {
			tecla = TSALIR;
			incorrecta = false;
		}
		else if (dir == 32) {
			tecla = TLASER;
			incorrecta = false;
		}
	}
	return tecla;
}