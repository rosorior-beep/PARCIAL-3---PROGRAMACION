package org.example.estado;

import org.example.modelo.Pedido;

public class EstadoListo extends EstadoBase {
    @Override
    public String getNombre() {
        return "Listo para entregar";
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEntregado());
    }
}
