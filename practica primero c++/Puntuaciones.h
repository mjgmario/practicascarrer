// Mario Jiménez Gutiérrez
// Puntuaciones.h
// Funciones relacionadas con la puntuación
#ifndef Puntuaciones_h
#define Puntuaciones_h
#include <string>
#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
const int TAM = 4;
const std::string NOMBRE = "Datos.txt";
struct tPuntuacionJugador {
	std::string nombre;
	int puntuacion;
};
struct tPuntuaciones {
	int capacidad;
	int num_jugadores;
	tPuntuacionJugador ** array_clasificacion;
};
struct comp1;
void redimensionar(tPuntuaciones & clasificacion);
struct comp2;
void mostraralfab(tPuntuaciones const & puntuaciones);
void liberar1(tPuntuaciones & clasificacion);
std::string aMinusculas(std::string const&	Palabra);
bool cargarpuntuaciones(tPuntuaciones & puntuaciones);
void guardarPuntuaciones(tPuntuaciones const & puntos);
void mostrarPuntuaciones(tPuntuaciones  const & puntos);
bool buscarbinaria(tPuntuaciones & puntos, tPuntuacionJugador* const  usuario, int & pos);
void actualizarPuntuacion(tPuntuaciones & puntos, std::string const & nombre, int nuevos);
void insertar1(tPuntuaciones & puntos, tPuntuacionJugador* usuario, int pos1);
void insertar2(tPuntuaciones & puntos, tPuntuacionJugador* const  usuario, int pos1, int pos2);
void mostraralfab(tPuntuaciones const & puntuaciones);

#endif