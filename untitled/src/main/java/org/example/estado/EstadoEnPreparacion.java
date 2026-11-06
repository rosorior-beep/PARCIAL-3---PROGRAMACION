package org.example.estado;

import org.example.modelo.Pedido;

public class EstadoEnPreparacion extends EstadoBase {
    @Override
    public String getNombre() {
        return "En preparación";
    }

    @Override
    public void marcarListo(Pedido pedido) {
        pedido.cambiarEstado(new EstadoListo());
    }
}
