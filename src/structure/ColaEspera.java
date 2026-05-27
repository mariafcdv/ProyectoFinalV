package structure;
import model.User;

import java.util.LinkedList;
import java.util.Queue;

public class ColaEspera {

    private Queue<User> cola = new LinkedList<>();

    public void agregarUsuario(User usuario) {
        cola.offer(usuario);
    }

    public User atenderUsuario() {
        return cola.poll();
    }

    public void mostrarCola() {

        for (User u : cola) {
            System.out.println(u);
        }
    }
}