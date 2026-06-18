package com.example.Ecoride.state;

import com.example.Ecoride.model.Vehiculo;

public interface EstadoVehiculo {
    String getNombre();
    void iniciarViaje(Vehiculo vehiculo);
    void finalizarViaje(Vehiculo vehiculo);
    void enviarAReparacion(Vehiculo vehiculo);
    void finalizarReparacion(Vehiculo vehiculo);
}
