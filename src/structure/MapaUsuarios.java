package structure;
import model.User;
import java.util.HashMap;
import java.util.Collection;

public class MapaUsuarios {

    private HashMap<String, User> mapa;

    public MapaUsuarios() {
        mapa = new HashMap<>();
    }

    public void agregar(User usuario) {
        mapa.put(usuario.getCodigo().toLowerCase(), usuario);
    }

    public User buscar(String codigo) {
        return mapa.get(codigo.toLowerCase());
    }

    public boolean existe(String codigo) {
        return mapa.containsKey(codigo.toLowerCase());
    }

    public boolean eliminar(String codigo) {
        return mapa.remove(codigo.toLowerCase()) != null;
    }

    public void mostrarTodos() {
        if (mapa.isEmpty()) {
            System.out.println("  No hay usuarios registrados.");
            return;
        }
        int i = 1;
        for (User u : mapa.values()) {
            System.out.println("  " + i++ + ". " + u);
        }
    }

    public Collection<User> getTodos() {
        return mapa.values();
    }

    public int getSize() { return mapa.size(); }
}
