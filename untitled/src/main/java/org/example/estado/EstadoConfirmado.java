package org.example.estado;

import org.example.modelo.Pedido;

public class EstadoConfirmado extends EstadoBase {
    @Override
    public String getNombre() {
        return "Confirmado";
    }

    @Override
    public void pagar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoPagado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
