package org.example.estrategia;

public class PagoNequi implements EstrategiaPago {
    private final String celular;

    public PagoNequi(String celular) {
        this.celular = celular;
    }

    @Override
    public String getNombre() { return "Nequi"; }

    @Override
    public boolean procesarPago(double monto) {
        System.out.printf("  [Nequi] Notificación push enviada al %s por $%,.0f%n", celular, monto);
        System.out.println("  [Nequi] El cliente aceptó el pago en la app.");
        return true;
    }
}
