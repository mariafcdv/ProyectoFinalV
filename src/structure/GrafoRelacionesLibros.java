package structure;
import java.util.*;

public class GrafoRelacionesLibros {

    private Map<String, List<String>> grafo;

    public GrafoRelacionesLibros() {

        grafo = new HashMap<>();
    }

    public void conectarLibros(String libro1, String libro2) {

        grafo.putIfAbsent(libro1, new ArrayList<>());
        grafo.putIfAbsent(libro2, new ArrayList<>());

        grafo.get(libro1).add(libro2);
        grafo.get(libro2).add(libro1);
    }

    public void mostrarRelaciones() {

        for (String libro : grafo.keySet()) {

            System.out.println(
                    libro + " -> " + grafo.get(libro)
            );
        }
    }
}