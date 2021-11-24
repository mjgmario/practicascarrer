// Mario Jiménez Gutiérrez
// Puntuaciones.cpp
#include "Puntuaciones.h"
#include "checkML.h"



void inicializarpuntuaciones(tPuntuaciones  & puntuaciones) { // Inicializa el array de punteros de puntuaciones
	puntuaciones.array_clasificacion = new tPuntuacionJugador*[TAM];

}
bool cargarpuntuaciones(tPuntuaciones & puntuaciones) { // Carga puntuaciones a partir del fichero Puntuaciones.txt
	inicializarpuntuaciones(puntuaciones);
	puntuaciones.capacidad = TAM;
	puntuaciones.num_jugadores = 0;
	std::ifstream archivo;
	archivo.open(NOMBRE);
	if (!archivo.is_open()) return false;
	else {
		std::string  cadena = "";
		while (!archivo.eof()) {
			tPuntuacionJugador* usuario = new tPuntuacionJugador; // Creado como variable dinámica
			archivo >> usuario->nombre >> usuario->puntuacion;
			if (puntuaciones.capacidad != puntuaciones.num_jugadores) {
				puntuaciones.array_clasificacion[puntuaciones.num_jugadores] = usuario;
				++puntuaciones.num_jugadores;
			}
			else {
				redimensionar(puntuaciones);
				puntuaciones.array_clasificacion[puntuaciones.num_jugadores] = usuario;
				++puntuaciones.num_jugadores;
			}
			getline(archivo, cadena);
		}
		archivo.close();
		return true;
	}
}
void guardarPuntuaciones(tPuntuaciones const & puntos) { // Guarda puntuaciones en Puntuaciones.txt
	std::ofstream archivo;
	archivo.open(NOMBRE);
	if (puntos.num_jugadores != 0) {
		for (int i = 0; i < puntos.num_jugadores - 1; ++i) {
			archivo << puntos.array_clasificacion[i]->nombre << " " << puntos.array_clasificacion[i]->puntuacion << '\n';
		}
		archivo << puntos.array_clasificacion[puntos.num_jugadores - 1]->nombre << " " << puntos.array_clasificacion[puntos.num_jugadores - 1]->puntuacion;
	}
	archivo.close();
}

void mostrarPuntuaciones(tPuntuaciones const & puntos) {
	for (int i = 0; i < puntos.num_jugadores; ++i) {
		std::cout << puntos.array_clasificacion[i]->nombre << " " << puntos.array_clasificacion[i]->puntuacion << '\n';
	}
}
struct comp1 { // Comparador principal, mira primero la puntuacion, y en caso de ser igual ordena de forma alfabética
	bool operator ()(tPuntuacionJugador*  puntuacion1, tPuntuacionJugador*  puntuacion2) {
		if (puntuacion1->puntuacion == puntuacion2->puntuacion) return (aMinusculas(puntuacion1->nombre) < aMinusculas(puntuacion2->nombre));
		else return puntuacion1->puntuacion > puntuacion2->puntuacion;
	}
};
bool buscarbinaria(tPuntuaciones & puntos, tPuntuacionJugador* const  usuario, int & pos) { // Ver const &

	int ini = 0, fin = puntos.num_jugadores, mitad;
	bool encontrado = false;

	while (ini	<	fin && !encontrado) {
		mitad = (ini + fin - 1) / 2;	//	división	entera	
		if (comp1()(usuario, puntos.array_clasificacion[mitad]))	fin = mitad;
		else if (comp1()(puntos.array_clasificacion[mitad], usuario))	ini = mitad + 1;
		else encontrado = true;
	}
	if (encontrado)	pos = mitad;
	else pos = ini;	//	en	la	posición mitad
	return encontrado;
}

void insertar1(tPuntuaciones & puntos, tPuntuacionJugador* const usuario, int pos1) { // Inserta un elemento que no está
	if (puntos.num_jugadores != 1) {
		for (int i = puntos.num_jugadores - 1; i > pos1; --i) { // Desplaza a la derecha para crear el hueco
			puntos.array_clasificacion[i] = puntos.array_clasificacion[i - 1];
		}
	}
	puntos.array_clasificacion[pos1] = usuario;
}
void insertar2(tPuntuaciones & puntos, tPuntuacionJugador* const  usuario, int pos1, int pos2) { // Recoloca el elemento en su lugar correspondiente
	delete puntos.array_clasificacion[pos2]; // Elimina el elemento cambiándolo por el creado en función llamante
	for (int i = pos1 + 1; i < pos2 + 1; ++i) {
		puntos.array_clasificacion[i - 1] = puntos.array_clasificacion[i]; // Desplaza a la izquierda para recolocar
	}
	puntos.array_clasificacion[pos2] = usuario;
}
void actualizarPuntuacion(tPuntuaciones & puntos, std::string const & nombre, int nuevos) {
	int posicion1 = 0;

	tPuntuacionJugador* usuario = new tPuntuacionJugador;
	usuario->nombre = nombre;
	usuario->puntuacion = nuevos;
	int i = 0;
	if (puntos.num_jugadores != 0) {
		while (i < puntos.num_jugadores && usuario->nombre != puntos.array_clasificacion[i]->nombre) {
			++i;
		}
	}
	if (i == puntos.num_jugadores) { // No está el elemento
		if (puntos.capacidad == puntos.num_jugadores) {
			redimensionar(puntos);
		}


		buscarbinaria(puntos, usuario, posicion1); // Se busca la posición que le corresponde
		++puntos.num_jugadores;
		insertar1(puntos, usuario, posicion1);
	}
	else {// SI
		usuario->puntuacion = puntos.array_clasificacion[i]->puntuacion + nuevos;
		buscarbinaria(puntos, usuario, posicion1);
		insertar2(puntos, usuario, i, posicion1); // Se envia la primera y en la que debe estar

	}



}

void redimensionar(tPuntuaciones & clasificacion) {
	tPuntuacionJugador** aux = clasificacion.array_clasificacion;
	clasificacion.array_clasificacion = new tPuntuacionJugador*[clasificacion.capacidad + 4];
	for (int i = 0; i < clasificacion.capacidad; ++i) {
		clasificacion.array_clasificacion[i] = aux[i];
	}

	delete[] aux;
	clasificacion.capacidad += 4;
}
void liberar1(tPuntuaciones & clasificacion) { // Primero elimina los elementos del array, y después el array
	for (int i = 0; i < clasificacion.num_jugadores; ++i) {
		delete clasificacion.array_clasificacion[i];
	}
	delete[] clasificacion.array_clasificacion;
}

std::string aMinusculas(std::string const&	Palabra) {
	std::string palabra = Palabra;
	for (size_t i = 0; i < palabra.length(); ++i)
		if ('A' <= palabra[i] && palabra[i] <= 'Z')
			palabra[i] = 'a' + (palabra[i] - 'A');
	return palabra;
}

struct comp2 { // 2 tipo de comparacion, por orden alfabetico
	bool operator ()(tPuntuacionJugador * puntuacion1, tPuntuacionJugador * puntuacion2) {
		return (aMinusculas(puntuacion1->nombre) < aMinusculas(puntuacion2->nombre));
	}
};

void mostraralfab(tPuntuaciones const & puntuaciones) { // Se utiliza un vector auxiliar de punteros creados de forma dinamica
	std::vector <tPuntuacionJugador*> puntuaciones2(puntuaciones.num_jugadores);
	for (int i = 0; i < puntuaciones.num_jugadores; ++i) {
		tPuntuacionJugador* punt = new tPuntuacionJugador;
		punt->nombre = puntuaciones.array_clasificacion[i]->nombre;
		punt->puntuacion = puntuaciones.array_clasificacion[i]->puntuacion;
		puntuaciones2[i] = punt;
	}
	sort(puntuaciones2.begin(), puntuaciones2.end(), comp2());
	for (int i = 0; i < puntuaciones2.size(); ++i) {
		std::cout << puntuaciones2[i]->nombre <<" "<< puntuaciones2[i]->puntuacion << '\n';
	}
	for (int i = 0; i < puntuaciones2.size(); ++i) {
		delete puntuaciones2[i];
	}
}