package org.example.entrega;

import org.example.modelo.Pedido;

public class EntregaEnMesa extends ProcesoEntrega {
    @Override
    protected String getModalidad() { return "En mesa"; }

    @Override
    protected void empacar(Pedido pedido) {
        System.out.println("  [Entrega] Emplatando en vajilla del restaurante.");
    }

    @Override
    protected void asignarResponsable(Pedido pedido) {
        System.out.println("  [Entrega] Mesero de turno asignado.");
    }

    @Override
    protected void llevarAlCliente(Pedido pedido) {
        System.out.println("  [Entrega] Sirviendo en la mesa " + pedido.getNumeroMesa() + ".");
    }
}
