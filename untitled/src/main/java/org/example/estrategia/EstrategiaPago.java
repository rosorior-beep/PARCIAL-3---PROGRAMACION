package org.example.estrategia;

public interface EstrategiaPago {
    String getNombre();
    boolean procesarPago(double monto);
}
