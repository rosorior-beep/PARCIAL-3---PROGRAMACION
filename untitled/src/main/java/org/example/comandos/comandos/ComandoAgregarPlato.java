package org.example.comandos.comandos;

import org.example.comandos.core.ComandoPedido;
import org.example.modelo.Pedido;
import org.example.modelo.Plato;

public class ComandoAgregarPlato extends ComandoPedido {
    private final Plato plato;
    private final int cantidad;

    public ComandoAgregarPlato(Pedido pedido, Plato plato, int cantidad) {
        super(pedido);
        this.plato = plato;
        this.cantidad = cantidad;
    }

    @Override
    protected boolean aplicar() {
        return pedido.agregarPlato(plato, cantidad);
    }

    @Override
    public String obtenerNombre() {
        return "Agregar " + cantidad + " x " + plato.getNombre();
    }
}
