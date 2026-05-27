package service;
import structure.ArbolLibrosBST;
import structure.ListaEnlazadaPrestamos;
import structure.PilaHistorial;
import model.Libro;

public class LibraryService {
    private ArbolLibrosBST catalogo = new ArbolLibrosBST();
    private ListaEnlazadaPrestamos prestamos = new ListaEnlazadaPrestamos();
    private final PilaHistorial historial = new PilaHistorial();

    public void registrarLibro(Libro libro) {

        catalogo.insertar(libro);

        historial.agregarAccion(
                "Libro registrado: " + libro.getTitulo()
        );
    }

    public void mostrar() {
        catalogo.inOrden();
    }
}