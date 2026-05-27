package main;
import service.LibraryService;
import model.Libro;

import java.util.Scanner;

public class Menu {

    private LibraryService service = new LibraryService();

    public void iniciar() {

        Scanner sc = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("1. Registrar libro");
            System.out.println("2. Mostrar libros");
            System.out.println("3. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine();

                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    Libro libro = new Libro(codigo, titulo, autor);

                    service.registrarLibro(libro);

                    break;

                case 2:
                    service.mostrar();
                    break;
            }

        } while (opcion != 0);
    }
}