package org.example.comandos.core;

import org.example.modelo.Pedido;
import org.example.modelo.PedidoMemento;

public abstract class ComandoPedido implements Comando {
    protected final Pedido pedido;
    private PedidoMemento respaldo;

    protected ComandoPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public final boolean ejecutar() {
        PedidoMemento antes = pedido.crearMemento();
        boolean exito = aplicar();
        if (exito) {
            this.respaldo = antes;
            System.out.println("  [Comando] " + obtenerNombre());
        }
        return exito;
    }

    @Override
    public void deshacer() {
        if (respaldo != null) {
            pedido.restaurar(respaldo);
        }
    }

    protected abstract boolean aplicar();
}
