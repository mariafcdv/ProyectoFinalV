package structure;
import model.Prestamo;

public class ListaEnlazadaPrestamos {

    private Nodo inicio;
    private int size;

    private static class Nodo {
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
        size++;
    }
    public Prestamo buscarActivoPorLibro(String codigoLibro) {
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.prestamo.getLibro().getCodigo().equalsIgnoreCase(codigoLibro)
                    && aux.prestamo.isActivo()) {
                return aux.prestamo;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public Prestamo buscarActivoPorUsuario(String codigo) {
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.prestamo.getUsuario().getCodigo().equalsIgnoreCase(codigo)
                    && aux.prestamo.isActivo()) {
                return aux.prestamo;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public void mostrar() {
        if (inicio == null) {
            System.out.println("  No hay préstamos registrados.");
            return;
        }
        Nodo aux = inicio;
        int i = 1;
        while (aux != null) {
            System.out.println("  " + i++ + ". " + aux.prestamo);
            aux = aux.siguiente;
        }
    }

    public void mostrarActivos() {
        Nodo aux = inicio;
        int i = 1;
        boolean hay = false;
        while (aux != null) {
            if (aux.prestamo.isActivo()) {
                System.out.println("  " + i++ + ". " + aux.prestamo);
                hay = true;
            }
            aux = aux.siguiente;
        }
        if (!hay) System.out.println("  No hay préstamos activos.");
    }

    public int getSize() { return size; }
}