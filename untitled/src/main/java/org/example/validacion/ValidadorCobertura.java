package org.example.validacion;

import org.example.modelo.Pedido;
import org.example.modelo.TipoEntrega;

import java.util.Set;

public class ValidadorCobertura extends ManejadorValidacion {
    private final Set<String> barriosConCobertura;

    public ValidadorCobertura(Set<String> barriosConCobertura) {
        this.barriosConCobertura = barriosConCobertura;
    }

    @Override
    public boolean validar(Pedido pedido) {
        if (pedido.getTipoEntrega() != TipoEntrega.DOMICILIO) {
            System.out.println("  [Validación 2/3] OK: no es domicilio, no requiere cobertura.");
            return pasarAlSiguiente(pedido);
        }
        String barrio = pedido.getCliente().getBarrio();
        if (!barriosConCobertura.contains(barrio)) {
            System.out.println("  [Validación 2/3] FALLÓ: el barrio '" + barrio + "' está fuera de la zona de reparto.");
            return false;
        }
        System.out.println("  [Validación 2/3] OK: el barrio '" + barrio + "' tiene cobertura.");
        return pasarAlSiguiente(pedido);
    }
}
