package org.example.comandos.core;

public interface Comando {
    boolean ejecutar();
    void deshacer();
    String obtenerNombre();

    default boolean esReversible() {
        return true;
    }
}
