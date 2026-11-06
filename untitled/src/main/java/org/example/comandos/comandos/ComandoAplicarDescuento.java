package org.example.comandos.comandos;

import org.example.comandos.core.ComandoPedido;
import org.example.modelo.Pedido;

public class ComandoAplicarDescuento extends ComandoPedido {
    private final String codigoCupon;
    private final double porcentaje;

    public ComandoAplicarDescuento(Pedido pedido, String codigoCupon, double porcentaje) {
        super(pedido);
        this.codigoCupon = codigoCupon;
        this.porcentaje = porcentaje;
    }

    @Override
    protected boolean aplicar() {
        return pedido.aplicarDescuento(porcentaje);
    }

    @Override
    public String obtenerNombre() {
        return "Aplicar cupón " + codigoCupon + " (" + (int) porcentaje + "%)";
    }
}
