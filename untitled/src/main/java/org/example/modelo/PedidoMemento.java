package org.example.modelo;

import java.util.List;

public class PedidoMemento {
    private final List<ItemPedido> items;
    private final double porcentajeDescuento;

    PedidoMemento(List<ItemPedido> items, double porcentajeDescuento) {
        this.items = List.copyOf(items);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    List<ItemPedido> getItems() { return items; }
    double getPorcentajeDescuento() { return porcentajeDescuento; }
}
