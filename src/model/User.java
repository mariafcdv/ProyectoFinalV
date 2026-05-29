package model;

public class User {
    private String codigo;
    private String nombre;
    private String correo;

    public User(String codigo, String nombre, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - " + correo;
    }
}