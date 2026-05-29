# Proyecto | Sistema de gestion de Biblioteca universitaria
Mariafernanda Castro Del Vecchio
0900-24-15601
Programacion III - Universidad Mariano Galvez de Guatemala

## Descripcion del proyecto:
Este programa de consola es un proyecto que implementa seis estructuras de datos diferentes, cada una fue seleccionada segun las necesidades del modulo al que pertenecen, gestiona una biblioteca universitaria llevando el registro de libros, usuarios, prestamos y devoluciones, grafo que relaciona recomendaciones de libros y un historial de cambios para mantener un registro profesional de los cambios. 
-Registra, busca por codigo y elimina libros
-Registra y busca usuarios por codigo
-Permite el registro de nuevos prestamos y devoluciones dependiendo disponibilidad
-Cola de espera si un libro ya fue prestado y aun no devuelto
-Grafo que relaciona libros y muestra recomendaciones de lectura

## Estructuras de datos:
1. Lista enlazada: Registro cronologico de prestamos -> "ListaEnlazadaPrestamos"
2. Pila(LIFO): Historial de acciones -> "PilaHistorial"
3. Cola(FIFO): Espera por libro no disponible -> "ColaEspera"
4. ArbolBST: Catalogo de libros ordenado por codigo -> "ArbolLibrosBST"
5. Tabla Hash: Acceso 0(1) a usuarios por codigo -> "MapaUsuarios"
6. Grafo: Relaciones y recomendaciones de libros BST -> "GrafoRelacionesLibros"

## Requisitos de ejecucion:
-Java version 17 o superior.
-IDE recomendado: IntelliJ IDEA.

## Ejecucion:
**1. Clonar repo o descomprimir .zip, se adjuntan ambos.:**
Desde la terminal clonar URL del repositorio de GitHub o descargar carpeta .zip y descomprimir desde archivos locales.
**2. Compilar desde terminal o Java IDE:**
En terminal usar cd para abrir carpeta y crear directorio para ejecucion con el comando: mkdir -p out y encontrar fuente: find src -name "*.java | xargs javac -d out -sourcepath src
**3. Ejecutar:**
Dese **terminal** usar comando: java -cp out main.Main
**Desde IntelliJ IDEA:** abrir la carpeta `ProyectoFinal` como proyecto, marcar `src` como Sources Root y ejecutar `Main.java`.  

## Uso del programa:
Al **inicializar** se muestra un menu principal que despliega opciones dependiendo el tipo de gestion que el usuario requiere.
Cada opcion tiene un submenu que permite seleccionar el tipo de registro e ingreso de datos.

**Flujo recomendado:**
1. Ingreso de libros en **Gestion de libros**.
2. Registro de usuarios en **Gestion de usuarios**.
3. Crear un prestamo y si el libro no esta disponible, unirse a la **cola de espera**.
4. Devolver libro para que el sistema modifique la cola de espera.
5. Tras devolver libro, buscar recomendacione o crear relaciones entre libros.

## Justificacion de estructuras:
Para el **catalogo de libros se implementa un Arbol BST** porque realiza un recorrido de busqueda 0(logn) en promedio ante el 0(n) en la lista; AL ser un recorrido In order, se genera un catalogo ya ordenado para su facil acceso.
En el **mapa de usuarios se implementa un Hash Map** porque una tabla hash garantiza acceso, registro y verificacion de datos 0(1) promedio, siendo esto ideal para validaciones constantes.
La **pila tiene arreglo propio porque al llenarse duplica su tamano**, lo cual es llamado "dimensionamiento dinamico" y se logra con: System.arraycopy().
El **grafo relacional de libro se estructura con una lista adyacente** ya que esa usa 0(V+E) de memoria ante el 0(V^2) de la matriz, volviendose mas eficiente a comparacion.

## Autor
Mariafernanda Castro,
Guatemala 30 de mayo 2026  
