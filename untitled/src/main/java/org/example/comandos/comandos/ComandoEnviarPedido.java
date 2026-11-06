package org.example.comandos.comandos;

import org.example.comandos.core.Comando;
import org.example.mediador.MediadorRestaurante;
import org.example.mediador.eventos.EventoPedido;
import org.example.mediador.eventos.TipoEvento;
import org.example.modelo.Pedido;

public class ComandoEnviarPedido implements Comando {
    private final Pedido pedido;
    private final MediadorRestaurante mediador;

    public ComandoEnviarPedido(Pedido pedido, MediadorRestaurante mediador) {
        this.pedido = pedido;
        this.mediador = mediador;
    }

    @Override
    public boolean ejecutar() {
        System.out.println("  [Comando] " + obtenerNombre());
        mediador.notificar(new EventoPedido(TipoEvento.PEDIDO_ENVIADO, pedido, "enviado desde la terminal del mesero"));
        return true;
    }

    @Override
    public void deshacer() {
        System.out.println("  [Comando] Un pedido enviado no se puede deshacer; debe cancelarse.");
    }

    @Override
    public boolean esReversible() {
        return false;
    }

    @Override
    public String obtenerNombre() {
        return "Enviar pedido " + pedido.getId() + " a la central";
    }
}
