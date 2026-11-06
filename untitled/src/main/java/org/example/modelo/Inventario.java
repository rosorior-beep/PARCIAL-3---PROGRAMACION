package org.example.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Inventario {
    private final Map<String, Integer> stock = new LinkedHashMap<>();

    public void registrar(Plato plato, int porciones) {
        stock.put(plato.getCodigo(), porciones);
    }

    public int disponible(Plato plato) {
        return stock.getOrDefault(plato.getCodigo(), 0);
    }

    public boolean hayDisponible(Plato plato, int cantidad) {
        return disponible(plato) >= cantidad;
    }

    public void descontar(Plato plato, int cantidad) {
        stock.put(plato.getCodigo(), disponible(plato) - cantidad);
    }

    public void mostrar() {
        System.out.println("\n--- INVENTARIO FINAL (porciones) ---");
        stock.forEach((codigo, cantidad) -> System.out.println("  " + codigo + ": " + cantidad));
    }
}
