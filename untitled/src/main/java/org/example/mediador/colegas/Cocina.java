package org.example.mediador.colegas;

import org.example.mediador.eventos.EventoPedido;
import org.example.mediador.eventos.TipoEvento;
import org.example.modelo.Inventario;
import org.example.modelo.ItemPedido;
import org.example.modelo.Pedido;

public class Cocina extends ComponenteRestaurante {
    private final Inventario inventario;

    public Cocina(Inventario inventario) {
        this.inventario = inventario;
    }

    public void preparar(Pedido pedido) {
        pedido.iniciarPreparacion();
        for (ItemPedido item : pedido.getItems()) {
            inventario.descontar(item.getPlato(), item.getCantidad());
            System.out.println("  [Cocina] Preparando " + item.getCantidad() + " x " + item.getPlato().getNombre());
        }
        pedido.marcarListo();
        mediador.notificar(new EventoPedido(TipoEvento.PEDIDO_LISTO, pedido, "platos terminados"));
    }
}
