package org.example.validacion;

import org.example.modelo.Inventario;
import org.example.modelo.ItemPedido;
import org.example.modelo.Pedido;

public class ValidadorInventario extends ManejadorValidacion {
    private final Inventario inventario;

    public ValidadorInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public boolean validar(Pedido pedido) {
        for (ItemPedido item : pedido.getItems()) {
            if (!inventario.hayDisponible(item.getPlato(), item.getCantidad())) {
                System.out.println("  [Validación 3/3] FALLÓ: no hay suficiente '" + item.getPlato().getNombre()
                        + "' (pedido: " + item.getCantidad() + ", disponible: " + inventario.disponible(item.getPlato()) + ").");
                return false;
            }
        }
        System.out.println("  [Validación 3/3] OK: hay inventario para todos los platos.");
        return pasarAlSiguiente(pedido);
    }
}
