package service;

import model.Libro;
import model.User;
import model.Prestamo;
import structure.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {
    private final ArbolLibrosBST catalogo = new ArbolLibrosBST();
    private final MapaUsuarios usuarios = new MapaUsuarios();
    private final ListaEnlazadaPrestamos prestamos = new ListaEnlazadaPrestamos();
    private final PilaHistorial historial = new PilaHistorial();
    private final GrafoRelacionesLibros grafo = new GrafoRelacionesLibros();

    private final Map<String, ColaEspera>   colasEspera = new HashMap<>();

    public boolean registrarLibro(String codigo, String titulo, String autor) {
        if (catalogo.buscarPorCodigo(codigo) != null) {
            System.out.println("  Ya existe un libro con el código: " + codigo);
            return false;
        }
        Libro libro = new Libro(codigo, titulo, autor);
        catalogo.insertar(libro);
        grafo.agregarLibro(codigo);
        historial.agregarAccion("Libro registrado: [" + codigo + "] " + titulo);
        System.out.println("  ✓ Libro registrado exitosamente.");
        return true;
    }

    public void listarLibros() {
        System.out.println("\n  >> Catálogo de libros ");
        catalogo.inOrden();
    }

    public Libro buscarLibroPorCodigo(String codigo) {
        Libro l = catalogo.buscarPorCodigo(codigo);
        historial.agregarAccion("Búsqueda por código: " + codigo + (l != null ? " Encontrado" : " No encontrado"));
        return l;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        Libro l = catalogo.buscarPorTitulo(titulo);
        historial.agregarAccion("Búsqueda por título: " + titulo + (l != null ? " Encontrado" : " No encontrado"));
        return l;
    }

    public boolean eliminarLibro(String codigo) {
        Libro libro = catalogo.buscarPorCodigo(codigo);
        if (libro == null) {
            System.out.println(" No se encontró el libro con código: " + codigo);
            return false;
        }
        if (!libro.isDisponible()) {
            System.out.println(" No se puede eliminar un libro que está prestado.");
            return false;
        }
        catalogo.eliminar(codigo);
        historial.agregarAccion("Libro eliminado: [" + codigo + "] " + libro.getTitulo());
        System.out.println("  Libro eliminado.");
        return true;
    }

    public boolean registrarUsuario(String codigo, String nombre, String correo) {
        if (usuarios.existe(codigo)) {
            System.out.println("  Ya existe un usuario con código: " + codigo);
            return false;
        }
        usuarios.agregar(new User(codigo, nombre, correo));
        historial.agregarAccion("Usuario registrado: " + nombre + " [" + codigo + "]");
        System.out.println(" Usuario registrado exitosamente.");
        return true;
    }

    public void listarUsuarios() {
        System.out.println("\n  >> Usuarios registrados ");
        usuarios.mostrarTodos();
    }

    public User buscarUsuario(String codigo) {
        return usuarios.buscar(codigo);
    }

    public boolean registrarPrestamo(String codigoLibro, String codigoUsuario) {
        Libro libro = catalogo.buscarPorCodigo(codigoLibro);
        if (libro == null) {
            System.out.println(" El libro no existe.");
            return false;
        }
        User usuario = usuarios.buscar(codigoUsuario);
        if (usuario == null) {
            System.out.println("  El usuario no existe.");
            return false;
        }
        if (!libro.isDisponible()) {
            System.out.println("  El libro no está disponible. Puede unirse a la cola de espera.");
            return false;
        }
        Prestamo prestamo = new Prestamo(libro, usuario);
        libro.setDisponible(false);
        prestamos.agregar(prestamo);
        historial.agregarAccion("Préstamo registrado: " + prestamo.getIdPrestamo()
                + " | " + libro.getTitulo() + " → " + usuario.getNombre());
        System.out.println(" Préstamo registrado: " + prestamo.getIdPrestamo());
        return true;
    }

    public boolean registrarDevolucion(String codigoLibro) {
        Prestamo prestamo = prestamos.buscarActivoPorLibro(codigoLibro);
        if (prestamo == null) {
            System.out.println("  No se encontró un préstamo activo para ese libro.");
            return false;
        }
        prestamo.registrarDevolucion();
        prestamo.getLibro().setDisponible(true);
        historial.agregarAccion("Devolución registrada: " + prestamo.getIdPrestamo()
                + " | " + prestamo.getLibro().getTitulo());
        System.out.println("  Devolución registrada para: " + prestamo.getLibro().getTitulo());

        atenderColaEspera(codigoLibro);
        return true;
    }

    public void listarPrestamos() {
        System.out.println("\n  >> Todos los préstamos ");
        prestamos.mostrar();
    }

    public void listarPrestamosActivos() {
        System.out.println("\n >> Préstamos activos ");
        prestamos.mostrarActivos();
    }

    public boolean agregarColaEspera(String codigoLibro, String carnetUsuario) {
        Libro libro = catalogo.buscarPorCodigo(codigoLibro);
        if (libro == null) {
            System.out.println(" El libro no existe.");
            return false;
        }
        if (libro.isDisponible()) {
            System.out.println("  El libro está disponible.");
            return false;
        }
        User usuario = usuarios.buscar(carnetUsuario);
        if (usuario == null) {
            System.out.println("  El usuario no existe.");
            return false;
        }
        colasEspera.putIfAbsent(codigoLibro, new ColaEspera(codigoLibro));
        ColaEspera cola = colasEspera.get(codigoLibro);

        if (cola.estaEnCola(carnetUsuario)) {
            System.out.println("  El usuario ya está en la cola de espera.");
            return false;
        }
        cola.encolar(usuario);
        historial.agregarAccion("Usuario en cola de espera: " + usuario.getNombre()
                + " por libro [" + codigoLibro + "]");
        System.out.println(usuario.getNombre() + " agregado a la cola (posición "
                + cola.getSize() + ").");
        return true;
    }

    private void atenderColaEspera(String codigoLibro) {
        ColaEspera cola = colasEspera.get(codigoLibro);
        if (cola != null && !cola.isEmpty()) {
            User siguiente = cola.desencolar();
            System.out.println("\n  Notificación: el libro ya está disponible para → "
                    + siguiente.getNombre() + " [" + siguiente.getCodigo() + "]");
            historial.agregarAccion("Cola atendida: " + siguiente.getNombre()
                    + " puede retirar [" + codigoLibro + "]");
        }
    }

    public void mostrarColaEspera(String codigoLibro) {
        ColaEspera cola = colasEspera.get(codigoLibro);
        if (cola == null || cola.isEmpty()) {
            System.out.println("  Sin usuarios en espera para el libro: " + codigoLibro);
        } else {
            System.out.println("\n  > Cola de espera para [" + codigoLibro + "] ──");
            cola.mostrar();
        }
    }

    public void mostrarHistorial() {
        System.out.println();
        historial.mostrarHistorial();
    }

    public boolean conectarLibros(String codigo1, String codigo2) {
        if (catalogo.buscarPorCodigo(codigo1) == null) {
            System.out.println("  No existe el libro con código: " + codigo1); return false;
        }
        if (catalogo.buscarPorCodigo(codigo2) == null) {
            System.out.println("  No existe el libro con código: " + codigo2); return false;
        }
        grafo.conectar(codigo1, codigo2);
        historial.agregarAccion("Relación creada entre: " + codigo1 + " <-> " + codigo2);
        System.out.println(" -> Relación creada.");
        return true;
    }

    public void mostrarRecomendaciones(String codigoLibro) {
        Libro libro = catalogo.buscarPorCodigo(codigoLibro);
        if (libro == null) { System.out.println("  Libro no encontrado."); return; }

        List<String> recomendados = grafo.recomendar(codigoLibro);
        if (recomendados.isEmpty()) {
            System.out.println("  Sin recomendaciones para este libro aún.");
            return;
        }
        System.out.println("\n  >> Libros recomendados basados en [" + codigoLibro + "] << ");
        for (String cod : recomendados) {
            Libro r = catalogo.buscarPorCodigo(cod);
            System.out.println("  • " + (r != null ? r : cod));
        }
    }

    public void mostrarRelacionesGrafo() {
        System.out.println("\n  >> Relaciones entre libros << ");
        grafo.mostrarRelaciones();
    }
}
