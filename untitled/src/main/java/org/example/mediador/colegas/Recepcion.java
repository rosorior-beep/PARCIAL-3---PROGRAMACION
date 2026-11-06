package org.example.mediador.colegas;

import org.example.mediador.eventos.EventoPedido;
import org.example.mediador.eventos.TipoEvento;
import org.example.modelo.Pedido;
import org.example.validacion.ManejadorValidacion;

public class Recepcion extends ComponenteRestaurante {
    private final ManejadorValidacion cadenaValidacion;

    public Recepcion(ManejadorValidacion cadenaValidacion) {
        this.cadenaValidacion = cadenaValidacion;
    }

    public void validar(Pedido pedido) {
        pedido.mostrarResumen();
        if (cadenaValidacion.validar(pedido)) {
            pedido.confirmar();
            mediador.notificar(new EventoPedido(TipoEvento.PEDIDO_VALIDADO, pedido, "pasó todas las validaciones"));
        } else {
            mediador.notificar(new EventoPedido(TipoEvento.PEDIDO_RECHAZADO, pedido, "no pasó la validación"));
        }
    }
}
