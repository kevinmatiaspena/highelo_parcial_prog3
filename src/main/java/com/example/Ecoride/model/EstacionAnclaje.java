package com.example.Ecoride.model;

import java.util.ArrayList;
import java.util.List;

public class EstacionAnclaje {
    private String nombre;
    private List<Vehiculo> vehiculosDisponibles;

    public EstacionAnclaje() {
        this.vehiculosDisponibles = new ArrayList<>();
    }

    public EstacionAnclaje(String nombre) {
        this.nombre = nombre;
        this.vehiculosDisponibles = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vehiculo> getVehiculosDisponibles() {
        return vehiculosDisponibles;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculosDisponibles.add(vehiculo);
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        for (Vehiculo vehiculo : vehiculosDisponibles) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                return vehiculo;
            }
        }
        return null;
    }
}