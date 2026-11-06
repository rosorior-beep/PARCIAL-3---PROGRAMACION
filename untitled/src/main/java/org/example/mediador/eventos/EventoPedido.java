package org.example.mediador.eventos;

import org.example.modelo.Pedido;

public class EventoPedido {
    private final TipoEvento tipo;
    private final Pedido pedido;
    private final String detalle;

    public EventoPedido(TipoEvento tipo, Pedido pedido, String detalle) {
        this.tipo = tipo;
        this.pedido = pedido;
        this.detalle = detalle;
    }

    public TipoEvento getTipo() { return tipo; }
    public Pedido getPedido() { return pedido; }
    public String getDetalle() { return detalle; }
}
