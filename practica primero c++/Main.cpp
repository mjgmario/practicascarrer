// MARIO JIMÉNEZ GUTIÉRREZ

#include "checkML.h"
#include "Juego.h"
#include "Puntuaciones.h"
std::string const frase1 = "1-Jugar, 2-Mostrarpuntuaciones, 0-salir '\n'";
std::string const frase2 = "Opcion incorrecta '\n'";
std::string const frase3 = "0-Orden normal,1-orden alfabetico '\n'";
const int valMax = 2, valMin = 0, valMax2 = 1;

void jugar(tPuntuaciones & puntuaciones) {
	bool fin = false;
	tJuego juego;
	std::string frase4 = "Introduzca el numero de jugadores'\n'";
	std::cout << frase4;
	std::cin >> juego.numini;
	juego.turno = 0;
	cargarJuego(juego, juego.numini);
	while (!fin) { // Condición de salida 
		for (int i = 0; i < juego.lista.size() && !fin; ++i) {
			mostrarJuego(juego);
			if (ejecutarturno(juego)) {
				fin = true;
				actualizarPuntuacion(puntuaciones, juego.lista[i].nombre, juego.numini);
			}
			mostrarJuego(juego);
			system("cls");
			juego.turno = (juego.turno + 1) % juego.lista.size();
		}
	}
	for (int i = 0; i < juego.numini; ++i) {
		liberarmazo(juego.lista[i].mazo);
	}

}
int menu() {

	int num = 0;
	std::cout << frase1;
	std::cin >> num;
	while (num > valMax || num < valMin) {
		std::cout << frase2;
		std::cout << frase1;
		std::cin >> num;

	}
	return num;
}
int menu2() {
	int num = 0;
	std::cout << frase3;
	std::cin >> num;
	while (num > valMax2 || num < valMin) {
		std::cout << frase2;
		std::cout << frase3;
		std::cin >> num;

	}
	return num;
}
int main() {
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF); // Se indica para la memoria dinámica
	srand(time(NULL));
	std::string const  frase5 = "Juega por primera vez '\n'";
	tPuntuaciones puntos;
	if (!cargarpuntuaciones(puntos)) std::cout << frase5;
	int num = 0;
	num = menu();
	while (num != 0) {
		switch (num) {
		case(1): jugar(puntos); break;
		case(2): num = menu2();
			if (num == 0) mostrarPuntuaciones(puntos);
			else mostraralfab(puntos);
		}
		num = menu();
	}
	guardarPuntuaciones(puntos);
	liberar1(puntos);

	//system("PAUSE"); // Puesto así para poder visualizar la memoria dinámica no liberada
	return 0;
}