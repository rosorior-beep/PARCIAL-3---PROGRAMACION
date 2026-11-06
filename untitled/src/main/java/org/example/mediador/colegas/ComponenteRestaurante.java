package org.example.mediador.colegas;

import org.example.mediador.MediadorRestaurante;

public abstract class ComponenteRestaurante {
    protected MediadorRestaurante mediador;

    public void setMediador(MediadorRestaurante mediador) {
        this.mediador = mediador;
    }
}
