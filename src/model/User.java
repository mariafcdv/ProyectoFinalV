package model;

public class User {
    private String carnet;
    private String nombre;

    public User(String carnet, String nombre) {
        this.carnet = carnet;
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return carnet + " - " + nombre;
    }
}