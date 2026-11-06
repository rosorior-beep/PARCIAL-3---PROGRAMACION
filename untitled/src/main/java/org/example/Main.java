package org.example;

import org.example.comandos.comandos.ComandoAgregarPlato;
import org.example.comandos.comandos.ComandoAplicarDescuento;
import org.example.comandos.comandos.ComandoEnviarPedido;
import org.example.comandos.comandos.ComandoQuitarPlato;
import org.example.comandos.invocador.TerminalMesero;
import org.example.entrega.EntregaDomicilio;
import org.example.entrega.EntregaEnMesa;
import org.example.entrega.EntregaParaLlevar;
import org.example.estrategia.PagoEfectivo;
import org.example.estrategia.PagoNequi;
import org.example.estrategia.PagoTarjeta;
import org.example.mediador.CentralRestaurante;
import org.example.mediador.colegas.Caja;
import org.example.mediador.colegas.Cocina;
import org.example.mediador.colegas.Despacho;
import org.example.mediador.colegas.Recepcion;
import org.example.modelo.Cliente;
import org.example.modelo.Inventario;
import org.example.modelo.Pedido;
import org.example.modelo.Plato;
import org.example.modelo.TipoEntrega;
import org.example.observer.subscribers.NotificadorCliente;
import org.example.observer.subscribers.RegistroAuditoria;
import org.example.validacion.ManejadorValidacion;
import org.example.validacion.ValidadorCobertura;
import org.example.validacion.ValidadorInventario;
import org.example.validacion.ValidadorPedidoNoVacio;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Plato bandeja  = new Plato("BAN", "Bandeja paisa", 32000);
        Plato ajiaco   = new Plato("AJI", "Ajiaco", 28000);
        Plato trucha   = new Plato("TRU", "Trucha al ajillo", 35000);
        Plato limonada = new Plato("LIM", "Limonada de coco", 9000);
        Plato postre   = new Plato("POS", "Postre de natas", 8000);

        Inventario inventario = new Inventario();
        inventario.registrar(bandeja, 5);
        inventario.registrar(ajiaco, 3);
        inventario.registrar(trucha, 4);
        inventario.registrar(limonada, 10);
        inventario.registrar(postre, 4);

        ManejadorValidacion cadena = new ValidadorPedidoNoVacio();
        cadena.enlazarCon(new ValidadorCobertura(Set.of("Centro", "Norte", "La Castellana", "Laureles")))
                .enlazarCon(new ValidadorInventario(inventario));

        Despacho despacho = new Despacho(Map.of(
                TipoEntrega.DOMICILIO, new EntregaDomicilio(),
                TipoEntrega.EN_MESA, new EntregaEnMesa(),
                TipoEntrega.PARA_LLEVAR, new EntregaParaLlevar()));

        CentralRestaurante central = new CentralRestaurante(
                new Recepcion(cadena), new Caja(), new Cocina(inventario), despacho);

        NotificadorCliente notificadorSms = new NotificadorCliente();
        RegistroAuditoria auditoria = new RegistroAuditoria();

        TerminalMesero terminal = new TerminalMesero("Sofía");

        titulo("CASO 1: Domicilio con Nequi, usando deshacer y cupón");
        Cliente ana = new Cliente("Ana Gómez", "310 555 1234", "Cra 14 # 20-35", "Centro");
        Pedido pedido1 = new Pedido("PED-001", ana, TipoEntrega.DOMICILIO);
        pedido1.suscribir(notificadorSms);
        pedido1.suscribir(auditoria);
        pedido1.setMetodoPago(new PagoNequi(ana.getTelefono()));

        terminal.ejecutar(new ComandoAgregarPlato(pedido1, bandeja, 2));
        terminal.ejecutar(new ComandoAgregarPlato(pedido1, limonada, 2));
        terminal.ejecutar(new ComandoAgregarPlato(pedido1, postre, 1));
        terminal.ejecutar(new ComandoQuitarPlato(pedido1, postre));
        terminal.deshacerUltimo();
        terminal.ejecutar(new ComandoAplicarDescuento(pedido1, "QUINDIO10", 10));
        terminal.mostrarHistorial();
        pedido1.mostrarResumen();

        terminal.ejecutar(new ComandoEnviarPedido(pedido1, central));

        System.out.println("\n>> Intento de cancelar un pedido ya entregado:");
        pedido1.cancelar();

        titulo("CASO 2: Para llevar con efectivo");
        Cliente carlos = new Cliente("Carlos Ruiz", "315 222 9876", null, null);
        Pedido pedido2 = new Pedido("PED-002", carlos, TipoEntrega.PARA_LLEVAR);
        pedido2.suscribir(notificadorSms);
        pedido2.suscribir(auditoria);
        pedido2.setMetodoPago(new PagoEfectivo(50000));

        terminal.ejecutar(new ComandoAgregarPlato(pedido2, ajiaco, 1));
        terminal.ejecutar(new ComandoAgregarPlato(pedido2, limonada, 1));
        terminal.ejecutar(new ComandoEnviarPedido(pedido2, central));

        titulo("CASO 3: Domicilio fuera de la zona de cobertura");
        Cliente laura = new Cliente("Laura Pérez", "300 777 4321", "Vereda La Bella", "Calarcá");
        Pedido pedido3 = new Pedido("PED-003", laura, TipoEntrega.DOMICILIO);
        pedido3.suscribir(notificadorSms);
        pedido3.suscribir(auditoria);
        pedido3.setMetodoPago(new PagoNequi(laura.getTelefono()));

        terminal.ejecutar(new ComandoAgregarPlato(pedido3, trucha, 1));
        terminal.ejecutar(new ComandoEnviarPedido(pedido3, central));

        System.out.println("\n>> El mesero intenta seguir editando el pedido cancelado:");
        terminal.ejecutar(new ComandoAgregarPlato(pedido3, limonada, 1));
        terminal.deshacerUltimo();

        titulo("CASO 4: En mesa con tarjeta sin cupo suficiente");
        Cliente pedro = new Cliente("Pedro López", "320 444 1111", null, null);
        Pedido pedido4 = new Pedido("PED-004", pedro, TipoEntrega.EN_MESA);
        pedido4.setNumeroMesa(7);
        pedido4.suscribir(notificadorSms);
        pedido4.suscribir(auditoria);
        pedido4.setMetodoPago(new PagoTarjeta("4532015112830366", 50000));

        terminal.ejecutar(new ComandoAgregarPlato(pedido4, bandeja, 3));
        terminal.ejecutar(new ComandoEnviarPedido(pedido4, central));

        titulo("RESUMEN DEL DÍA");
        auditoria.mostrarRegistro();
        inventario.mostrar();
    }

    private static void titulo(String texto) {
        System.out.println("\n==================================================================");
        System.out.println(" " + texto);
        System.out.println("==================================================================");
    }
}
