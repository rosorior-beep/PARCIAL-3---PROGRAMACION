package org.example.comandos.comandos;

import org.example.comandos.core.ComandoPedido;
import org.example.modelo.Pedido;
import org.example.modelo.Plato;

public class ComandoQuitarPlato extends ComandoPedido {
    private final Plato plato;

    public ComandoQuitarPlato(Pedido pedido, Plato plato) {
        super(pedido);
        this.plato = plato;
    }

    @Override
    protected boolean aplicar() {
        return pedido.quitarPlato(plato);
    }

    @Override
    public String obtenerNombre() {
        return "Quitar " + plato.getNombre();
    }
}
