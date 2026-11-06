package org.example.observer.interfaces;

import org.example.modelo.Pedido;

public interface Observador {
    void actualizar(Pedido pedido, String estadoAnterior, String estadoNuevo);
}
