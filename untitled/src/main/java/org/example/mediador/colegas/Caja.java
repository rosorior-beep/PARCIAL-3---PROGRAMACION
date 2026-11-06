package org.example.mediador.colegas;

import org.example.estrategia.EstrategiaPago;
import org.example.mediador.eventos.EventoPedido;
import org.example.mediador.eventos.TipoEvento;
import org.example.modelo.Pedido;

public class Caja extends ComponenteRestaurante {
    private EstrategiaPago estrategia;

    public void setEstrategia(EstrategiaPago estrategia) {
        this.estrategia = estrategia;
    }

    public void cobrar(Pedido pedido) {
        setEstrategia(pedido.getMetodoPago());
        if (estrategia == null) {
            throw new IllegalStateException("El pedido " + pedido.getId() + " no tiene medio de pago.");
        }
        System.out.printf("  [Caja] Cobrando $%,.0f con %s%n", pedido.getTotal(), estrategia.getNombre());

        if (estrategia.procesarPago(pedido.getTotal())) {
            pedido.pagar();
            mediador.notificar(new EventoPedido(TipoEvento.PAGO_APROBADO, pedido, "pago con " + estrategia.getNombre()));
        } else {
            mediador.notificar(new EventoPedido(TipoEvento.PAGO_RECHAZADO, pedido, "pago con " + estrategia.getNombre() + " rechazado"));
        }
    }
}
