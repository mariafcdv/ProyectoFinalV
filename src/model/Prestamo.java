package model;

public class Prestamo {

    private Libro libro;
    private User user;
    private String fecha;

    public Prestamo(Libro libro, User user, String fecha) {
        this.libro = libro;
        this.user = user;
        this.fecha = fecha;
    }

    public Libro getLibro(){
        return libro;
    }

    public User getUser() {
        return user;
    }

    public String getFecha(){
        return fecha;
    }

    @Override
    public String toString() {
        return user.getNombre() + " tiene " + libro.getTitulo() + "fecha: " + fecha;
    }
}