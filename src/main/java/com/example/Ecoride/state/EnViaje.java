package com.example.Ecoride.state;

import com.example.Ecoride.model.Vehiculo;

public class EnViaje extends EstadoBase {
    public String getNombre() { return "EN_VIAJE"; }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnEspera());
    }
}
