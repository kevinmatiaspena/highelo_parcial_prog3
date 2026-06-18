package com.example.Ecoride.state;

import com.example.Ecoride.model.Vehiculo;

public class EnEspera extends EstadoBase {
    public String getNombre() { return "EN_ESPERA"; }

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnViaje());
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        vehiculo.cambiarEstado(new EnReparacion());
    }
}
