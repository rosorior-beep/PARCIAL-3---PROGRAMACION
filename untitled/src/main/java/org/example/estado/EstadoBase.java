package org.example.estado;

import org.example.modelo.Pedido;

public abstract class EstadoBase implements EstadoPedido {
    @Override
    public boolean permiteModificaciones() {
        return false;
    }

    protected void rechazar(Pedido pedido, String operacion) {
        System.out.println("  [Estado " + getNombre() + "] Operación '" + operacion
                + "' no permitida para el pedido " + pedido.getId() + ".");
    }

    @Override public void confirmar(Pedido pedido){
        rechazar(pedido, "confirmar");
    }
    @Override public void pagar(Pedido pedido){
        rechazar(pedido, "pagar");
    }
    @Override public void iniciarPreparacion(Pedido pedido){
        rechazar(pedido, "iniciar preparación");
    }
    @Override public void marcarListo(Pedido pedido){
        rechazar(pedido, "marcar listo");
    }
    @Override public void entregar(Pedido pedido){
        rechazar(pedido, "entregar");
    }
    @Override public void cancelar(Pedido pedido){
        rechazar(pedido, "cancelar");
    }
}
