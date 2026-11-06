package org.example.estado;

import org.example.modelo.Pedido;

public interface EstadoPedido {
    String getNombre();
    boolean permiteModificaciones();

    void confirmar(Pedido pedido);
    void pagar(Pedido pedido);
    void iniciarPreparacion(Pedido pedido);
    void marcarListo(Pedido pedido);
    void entregar(Pedido pedido);
    void cancelar(Pedido pedido);
}
