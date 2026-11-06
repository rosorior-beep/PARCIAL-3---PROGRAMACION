package org.example.modelo;

public class Plato {
    private final String codigo;
    private final String nombre;
    private final double precio;

    public Plato(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
}
