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

    public Libro buscarPorCodigo(String codigo) {
        return buscarRec(raiz, codigo);
    }

    private Libro buscarRec(Nodo actual, String codigo) {
        if (actual == null) return null;
        int cmp = codigo.compareToIgnoreCase(actual.libro.getCodigo());
        if (cmp == 0) return actual.libro;
        if (cmp < 0)  return buscarRec(actual.izquierda, codigo);
        return buscarRec(actual.derecha, codigo);
    }

    public Libro buscarPorTitulo(String titulo) {
        return buscarTituloRec(raiz, titulo);
    }

    private Libro buscarTituloRec(Nodo actual, String titulo) {
        if (actual == null) return null;
        if (actual.libro.getTitulo().equalsIgnoreCase(titulo)) return actual.libro;
        Libro izq = buscarTituloRec(actual.izquierda, titulo);
        if (izq != null) return izq;
        return buscarTituloRec(actual.derecha, titulo);
    }

    public boolean eliminar(String codigo) {
        if (buscarPorCodigo(codigo) == null) return false;
        raiz = eliminarRec(raiz, codigo);
        return true;
    }

    private Nodo eliminarRec(Nodo actual, String codigo) {
        if (actual == null) return null;
        int cmp = codigo.compareToIgnoreCase(actual.libro.getCodigo());
        if (cmp < 0) {
            actual.izquierda = eliminarRec(actual.izquierda, codigo);
        } else if (cmp > 0) {
            actual.derecha = eliminarRec(actual.derecha, codigo);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (actual.izquierda == null) return actual.derecha;
            if (actual.derecha   == null) return actual.izquierda;
            // Nodo con dos hijos: sucesor en orden (mínimo del subárbol derecho)
            Nodo sucesor = minimo(actual.derecha);
            actual.libro = sucesor.libro;
            actual.derecha = eliminarRec(actual.derecha, sucesor.libro.getCodigo());
        }
        return actual;
    }

    private Nodo minimo(Nodo nodo) {
        while (nodo.izquierda != null) nodo = nodo.izquierda;
        return nodo;
    }

    public void inOrden() {
        if (raiz == null) {
            System.out.println("  No hay libros registrados.");
            return;
        }
        inOrdenRec(raiz);
    }

    private void inOrdenRec(Nodo nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda);
            System.out.println("  " + nodo.libro);
            inOrdenRec(nodo.derecha);
        }
    }

    public boolean isEmpty() { return raiz == null; }

}