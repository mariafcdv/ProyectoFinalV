package structure;
import java.util.*;

public class GrafoRelacionesLibros {

    private Map<String, List<String>> adyacencia;

    public GrafoRelacionesLibros() {
        adyacencia = new HashMap<>();
    }

    public void agregarLibro(String codigoLibro) {
        adyacencia.putIfAbsent(codigoLibro, new ArrayList<>());
    }

    public void conectar(String codigo1, String codigo2) {
        adyacencia.putIfAbsent(codigo1, new ArrayList<>());
        adyacencia.putIfAbsent(codigo2, new ArrayList<>());

        if (!adyacencia.get(codigo1).contains(codigo2)) {
            adyacencia.get(codigo1).add(codigo2);
        }
        if (!adyacencia.get(codigo2).contains(codigo1)) {
            adyacencia.get(codigo2).add(codigo1);
        }
    }

    public void desconectar(String codigo1, String codigo2) {
        if (adyacencia.containsKey(codigo1)) adyacencia.get(codigo1).remove(codigo2);
        if (adyacencia.containsKey(codigo2)) adyacencia.get(codigo2).remove(codigo1);
    }

    public List<String> obtenerRelacionados(String codigoLibro) {
        return adyacencia.getOrDefault(codigoLibro, Collections.emptyList());
    }

    public List<String> recomendar(String codigoOrigen) {
        List<String> resultado = new ArrayList<>();
        if (!adyacencia.containsKey(codigoOrigen)) return resultado;

        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        Map<String, Integer> nivel = new HashMap<>();

        visitados.add(codigoOrigen);
        cola.add(codigoOrigen);
        nivel.put(codigoOrigen, 0);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            int nivelActual = nivel.get(actual);

            if (nivelActual >= 2) continue; // máximo 2 saltos

            for (String vecino : adyacencia.getOrDefault(actual, Collections.emptyList())) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                    nivel.put(vecino, nivelActual + 1);
                    resultado.add(vecino);
                }
            }
        }
        return resultado;
    }
    public void mostrarRelaciones() {
        if (adyacencia.isEmpty()) {
            System.out.println("  No hay relaciones registradas.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : adyacencia.entrySet()) {
            System.out.println("  " + entry.getKey() + " <-> " + entry.getValue());
        }
    }

    public boolean existeLibro(String codigo) {
        return adyacencia.containsKey(codigo);
    }

    public boolean isEmpty() { return adyacencia.isEmpty(); }
}