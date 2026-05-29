package structure;
import java.util.Stack;

public class PilaHistorial {

    private static final int CAPACIDAD_INICIAL = 50;
    private String[] datos;
    private int tope;

    public PilaHistorial() {
        datos = new String[CAPACIDAD_INICIAL];
        tope = -1;
    }

    public void agregarAccion(String accion) {
        if (tope == datos.length - 1) {
            String[] nuevo = new String[datos.length * 2];
            System.arraycopy(datos, 0, nuevo, 0, datos.length);
            datos = nuevo;
        }
        datos[++tope] = accion;
    }

    public String verUltima() {
        if (isEmpty()) return null;
        return datos[tope];
    }

    public String desapilar() {
        if (isEmpty()) return null;
        return datos[tope--];
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public int size() {
        return tope + 1;
    }

    public void mostrarHistorial() {
        if (isEmpty()) {
            System.out.println("  El historial está vacío.");
            return;
        }
        System.out.println(" >> Historial de acciones <<");
        for (int i = tope; i >= 0; i--) {
            System.out.println("  " + (tope - i + 1) + ". " + datos[i]);
        }
    }
}
