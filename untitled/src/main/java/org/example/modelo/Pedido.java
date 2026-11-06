package org.example.modelo;

import org.example.estado.EstadoAbierto;
import org.example.estado.EstadoPedido;
import org.example.estrategia.EstrategiaPago;
import org.example.observer.interfaces.Observador;
import org.example.observer.interfaces.Sujeto;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Sujeto {
    private final String id;
    private final Cliente cliente;
    private final TipoEntrega tipoEntrega;
    private int numeroMesa;

    private List<ItemPedido> items = new ArrayList<>();
    private double porcentajeDescuento;
    private EstrategiaPago metodoPago;

    private EstadoPedido estado;
    private final List<Observador> observadores = new ArrayList<>();

    public Pedido(String id, Cliente cliente, TipoEntrega tipoEntrega) {
        this.id = id;
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.estado = new EstadoAbierto();
    }

    public boolean agregarPlato(Plato plato, int cantidad) {
        if (!puedeModificarse()) return false;
        for (int i = 0; i < items.size(); i++) {
            ItemPedido actual = items.get(i);
            if (actual.getPlato().getCodigo().equals(plato.getCodigo())) {
                items.set(i, new ItemPedido(plato, actual.getCantidad() + cantidad));
                return true;
            }
        }
        items.add(new ItemPedido(plato, cantidad));
        return true;
    }

    public boolean quitarPlato(Plato plato) {
        if (!puedeModificarse()) return false;
        return items.removeIf(item -> item.getPlato().getCodigo().equals(plato.getCodigo()));
    }

    public boolean aplicarDescuento(double porcentaje) {
        if (!puedeModificarse()) return false;
        this.porcentajeDescuento = porcentaje;
        return true;
    }

    private boolean puedeModificarse() {
        if (!estado.permiteModificaciones()) {
            System.out.println("  [Pedido " + id + "] No se puede modificar: el pedido está en estado '" + estado.getNombre() + "'.");
            return false;
        }
        return true;
    }

    public void confirmar()          { estado.confirmar(this); }
    public void pagar()              { estado.pagar(this); }
    public void iniciarPreparacion() { estado.iniciarPreparacion(this); }
    public void marcarListo()        { estado.marcarListo(this); }
    public void entregar()           { estado.entregar(this); }
    public void cancelar()           { estado.cancelar(this); }

    public void cambiarEstado(EstadoPedido nuevoEstado) {
        String anterior = this.estado.getNombre();
        this.estado = nuevoEstado;
        System.out.println("  [Pedido " + id + "] Estado: " + anterior + " -> " + nuevoEstado.getNombre());
        notificarObservadores(anterior, nuevoEstado.getNombre());
    }

    @Override
    public void suscribir(Observador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void desuscribir(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String estadoAnterior, String estadoNuevo) {
        for (Observador obs : observadores) {
            obs.actualizar(this, estadoAnterior, estadoNuevo);
        }
    }

    public PedidoMemento crearMemento() {
        return new PedidoMemento(items, porcentajeDescuento);
    }

    public void restaurar(PedidoMemento memento) {
        if (!puedeModificarse()) return;
        this.items = new ArrayList<>(memento.getItems());
        this.porcentajeDescuento = memento.getPorcentajeDescuento();
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double getValorDescuento() {
        return getSubtotal() * porcentajeDescuento / 100.0;
    }

    public double getTotal() {
        return getSubtotal() - getValorDescuento();
    }

    public void mostrarResumen() {
        System.out.println("  ----- Pedido " + id + " (" + cliente.getNombre() + ", " + tipoEntrega + ") -----");
        if (items.isEmpty()) {
            System.out.println("  (sin platos)");
        }
        for (ItemPedido item : items) {
            System.out.printf("  %-26s $%,10.0f%n", item.getCantidad() + " x " + item.getPlato().getNombre(), item.getSubtotal());
        }
        if (porcentajeDescuento > 0) {
            System.out.printf("  %-26s-$%,10.0f%n", "Descuento (" + (int) porcentajeDescuento + "%)", getValorDescuento());
        }
        System.out.printf("  %-26s $%,10.0f%n", "TOTAL", getTotal());
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public TipoEntrega getTipoEntrega() { return tipoEntrega; }
    public List<ItemPedido> getItems() { return List.copyOf(items); }
    public String getNombreEstado() { return estado.getNombre(); }
    public int getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(int numeroMesa) { this.numeroMesa = numeroMesa; }
    public EstrategiaPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(EstrategiaPago metodoPago) { this.metodoPago = metodoPago; }
}
