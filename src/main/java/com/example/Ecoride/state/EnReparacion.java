package com.example.Ecoride.state;

import com.example.Ecoride.model.Vehiculo;

public class EnReparacion extends EstadoBase {
    public String getNombre() { return "EN_REPARACION"; }

    @Override
    public void finalizarReparacion(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnEspera());
    }
}
