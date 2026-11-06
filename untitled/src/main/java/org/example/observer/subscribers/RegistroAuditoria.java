package org.example.observer.subscribers;

import org.example.modelo.Pedido;
import org.example.observer.interfaces.Observador;

import java.util.ArrayList;
import java.util.List;

public class RegistroAuditoria implements Observador {
    private final List<String> registros = new ArrayList<>();

    @Override
    public void actualizar(Pedido pedido, String estadoAnterior, String estadoNuevo) {
        registros.add(pedido.getId() + ": " + estadoAnterior + " -> " + estadoNuevo);
    }

    public void mostrarRegistro() {
        System.out.println("\n--- REGISTRO DE AUDITORÍA (Observer) ---");
        for (int i = 0; i < registros.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + registros.get(i));
        }
    }
}
