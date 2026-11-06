package org.example.estrategia;

public class PagoEfectivo implements EstrategiaPago {
    private final double montoRecibido;

    public PagoEfectivo(double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    @Override
    public String getNombre() {
        return "Efectivo";
    }

    @Override
    public boolean procesarPago(double monto) {
        if (montoRecibido < monto) {
            System.out.printf("  [Efectivo] Dinero insuficiente: recibido $%,.0f, total $%,.0f%n", montoRecibido, monto);
            return false;
        }
        System.out.printf("  [Efectivo] Recibido $%,.0f. Cambio a devolver: $%,.0f%n", montoRecibido, montoRecibido - monto);
        return true;
    }
}
