package main;

import model.Libro;
import model.User;
import service.LibraryService;

import java.util.Scanner;

public class Menu {

    private final LibraryService service = new LibraryService();
    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n >> Menu Principal <<");
            System.out.println("1. Gestion de libros");
            System.out.println("2. Gestion de usuarios");
            System.out.println("3. Prestamos y devoluciones");
            System.out.println("4. Cola de espera");
            System.out.println("5. Grafos");
            System.out.println("6. Historial");
            System.out.println("7. Salir");
            System.out.print("> Ingresa una opción: ");

            opcion = leerInt();
            switch (opcion) {
                case 1:
                    menuLibros();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 3:
                    menuPrestamos();
                    break;
                case 4:
                    menuColaEspera();
                    break;
                case 5:
                    menuGrafo();
                    break;
                case 6:
                    service.mostrarHistorial();
                    break;
                case 7:
                    System.out.println("\n  Saliendo del programa...\n");
                    break;
                default:
                    System.out.println("  Opción no válida.");
                    break;
            }
        } while (opcion != 7);
    }

    private int leerInt() {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("  Ingrese un número válido: ");
            }
        }
    }

    private String leerLinea(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private void menuLibros() {
        int op;
        do {
            System.out.println("\n  >> Gestión de Libros ");
            System.out.println("  1. Registrar libro");
            System.out.println("  2. Mostrar todos los libros");
            System.out.println("  3. Buscar libro por código");
            System.out.println("  4. Buscar libro por título");
            System.out.println("  5. Eliminar libro");
            System.out.println("  6. Volver");
            System.out.print("  Opción: ");
            op = leerInt();

            switch (op) {
                case 1:
                    registrarLibro();
                    break;
                case 2:
                    service.listarLibros();
                    break;
                case 3: {
                    String cod = leerLinea("  Código: ");
                    Libro l = service.buscarLibroPorCodigo(cod);
                    System.out.println(l != null ? "  " + l : "  Libro no encontrado.");
                }
                break;
                case 4: {
                    String titulo = leerLinea("  Título: ");
                    Libro l = service.buscarLibroPorTitulo(titulo);
                    System.out.println(l != null ? "  " + l : "  Libro no encontrado.");
                }
                break;
                case 5: {
                    String cod = leerLinea("  Código del libro a eliminar: ");
                    service.eliminarLibro(cod);
                }
                break;
                case 6: {
                }
                break;
                default:
                    System.out.println("  Opción no válida.");
                    break;
            }
        } while (op != 6);
    }

    private void registrarLibro() {
        System.out.println("\n  >> Registrar nuevo libro <<");
        String codigo = leerLinea(" > Código    : ");
        String titulo = leerLinea(" > Título    : ");
        String autor = leerLinea(" > Autor     : ");
        service.registrarLibro(codigo, titulo, autor);
    }

    private void menuUsuarios() {
        int op;
        do {
            System.out.println("\n  >> Gestión de Usuarios << ");
            System.out.println("  1. Registrar usuario");
            System.out.println("  2. Mostrar todos los usuarios");
            System.out.println("  3. Buscar usuario");
            System.out.println("  4. Volver");
            System.out.print("  Opción: ");
            op = leerInt();

            switch (op) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    service.listarUsuarios();
                    break;
                case 3: {
                    String codigo = leerLinea("  Código de usuario: ");
                    User u = service.buscarUsuario(codigo);
                    System.out.println(u != null ? "  " + u : " Usuario no encontrado.");
                }
                break;
                case 4: {
                }
                break;
                default:
                    System.out.println("  Opción no válida.");
                    break;
            }
        } while (op != 4);
    }

    private void registrarUsuario() {
        System.out.println("\n  >> Registrar nuevo usuario <<");
        String codigo = leerLinea(" > Código  : ");
        String nombre = leerLinea(" > Nombre  : ");
        String correo = leerLinea(" > Correo  : ");
        service.registrarUsuario(codigo, nombre, correo);
    }

    private void menuPrestamos() {
        int op;
        do {
            System.out.println("\n  >> Préstamos y Devoluciones <<");
            System.out.println("  1. Registrar préstamo");
            System.out.println("  2. Registrar devolución");
            System.out.println("  3. Ver préstamos activos");
            System.out.println("  4. Ver historial completo de préstamos");
            System.out.println("  5. Volver");
            System.out.print("  Opción: ");
            op = leerInt();

            switch (op) {
                case 1 -> {
                    String cod = leerLinea("  Código del libro : ");
                    String carnet = leerLinea("  Código del usuario: ");
                    service.registrarPrestamo(cod, carnet);
                }
                case 2 -> {
                    String cod = leerLinea("  Código del libro a devolver: ");
                    service.registrarDevolucion(cod);
                }
                case 3 -> service.listarPrestamosActivos();
                case 4 -> service.listarPrestamos();
                case 0 -> {
                }
                default -> System.out.println("  Opción no válida.");
            }
        } while (op != 5);
    }

    private void menuColaEspera() {
        int op;
        do {
            System.out.println("\n  >> Cola de Espera ");
            System.out.println("  1. Unirse a cola de espera por libro");
            System.out.println("  2. Ver cola de espera de un libro");
            System.out.println("  3. Volver");
            System.out.print("  Opción: ");
            op = leerInt();

            switch (op) {
                case 1: {
                    String cod = leerLinea("  Código del libro  : ");
                    String codigo = leerLinea("  Código del usuario: ");
                    service.agregarColaEspera(cod, codigo);
                }
                break;
                case 2: {
                    String cod = leerLinea("  Código del libro: ");
                    service.mostrarColaEspera(cod);
                }
                break;
                case 0: {
                }
                break;
                default:
                    System.out.println("  Opción no válida.");
                    break;
            }
        } while (op != 3);
    }

    private void menuGrafo() {
        int op;
        do {
            System.out.println("\n  >> Relaciones entre Libros ");
            System.out.println("  1. Conectar dos libros");
            System.out.println("  2. Ver recomendaciones de un libro");
            System.out.println("  3. Ver todas las relaciones");
            System.out.println("  4. Volver");
            System.out.print("  Opción: ");
            op = leerInt();

            switch (op) {
                case 1 -> {
                    String c1 = leerLinea("  Código libro 1: ");
                    String c2 = leerLinea("  Código libro 2: ");
                    service.conectarLibros(c1, c2);
                }
                case 2 -> {
                    String cod = leerLinea("  Código del libro: ");
                    service.mostrarRecomendaciones(cod);
                }
                case 3 -> service.mostrarRelacionesGrafo();
                case 4 -> {
                }
                default -> System.out.println("  Opción no válida.");
            }
        } while (op != 4);
    }
}