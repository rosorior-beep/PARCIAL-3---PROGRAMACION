package org.example.modelo;

public class ItemPedido {
    private final Plato plato;
    private final int cantidad;

    public ItemPedido(Plato plato, int cantidad) {
        this.plato = plato;
        this.cantidad = cantidad;
    }

    public Plato getPlato() { return plato; }
    public int getCantidad() { return cantidad; }

    public double getSubtotal() {
        return plato.getPrecio() * cantidad;
    }
}
