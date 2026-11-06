package org.example.entrega;

import org.example.modelo.Pedido;

public class EntregaDomicilio extends ProcesoEntrega {
    @Override
    protected String getModalidad() { return "Domicilio"; }

    @Override
    protected void empacar(Pedido pedido) {
        System.out.println("  [Entrega] Empacando en caja térmica sellada.");
    }

    @Override
    protected void asignarResponsable(Pedido pedido) {
        System.out.println("  [Entrega] Domiciliario asignado: Juan (moto).");
    }

    @Override
    protected void llevarAlCliente(Pedido pedido) {
        System.out.println("  [Entrega] En camino a " + pedido.getCliente().getDireccion()
                + " (" + pedido.getCliente().getBarrio() + ").");
    }

    @Override
    protected boolean requiereConfirmacionDelCliente() {
        return true;
    }
}
