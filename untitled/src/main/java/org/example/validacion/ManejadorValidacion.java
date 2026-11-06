package org.example.validacion;

import org.example.modelo.Pedido;

public abstract class ManejadorValidacion {
    private ManejadorValidacion siguiente;

    public ManejadorValidacion enlazarCon(ManejadorValidacion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public abstract boolean validar(Pedido pedido);

    protected boolean pasarAlSiguiente(Pedido pedido) {
        if (siguiente == null) {
            return true;
        }
        return siguiente.validar(pedido);
    }
}
