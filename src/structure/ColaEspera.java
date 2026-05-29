package structure;
import model.User;

import java.util.LinkedList;
import java.util.Queue;

public class ColaEspera {

    private Nodo frente;
    private Nodo final_;
    private String codigoLibro;
    private int size;

    private static class Nodo {
        User usuario;
        Nodo siguiente;

        Nodo(User usuario) {
            this.usuario = usuario;
        }
    }

    public ColaEspera(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public void encolar(User usuario) {
        Nodo nuevo = new Nodo(usuario);
        if (final_ == null) {
            frente = final_ = nuevo;
        } else {
            final_.siguiente = nuevo;
            final_ = nuevo;
        }
        size++;
    }
    public User desencolar() {
        if (frente == null) return null;
        User u = frente.usuario;
        frente = frente.siguiente;
        if (frente == null) final_ = null;
        size--;
        return u;
    }

    public boolean estaEnCola(String codigo) {
        Nodo aux = frente;
        while (aux != null) {
            if (aux.usuario.getCodigo().equalsIgnoreCase(codigo)) return true;
            aux = aux.siguiente;
        }
        return false;
    }

    public boolean isEmpty() { return frente == null; }
    public int getSize()  { return size; }
    public String getCodigoLibro() { return codigoLibro; }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("  Sin usuarios en espera para este libro.");
            return;
        }
        Nodo aux = frente;
        int pos = 1;
        while (aux != null) {
            System.out.println("  " + pos++ + ". " + aux.usuario);
            aux = aux.siguiente;
        }
    }
}