package structure;
import model.Prestamo;

public class ListaEnlazadaPrestamos {

    private Nodo inicio;

    private class Nodo {
        Prestamo prestamo;
        Nodo siguiente;

        Nodo(Prestamo prestamo) {
            this.prestamo = prestamo;
        }
    }

    public void agregar(Prestamo prestamo) {

        Nodo nuevo = new Nodo(prestamo);

        if (inicio == null) {
            inicio = nuevo;
        } else {

            Nodo aux = inicio;

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }

            aux.siguiente = nuevo;
        }
    }

    public void mostrar() {

        Nodo aux = inicio;

        while (aux != null) {
            System.out.println(aux.prestamo);
            aux = aux.siguiente;
        }
    }
}