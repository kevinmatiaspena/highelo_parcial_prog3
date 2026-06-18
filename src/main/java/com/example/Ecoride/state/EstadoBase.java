package com.example.Ecoride.state;

import com.example.Ecoride.exception.TransicionEstadoInvalidaException;
import com.example.Ecoride.model.Vehiculo;

public abstract class EstadoBase implements EstadoVehiculo {
    protected void transicionInvalida(String accion) {
        throw new TransicionEstadoInvalidaException(
                "No se puede " + accion + " cuando el vehículo está " + getNombre());
    }

    public void iniciarViaje(Vehiculo vehiculo) { transicionInvalida("iniciar un viaje"); }
    public void finalizarViaje(Vehiculo vehiculo) { transicionInvalida("finalizar un viaje"); }
    public void enviarAReparacion(Vehiculo vehiculo) { transicionInvalida("enviar a reparación"); }
    public void finalizarReparacion(Vehiculo vehiculo) { transicionInvalida("finalizar la reparación"); }
}
