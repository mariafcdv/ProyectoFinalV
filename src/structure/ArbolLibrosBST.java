package structure;
import model.Libro;

public class ArbolLibrosBST {
    private Nodo raiz;

    private class Nodo {

        Libro libro;
        Nodo izquierda;
        Nodo derecha;

        Nodo(Libro libro) {
            this.libro = libro;
        }
    }

    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro);
    }

    private Nodo insertarRec(Nodo actual, Libro libro) {

        if (actual == null) {
            return new Nodo(libro);
        }

        if (libro.getCodigo().compareTo(actual.libro.getCodigo()) < 0) {
            actual.izquierda = insertarRec(actual.izquierda, libro);
        } else {
            actual.derecha = insertarRec(actual.derecha, libro);
        }

        return actual;
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(Nodo nodo) {

        if (nodo != null) {

            inOrdenRec(nodo.izquierda);

            System.out.println(nodo.libro);

            inOrdenRec(nodo.derecha);
        }
    }
}