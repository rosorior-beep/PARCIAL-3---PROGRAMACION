package org.example.estrategia;

public class PagoTarjeta implements EstrategiaPago {
    private final String numeroTarjeta;
    private final double cupoDisponible;

    public PagoTarjeta(String numeroTarjeta, double cupoDisponible) {
        this.numeroTarjeta = numeroTarjeta;
        this.cupoDisponible = cupoDisponible;
    }

    @Override
    public String getNombre() { return "Tarjeta de crédito"; }

    @Override
    public boolean procesarPago(double monto) {
        String enmascarada = "**** " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
        System.out.println("  [Tarjeta] Solicitando autorización al banco para " + enmascarada + "...");
        if (monto > cupoDisponible) {
            System.out.printf("  [Tarjeta] RECHAZADA: cupo insuficiente ($%,.0f disponible)%n", cupoDisponible);
            return false;
        }
        System.out.printf("  [Tarjeta] Transacción aprobada por $%,.0f%n", monto);
        return true;
    }
}
