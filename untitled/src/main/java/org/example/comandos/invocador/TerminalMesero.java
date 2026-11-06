package org.example.comandos.invocador;

import org.example.comandos.core.Comando;

import java.util.Stack;

public class TerminalMesero {
    private final String mesero;
    private final Stack<Comando> historial = new Stack<>();

    public TerminalMesero(String mesero) {
        this.mesero = mesero;
    }

    public void ejecutar(Comando comando) {
        if (comando.ejecutar()) {
            historial.push(comando);
        } else {
            System.out.println("  [Terminal " + mesero + "] El comando '" + comando.obtenerNombre() + "' no se pudo ejecutar.");
        }
    }

    public void deshacerUltimo() {
        if (historial.isEmpty()) {
            System.out.println("  [Terminal " + mesero + "] No hay acciones para deshacer.");
            return;
        }
        Comando ultimo = historial.peek();
        if (!ultimo.esReversible()) {
            ultimo.deshacer();
            return;
        }
        historial.pop();
        System.out.println("  [Terminal " + mesero + "] Deshaciendo -> " + ultimo.obtenerNombre());
        ultimo.deshacer();
    }

    public void mostrarHistorial() {
        System.out.println("  --- Historial de la terminal de " + mesero + " ---");
        if (historial.isEmpty()) {
            System.out.println("  (vacío)");
        }
        for (int i = 0; i < historial.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + historial.get(i).obtenerNombre());
        }
    }
}
