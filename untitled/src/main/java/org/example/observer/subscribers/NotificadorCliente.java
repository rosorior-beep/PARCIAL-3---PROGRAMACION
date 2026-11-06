package org.example.observer.subscribers;

import org.example.modelo.Pedido;
import org.example.observer.interfaces.Observador;

public class NotificadorCliente implements Observador {
    @Override
    public void actualizar(Pedido pedido, String estadoAnterior, String estadoNuevo) {
        System.out.println("    [SMS -> " + pedido.getCliente().getNombre() + " (" + pedido.getCliente().getTelefono() + ")] "
                + "Tu pedido " + pedido.getId() + " ahora está: " + estadoNuevo);
    }
}
