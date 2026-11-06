package org.example.mediador;

import org.example.mediador.eventos.EventoPedido;

public interface MediadorRestaurante {
    void notificar(EventoPedido evento);
}
