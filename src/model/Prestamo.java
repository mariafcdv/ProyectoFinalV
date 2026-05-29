package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {

    private static int contador = 1;

    private String idPrestamo;
    private Libro libro;
    private User usuario;
    private String fechaPrestamo;
    private String fechaDevolucion;   // null mientras el libro no sea devuelto
    private boolean activo;

    public Prestamo(Libro libro, User usuario) {
        this.idPrestamo = "P-" + String.format("%03d", contador++);
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.activo = true;
    }

    public String getIdPrestamo(){
        return idPrestamo;
        }
    public Libro getLibro(){
        return libro;
        }
    public User getUsuario(){
        return usuario;
        }
    public String getFechaPrestamo(){
        return fechaPrestamo;
        }
    public String getFechaDevolucion(){
        return fechaDevolucion;
        }
    public boolean isActivo(){
        return activo;
        }

    public void registrarDevolucion() {
        this.activo = false;
        this.fechaDevolucion = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        String dev = (fechaDevolucion == null) ? "Pendiente" : fechaDevolucion;
        return String.format("%s | Libro: %s | Usuario: %s | Préstamo: %s | Devolución: %s | %s",
                idPrestamo, libro.getTitulo(), usuario.getNombre(),
                fechaPrestamo, dev, activo ? "ACTIVO" : "DEVUELTO");
    }
}
