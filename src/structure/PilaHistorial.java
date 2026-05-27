package structure;
import java.util.Stack;

public class PilaHistorial {

    private Stack<String> historial = new Stack<>();

    public void agregarAccion(String accion) {
        historial.push(accion);
    }

    public void mostrarHistorial() {

        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println(historial.get(i));
        }
    }
}
