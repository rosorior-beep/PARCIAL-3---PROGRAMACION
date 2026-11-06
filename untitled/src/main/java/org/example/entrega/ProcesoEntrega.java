package org.example.entrega;

import org.example.modelo.Pedido;

public abstract class ProcesoEntrega {
    public final void entregar(Pedido pedido) {
        System.out.println("  [Entrega] Modalidad: " + getModalidad());
        empacar(pedido);
        asignarResponsable(pedido);
        llevarAlCliente(pedido);
        if (requiereConfirmacionDelCliente()) {
            System.out.println("  [Entrega] El cliente confirma la recepción con el código enviado por SMS.");
        }
        pedido.entregar();
    }

    protected abstract String getModalidad();
    protected abstract void empacar(Pedido pedido);
    protected abstract void asignarResponsable(Pedido pedido);
    protected abstract void llevarAlCliente(Pedido pedido);

    protected boolean requiereConfirmacionDelCliente() {
        return false;
    }
}
