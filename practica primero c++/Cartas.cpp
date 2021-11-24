// Mario Jiménez Gutiérrez
// Cartas.cpp
#include "checkML.h"
#include "Cartas.h"
/*/
tCarta string2carta(std::string const& s) {
tCarta c;
if (s == "AVANZAR") c = AVANZAR;
else if (s == "GIRODERECHA")  c = GIRODERECHA;
else if (s == "GIROIZQUIERDA") c = GIROIZQUIERDA;
else c = LASER;
return c;

}
*/
std::string cartastostring(tCarta const & carta) { // Carta a cadena de caracteres
	std::string resu = "";
	switch (carta) {
	case(AVANZAR): resu = "A"; break;
	case(GIROIZQUIERDA): resu = "GI"; break;
	case(GIRODERECHA): resu = "GD"; break;
	case(LASER): resu = "L";
	}
	return resu;
}
std::ostream & operator<<(std::ostream & flujo, tCarta const & carta) { // Sobrecarga del operador para mostrar una carta
	std::string conver = "";
	conver = cartastostring(carta);
	flujo << conver << " ";
	return flujo;
}
std::ostream & operator<<(std::ostream & flujo2, tMazo const & mazo) { // Sobrecarga del operador para mostrar el mazo
	for (int i = 0; i < mazo.numElem; ++i) {
		flujo2 << *(mazo.lista[(mazo.inicio + i) % NUM_CARTAS]);
	}
	flujo2 << '\n';
	return flujo2;
}
tMazo crearMazoAleatorio() { // Genera un mazo aleatorio, habiéndose proporcionada el número de cada carta
							 //Además, se inicializa ini y fin a 0
	tMazo mazo;
	inicializar(mazo);
	int i = 0;
	while (i < cartasAvanza) {
		*(mazo.lista[i]) = AVANZAR;
		++i;
	}
	while (i < cartasAvanza + cartasGI) {
		*(mazo.lista[i]) = GIROIZQUIERDA;
		++i;
	}
	while (i < cartasAvanza + cartasGI + cartasGD) {
		*(mazo.lista[i]) = GIRODERECHA;
		++i;
	}
	while (i < 	 cartasAvanza + cartasGI + cartasGD + cartasPL) {
		*(mazo.lista[i]) = LASER;
		++i;
	}
	std::random_shuffle(mazo.lista, mazo.lista + NUM_CARTAS);
	return mazo;
}
/*
void insertarCarta(tMazo & mazo, tCarta const & carta) { // Devuelve una carta

*(mazo.lista[mazo.fin]) = carta;
mazo.fin = (mazo.fin + 1) % NUM_CARTAS;
++mazo.numElem;
}
*/
void devolvercartas(tMazo & mazo, tVCartas & cartas) { //	Dado un vector de cartas lo devuelve al mazo
	int N = cartas.size();
	for (int i = 0; i < N; ++i) {
		mazo.fin = (mazo.fin + 1) % NUM_CARTAS;
		++mazo.numElem;
		cartas.pop_back();
	}
}
bool  cogerCarta(tMazo & mazo, tCarta & carta) { // Coge una carta a partir del fin del mazo, si no existe devuelve falso
	if (mazo.numElem == 0) return false;
	else {
		carta = *(mazo.lista[mazo.inicio]);
		mazo.inicio = (mazo.inicio + 1) % NUM_CARTAS;
		--mazo.numElem;
		return true;
	}

}

void inicializar(tMazo & mazo) { // Crea las cartas como variable dinamica y se inicializa el mazo
	for (int i = 0; i < NUM_CARTAS; ++i) {
		mazo.lista[i] = new tCarta;
	}
	mazo.inicio = 0;
	mazo.fin = 0;
	mazo.numElem = NUM_CARTAS;
}
void liberarmazo(tMazo & mazo) {
	for (int i = 0; i < NUM_CARTAS; ++i) {
		delete mazo.lista[i];
	}
}