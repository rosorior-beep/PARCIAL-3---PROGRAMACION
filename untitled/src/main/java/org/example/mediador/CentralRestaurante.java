package org.example.mediador;

import org.example.mediador.colegas.Caja;
import org.example.mediador.colegas.Cocina;
import org.example.mediador.colegas.ComponenteRestaurante;
import org.example.mediador.colegas.Despacho;
import org.example.mediador.colegas.Recepcion;
import org.example.mediador.eventos.EventoPedido;
import org.example.modelo.Pedido;

public final class CentralRestaurante implements MediadorRestaurante {
    private final Recepcion recepcion;
    private final Caja caja;
    private final Cocina cocina;
    private final Despacho despacho;

    public CentralRestaurante(Recepcion recepcion, Caja caja, Cocina cocina, Despacho despacho) {
        this.recepcion = registrar(recepcion);
        this.caja = registrar(caja);
        this.cocina = registrar(cocina);
        this.despacho = registrar(despacho);
    }

    private <T extends ComponenteRestaurante> T registrar(T componente) {
        componente.setMediador(this);
        return componente;
    }

    @Override
    public void notificar(EventoPedido evento) {
        Pedido pedido = evento.getPedido();
        System.out.println("\n[Central] Evento " + evento.getTipo() + " | pedido " + pedido.getId() + " | " + evento.getDetalle());

        switch (evento.getTipo()) {
            case PEDIDO_ENVIADO -> {
                System.out.println("[Central] -> Recepción: validar el pedido.");
                recepcion.validar(pedido);
            }
            case PEDIDO_VALIDADO -> {
                System.out.println("[Central] -> Caja: cobrar el pedido.");
                caja.cobrar(pedido);
            }
            case PEDIDO_RECHAZADO, PAGO_RECHAZADO -> {
                System.out.println("[Central] -> Se cancela el pedido.");
                pedido.cancelar();
            }
            case PAGO_APROBADO -> {
                System.out.println("[Central] -> Cocina: preparar el pedido.");
                cocina.preparar(pedido);
            }
            case PEDIDO_LISTO -> {
                System.out.println("[Central] -> Despacho: entregar el pedido.");
                despacho.despachar(pedido);
            }
            case PEDIDO_ENTREGADO -> System.out.println("[Central] Ciclo del pedido " + pedido.getId() + " completado.");
        }
    }
}
