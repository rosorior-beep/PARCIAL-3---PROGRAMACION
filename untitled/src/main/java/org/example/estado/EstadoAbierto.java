package org.example.estado;

import org.example.modelo.Pedido;

public class EstadoAbierto extends EstadoBase {
    @Override
    public String getNombre() { return "Abierto"; }

    @Override
    public boolean permiteModificaciones() { return true; }

    @Override
    public void confirmar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoConfirmado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
