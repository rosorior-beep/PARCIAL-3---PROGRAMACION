package org.example.observer.interfaces;

public interface Sujeto {
    void suscribir(Observador observador);
    void desuscribir(Observador observador);
    void notificarObservadores(String estadoAnterior, String estadoNuevo);
}
