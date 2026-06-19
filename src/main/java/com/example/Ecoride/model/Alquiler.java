package com.example.Ecoride.model;

import java.time.Duration;
import java.time.Instant;

public class Alquiler {
    private final Usuario usuario;
    private final Vehiculo vehiculo;
    private final String metodoPago;
    private final Instant inicio;

    public Alquiler(Usuario usuario, Vehiculo vehiculo, String metodoPago) {
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.metodoPago = metodoPago;
        this.inicio = Instant.now();
    }

    public Usuario getUsuario() { return usuario; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public String getMetodoPago() { return metodoPago; }

    public long getMinutosTranscurridos() {
        long segundos = Duration.between(inicio, Instant.now()).getSeconds();
        long minutos = (segundos + 59) / 60;
        return Math.max(1, minutos);
    }
}
