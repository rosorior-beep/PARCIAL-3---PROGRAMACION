package org.example.mediador.colegas;

import org.example.entrega.ProcesoEntrega;
import org.example.mediador.eventos.EventoPedido;
import org.example.mediador.eventos.TipoEvento;
import org.example.modelo.Pedido;
import org.example.modelo.TipoEntrega;

import java.util.Map;

public class Despacho extends ComponenteRestaurante {
    private final Map<TipoEntrega, ProcesoEntrega> procesos;

    public Despacho(Map<TipoEntrega, ProcesoEntrega> procesos) {
        this.procesos = procesos;
    }

    public void despachar(Pedido pedido) {
        ProcesoEntrega proceso = procesos.get(pedido.getTipoEntrega());
        proceso.entregar(pedido);
        mediador.notificar(new EventoPedido(TipoEvento.PEDIDO_ENTREGADO, pedido, "entregado al cliente"));
    }
}
