package structure;
import model.User;
import java.util.HashMap;

public class MapaUsuarios {
    private HashMap<String, User> users;
    public MapaUsuarios(){
        users = new HashMap<>();
    }

    public void agregarUsuario(User user){
        users.put(
                user.getCarnet(),
                user
        );
    }

    public User buscarUsuario(String carnet){
        return users.get(carnet);
    }

    public void mostrarUsuarios(){

    }
}
