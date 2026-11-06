package org.example.entrega;

import org.example.modelo.Pedido;

public class EntregaParaLlevar extends ProcesoEntrega {
    @Override
    protected String getModalidad() { return "Para llevar"; }

    @Override
    protected void empacar(Pedido pedido) {
        System.out.println("  [Entrega] Empacando en recipientes desechables con bolsa.");
    }

    @Override
    protected void asignarResponsable(Pedido pedido) {
        System.out.println("  [Entrega] Cajero asignado para entregar en mostrador.");
    }

    @Override
    protected void llevarAlCliente(Pedido pedido) {
        System.out.println("  [Entrega] Llamando a " + pedido.getCliente().getNombre() + " al mostrador.");
    }
}
