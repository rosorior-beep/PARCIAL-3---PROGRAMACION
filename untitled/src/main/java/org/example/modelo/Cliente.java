package org.example.modelo;

public class Cliente {
    private final String nombre;
    private final String telefono;
    private final String direccion;
    private final String barrio;

    public Cliente(String nombre, String telefono, String direccion, String barrio) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.barrio = barrio;
    }

    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getBarrio() { return barrio; }
}
