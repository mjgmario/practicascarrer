// Mario Jiménez Gutiérrez
// Tablero.cpp
#include "Tablero.h"
#include "checkML.h"

// incremento prefijo
tDir operator++(tDir & dir) {
	dir = tDir((int(dir) + 1) % NUMDIRECCIONES);
	return dir;
}
tDir operator--(tDir & dir) {
	if (dir == UP) dir = LEFT;
	else dir = tDir((int(dir) - 1) % NUMDIRECCIONES);
	return dir;
}
// incremento posfijo
/*
tDir operator++(tDir & dir, int) {
tDir aux = dir;
dir = tDir((int(dir) + 1) % NUM_DIRECCIONES);
return aux;
}
*/

// incremento posfijo
/*
tDir operator--(tDir & dir, int) {
tDir aux = dir;
if (dir == tDir(0)) dir = tDir(3);
dir = tDir((int(dir) - 1) % NUM_DIRECCIONES);
return aux;
}
*/
void mostrartablero(tMatriz const & matriz) { // Llama a mostrar casilla
	for (int f = 0; f < DIMENSION; ++f) {
		for (int c = 0; c < DIMENSION; ++c) {
			mostrarcasilla(matriz[f][c]);
		}
		std::cout << '\n';
	}
}
bool comprobarcoordenada(tCoordenada const & coor) {
	return (coor.x < DIMENSION && coor.y < DIMENSION && coor.x >= 0 && coor.y >= 0);
}
bool movposible(tMatriz const & matriz, tDir const & direccion, tCoordenada  & coor) {
	bool ok = false;
	coor.x = coor.x + incF[direccion]; coor.y = coor.y + incC[direccion];
	if (comprobarcoordenada(coor)) {
		switch (matriz[coor.x][coor.y].contenido) {
		case(VACIA): ok = true; break;
		case(JOYA): ok = true; break;
		case(CAJA): coor.x = coor.x + incF[direccion]; coor.y = coor.y + incC[direccion];
			if (comprobarcoordenada(coor) && matriz[coor.x][coor.y].contenido == VACIA)ok = true;
		}

	}
	return ok;
}
bool avanzar(tMatriz  & matriz, tDir const & direccion, tCoordenada  & coor) { // Dada direccion, coordenada comprueba si se puede realizar el movimiento y modifica el tablero, si encuentra joya devuelve ok
	bool ok = false;
	tCoordenada coor2;
	coor2.x = coor.x;	coor2.y = coor.y;
	bool oki = movposible(matriz, direccion, coor2);
	if (oki) {
		coor.x = coor.x + incF[direccion]; coor.y = coor.y + incC[direccion];

		switch (matriz[coor.x][coor.y].contenido) {
		case(VACIA): matriz[coor.x][coor.y] = matriz[coor.x + (-1)*incF[direccion]][coor.y + (-1)*incC[direccion]];
			matriz[coor.x + (-1)*incF[direccion]][coor.y + (-1)*incC[direccion]].contenido = VACIA;
			break;
		case(JOYA): ok = true;
			matriz[coor.x][coor.y] = matriz[coor.x + (-1)*incF[direccion]][coor.y + (-1)*incC[direccion]];
			matriz[coor.x + (-1)*incF[direccion]][coor.y + (-1)*incC[direccion]].contenido = VACIA;
			break;
		case(CAJA): { coor.x = coor.x + incF[direccion]; coor.y = coor.y + incC[direccion];

			matriz[coor.x + (-1)*incF[direccion]][coor.y + (-1)*incC[direccion]] = matriz[coor.x + (-2)*incF[direccion]][coor.y + (-2)*incC[direccion]];
			matriz[coor.x + (-2)*incF[direccion]][coor.y + (-2)*incC[direccion]].contenido = VACIA;
			matriz[coor.x][coor.y].contenido = CAJA;
			coor.x = coor.x + (-1)*incF[direccion]; coor.y = coor.y + (-1)*incC[direccion];

		}
		}

	}
	return ok;

}



tcasilla  chartocasilla(char car, int ident) {
	tcasilla casilla;
	switch (car) {
	case('#'): casilla.contenido = MURO; 		break;
	case('@'): casilla.contenido = HIELO;  		break;
	case(' '): casilla.contenido = VACIA;   break;
	case('$'): casilla.contenido = JOYA;   break;
	case('C'): casilla.contenido = CAJA;    break;
	default: casilla.contenido = TORTUGA;
		casilla.tortuga.identificador = ident;
		switch (car) {
		case('U'): casilla.tortuga.move = UP;   break;
		case('D'): casilla.tortuga.move = DOWN;   break;
		case('R'): casilla.tortuga.move = RIGHT;  break;
		case('L'): casilla.tortuga.move = LEFT;
		}
	}
	return casilla;
}
void colorFondo(int color) {
	HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(handle, 15 | (color << 4));
}

void casillatostring(tcasilla const & casilla, std::string  & simbolos) {// Utiliza la funcion colordondo para cambiar el color una vez llamada
	if (casilla.contenido == TORTUGA) {
		colorFondo(PALETA[int(casilla.contenido) + 1 + int(casilla.tortuga.move)]);
	}
	else colorFondo(PALETA[int(casilla.contenido)]);
	switch (casilla.contenido) {
	case(MURO): simbolos = "||";  break;
	case(HIELO): simbolos = "**";  break;
	case(CAJA): simbolos = "[]"; break;
	case(JOYA): simbolos = "00";  break;
	case(VACIA): simbolos = "  "; break;
	case(TORTUGA): switch (casilla.tortuga.move) {
	case(UP): simbolos = "^^";  break;
	case(DOWN): simbolos = "vv"; break;
	case(RIGHT): simbolos = ">>";  break;
	case(LEFT): simbolos = "<<";
	}
	}
}

void mostrarcasilla(tcasilla const & casilla) { // Muestra una casilla
	std::string simbolos = "";
	casillatostring(casilla, simbolos);
	std::cout << simbolos;
}

bool cargar(tMatriz & matriz, int numjugadores, std::string const & fichero) {  // Carga la  matriz seleccionada con nombre de fichero pasado
	std::ifstream archivo;
	archivo.open(fichero);
	if (!archivo.is_open()) return false;
	else {
		int tortugas = 0;
		std::stringstream pp;
		std::string linea = "";
		getline(archivo, linea); pp << linea;
		int numjuego = 0;
		pp >> numjuego;
		while (numjuego != numjugadores) {
			for (int i = 0; i < DIMENSION; ++i) {
				getline(archivo, linea);
			}
			pp.clear();
			getline(archivo, linea);
			pp << linea;
			pp >> numjuego;
		}
		for (int f = 0; f < DIMENSION; ++f) {
			std::string linea;
			getline(archivo, linea);
			std::vector <tcasilla> vect(DIMENSION);
			for (int c = 0; c < DIMENSION; ++c) {
				vect[c] = chartocasilla(linea.at(c), tortugas);
				if (vect[c].contenido == TORTUGA) ++tortugas;
			}
			matriz.push_back(vect);
		}
		archivo.close();
		return true;
	}
}

void laser(tMatriz  & tablero, tCoordenada const  & coor, tDir const & direccion) {
	tCoordenada coordenada = coor;
	bool encontrado = false;
	const std::vector<int> incF{ -1,0,1,0 };
	const std::vector<int> incC{ 0,1,0,-1 };
	coordenada.x = coordenada.x + incF[direccion];
	coordenada.y = coordenada.y + incC[direccion];
	while (comprobarcoordenada(coordenada) && !encontrado) {
		switch (tablero[coordenada.x][coordenada.y].contenido) {
		case(VACIA): coordenada.x = coordenada.x + incF[direccion];
			coordenada.y = coordenada.y + incC[direccion]; break;
		case(HIELO): {
			encontrado = true;
			tablero[coordenada.x][coordenada.y].contenido = VACIA;
		}
		default: encontrado = true;
		}
	}

}
