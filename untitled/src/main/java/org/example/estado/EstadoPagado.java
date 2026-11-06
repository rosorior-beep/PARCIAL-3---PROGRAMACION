package org.example.estado;

import org.example.modelo.Pedido;

public class EstadoPagado extends EstadoBase {
    @Override
    public String getNombre() {
        return "Pagado";
    }

    @Override
    public void iniciarPreparacion(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnPreparacion());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("  [Estado Pagado] Se emite reembolso al cliente.");
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
