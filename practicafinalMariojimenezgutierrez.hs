--Mario Jimenez Gutierrez
--Practica final p declarativa
data Rel a = R [(a,a)] deriving (Read)


--ejemplos para probar las correspondientes funciones

r1 = R [(1,2),(2,1),(1,1),(2,2),(3,3)]
r2 = R [(1,2),(2,4),(4,3)]
r3 = R [(1,2),(2,3), (4,6), (1,4)]

--vemos que usamos el read por defecto por medio del deriving por comodidad
-- eq y demas lo definiremos mas adelante

--ejercicio1
--Para el ejercicio 1 la idea es sencilla: ver si no existen repetidos,
--para ello definimos funcion repetidos que se implementa facil, viendo
--que no tenemos elementos repetidos en dicha lista

esRelacion:: (Eq a)=> (Rel a) -> Bool
esRelacion (R r) = not(repetidos r)

repetidos:: (Eq a) => [a]->Bool
repetidos [] = False
repetidos (x:xs) = (elem x xs) || (repetidos xs)

--ejercicio2
--De hecho, para el Eq definimos la funcion subconjunto, que nos
-- da si una lista es subconjunto de otra, es decir, si sus elementos
-- estan contenidas en la otra
-- subconjunto simplemente por recursividad viendo que cada elemento pertenece
--a la otra

subconjunto :: (Eq a) => [a] -> [a] -> Bool
subconjunto [] _ = True
subconjunto (x:xs) y = (elem x y)&& (subconjunto xs y)

-- para eq si una contenida en la otra y viceversa

instance (Eq a) => Eq (Rel a) where
  (R r1) == (R r2) = (subconjunto r1 r2) && (subconjunto r2 r1)

  --ejercicio3
  --definimos lista_disjunta, que es una funcion que usaremos de manera
  --auxiliar en diferentes funciones posteriores
  --para implementarla bucle en el que vemos si el elemento pertenece o no

lista_disjunta:: (Eq a) => [a] -> [a]
lista_disjunta  lista = foldl (\resul x -> if not (elem x resul) then (x:resul) else resul ) [] lista

--con la auxiliar definida anteriormente iteramos viendo en el dominio el primera
--parametro, y en el soporte ambos
dominio :: (Eq a) => (Rel a) -> [a]
dominio (R lista) = lista_disjunta(foldl(\resul (x,y)->x:resul) [] lista)

soporte:: (Eq a) => (Rel a) ->[a]
soporte (R lista) = lista_disjunta(foldl(\resul (x,y)->x:y:resul) [] lista)


-- c) ver si es relacion de equivalencia
-- Vamos a hacer varios pasos:
-- En primer lugas, calculamos la clase de equivalencia de un elemento,
-- para ello, vemos los que estan relacionados por la izquierda y por la derecha,
-- con sendas funciones auxiliares que recorremos con un bucle
-- Posteriormente usamos funcion cuya utilidad es ver para cada elemento si
-- esta relacionado con cada elemento de su clase de equivalencia con
-- relacionadosEntre


relEquivalencia :: (Eq a) => (Rel a) -> Bool
relEquivalencia re = esRelacion re && funcion re (soporte re)


clase:: (Eq a) => a-> (Rel a) -> [a]
clase x re = lista_disjunta (x:(relacionadosDer x re ++ relacionadosIzq x re))

--nos seran utiles estas funciones para calcular la clase de equivalencia; la primera calcula elementos de la forma(_, x), la segunda
--(x,_)

relacionadosIzq:: (Eq a) => a -> (Rel a) -> [a]
relacionadosIzq x (R re) = foldl (\res (i,j) -> if(j==x) then i:res else res) [] re

relacionadosDer:: (Eq a) => a -> (Rel a) -> [a]
relacionadosDer x (R re) = foldl (\res (y,w) -> if(y==x) then w:res else res) [] re


--para ver si los elementos de la lista estan todos relacionados entre si, hacemos dos "bucles" foldl comprobando lo dicho
relacionadosEntre :: (Eq a)=>[a]->(Rel a)-> Bool
relacionadosEntre lista (R re) = foldl(\res1 x -> res1 && foldl(\res2 y -> res2 && (elem (x,y) re)) True lista ) True lista

--la idea es ver cada elemento su clase de equivalencia y ver si estan relacionados todo entre si por medio de la funcion auxiliar
-- relacionadosEntre

funcion :: (Eq a) => (Rel a) -> [a] -> Bool
funcion re [] = True
funcion re (x:xs) = (relacionadosEntre (clase x re) re) && (funcion re xs)


-- apartado d) ejer 3
-- la idea para calcular el cociente de un elemento es :
-- primero ver si es de equivalencia, una vez realizado esta comprobacion
-- nos damos cuenta que debe devolver una lista de listas y la idea es separar
-- en clases de equivalencia. Para ello, definimos funcion auxiliar coc,
-- donde realizamos el calculo de la clase del primer elemento y asi iteramos
-- quitando dicho conjunto hasta que tengamos la lista vacia

coc :: (Eq a) => (Rel a) -> [a] -> [[a]]
coc _ [] = []
coc re soport = let elemento = clase (head soport) re in elemento:(coc re (difconjuntos soport elemento))


difconjuntos :: (Eq a) => [a] -> [a] -> [a]
difconjuntos lista1 lista2 = [ a | a<-lista1, not (elem a lista2)]

--conjunto COCIENTE
conjCociente :: (Eq a) => (Rel a) -> [[a]]
conjCociente re = if relEquivalencia re then  coc re (soporte re) else error "No equivalencia"


-- los dos apartados siguientes no tienen complicacion: definir listas intensionales
-- y usar el constructor de relaciones

--generaDiv
generaDiv:: Int -> Int -> (Rel Int)
generaDiv n m = R[(a,b) | a <- [n..m], b<-[n..m] , mod b a == 0]

--generaGE
generaGE :: (Ord a) => [a] -> Rel a
generaGE xs = R [(a,b) | a<-xs, b<-xs, a >= b]

--COMPOSICION
-- El ultimo apartado del 3er ejercicio se implementa por medio de listas intensionales
-- con el dominio y soporte y el constructor de relaciones

composicion:: (Eq a) => (Rel a) -> (Rel a) -> (Rel a)
composicion r1 r2 = R (lista_disjunta [(a,c) | b <- dominio r2, a <- (relacionadosIzq b r1), c <- (relacionadosDer b r2)])

--EJERCICIO4

-- Para este ejercicio, primero tenemos que llevar a cabo la funcion
-- intro rel para leer una relacion introducida por el usuario,
-- para ello usaremos faux que nos leera tantos pares como el usuario desee hasta
-- que introduzca 0
-- leemos enteros por simplificar enteros, mejor que se introduzcan de un digito
-- para que se ajuste bien a la hora de enseñarlo

anadir:: Rel Int-> (Int,Int)->Rel Int
anadir (R xs) (a,b)= R (xs++[(a,b)])



faux::Rel Int-> IO(Rel Int)
faux r= do
	linea <- getLine
	if linea == "0" then
		return r
		else do
			let valor = read linea :: (Int, Int)
			r<-return (anadir r valor)
			faux r

introRel::IO(Rel Int)
introRel = do
	r <- return (R [])
	putStrLn "De una relacion en la que cada par debe ir en una linea diferente, al finalizar inserte 0"
	faux r

-- para implementar dicho metodo, lo mostramos como se nos dice por medio de
-- listas intensionales con sucesivos foldl
instance (Show a, Eq a) => Show (Rel a) where
    show (R re) = "    " ++ concat [show x ++ "  " | x <- soporte(R re)] ++ "\n"
                  ++ concat ['\n' : show x1 ++ ':' : (foldl(\res x -> if (elem(x1,x) re) then res++"  x" else res++"   " ) "" (soporte(R re))) | x1 <- soporte(R re)]
                  ++ "\n"
