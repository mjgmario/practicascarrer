// Mario Jiménez Gutiérrez
// Juego.cpp
#include "checkML.h"
#include "Juego.h"
void seleccioncartas(tVCartas & cartas, tJuego  & juego) {  // Selecciona un vector de cartas mostrando cada carta si es valida la jugada
	bool encontrado = true;
	tMano seleccion(NUMCARTAS, 0);
	tCarta carta;
	tTecla tecla;
	tecla = leertecla();

	while (tecla != TSALIR) {
		carta = tCarta(int(tecla));
		if (seleccion[carta] < juego.lista[juego.turno].mano[carta]) { // Si hay suficientes carta  se incluye
			cartas.push_back(carta);
			++seleccion[carta];
			std::cout << carta << ' '; // Sobrecarga del operador implementada en Cartas.cpp
		}
		tecla = leertecla();
	}
}

bool realizarMovimiento(tMatriz & tablero, tCoordenada &  coor, tVCartas const & vCartas) {
	//realizarMovimiento que dado un tablero, unas coordenadas y un
	//vector de cartas, devuelve cierto si joya
	bool encontrado = false;
	for (int i = 0; i < vCartas.size() && !encontrado; ++i) {
		switch (vCartas[i]) {
		case(AVANZAR):if (avanzar(tablero, tablero[coor.x][coor.y].tortuga.move, coor)) encontrado = true;
			break;
		case(GIROIZQUIERDA): --tablero[coor.x][coor.y].tortuga.move; break;
		case(GIRODERECHA): ++tablero[coor.x][coor.y].tortuga.move; break;
		case(LASER): laser(tablero, coor, tablero[coor.x][coor.y].tortuga.move);
		}
	}
	return encontrado;
}
// incremento prefijo


bool ejecutarturno(tJuego & juego) {
	bool oki = false;
	char tipoMov;
	std::string const frase1 = "Incorrecta introduzca de nuevo '\n'";
	std::string const frase2 = "R: robar, E secuencia '\n' ";
	std::string const frase3 = "No hay mas cartas en el mazo'\n'";
	std::cout << frase2;
	std::cin >> tipoMov;
	while (tipoMov != 'R' && tipoMov != 'E') {
		std::cout << frase1;
		std::cout << frase2;
		std::cin >> tipoMov;
	}
	if (tipoMov == 'R') {
		tCarta carta;
		bool ok = cogerCarta(juego.lista[juego.turno].mazo, carta);
		if (ok) ++juego.lista[juego.turno].mano[carta];
		else std::cout << frase3;
	}
	else { // A partir de un vector de cartas llama a la funcion seleccion de cartas
		tVCartas cartas;
		seleccioncartas(cartas, juego);
		oki = realizarMovimiento(juego.tablero, juego.lista[juego.turno].coordenada, cartas);
		devolvercartas(juego.lista[juego.turno].mazo, cartas);
	}
	return oki;
}
void cargarJuego(tJuego & juego, int numJugadores) {
	std::string fichero;
	std::cout << "Introduzca el nombre del fichero '\n'";
	std::cin >> fichero;
	while (!cargar(juego.tablero, numJugadores, fichero)) {
		std::cout << "Nombre incorrecto'\n'";
		std::cout << "Introduzca el nombre del fichero '\n'";
		std::cin >> fichero;
	}
	for (int i = 0; i < numJugadores; ++i) {
		tMano  inicio(NUMCARTAS, 0);
		tJugador jugador;
		std::cout << "Introduzca el nombre del jugador: " << i << '\n';
		std::cin >> jugador.nombre;
		jugador.mazo = crearMazoAleatorio();
		jugador.mano = inicio;
		for (int j = 0; j < NUMCARTASINI; ++j) { // Inicializa la mano a 3 cartas
			tCarta carta;
			cogerCarta(jugador.mazo, carta);
			++jugador.mano[carta];
		}

		juego.lista.push_back(jugador);
	}
	bool enc = false;
	int encontradas = 0;
	for (int f = 0; f < DIMENSION && !enc; ++f) { // Bucle para asignar a cada tortuga su coordenada
		for (int j = 0; j < DIMENSION && !enc; ++j) {
			if (juego.tablero[f][j].contenido == TORTUGA) {
				encontradas++;
				if (encontradas == numJugadores) enc = true;
				juego.lista[encontradas - 1].coordenada.x = f;
				juego.lista[encontradas - 1].coordenada.y = j;
			}
		}

	}


}




void mostrarmano(tJuego const & juego) {
	for (int i = 0; i < juego.lista.size(); ++i) {
		colorFondo(i + 1);
		if (juego.turno == i) std::cout << " > ";
		std::cout << i << juego.lista[i].nombre << ": " << juego.lista[i].mano[0] << " ^ " << juego.lista[i].mano[1] << " < " << juego.lista[i].mano[2] << " > " << juego.lista[i].mano[3] << " L " << '\n';
		colorFondo(0);
	}
}
void mostrarJuego(tJuego const & juego) { // Llama a mostrar tablero y mano de cada jugador
	mostrartablero(juego.tablero);
	mostrarmano(juego);
}