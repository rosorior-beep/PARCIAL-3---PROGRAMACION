package org.example.validacion;

import org.example.modelo.Pedido;

public class ValidadorPedidoNoVacio extends ManejadorValidacion {
    @Override
    public boolean validar(Pedido pedido) {
        if (pedido.getItems().isEmpty()) {
            System.out.println("  [Validación 1/3] FALLÓ: el pedido no tiene platos.");
            return false;
        }
        System.out.println("  [Validación 1/3] OK: el pedido tiene " + pedido.getItems().size() + " plato(s).");
        return pasarAlSiguiente(pedido);
    }
}
