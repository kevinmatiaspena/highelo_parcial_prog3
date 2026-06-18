package com.example.Ecoride.model;

import com.example.Ecoride.state.EnEspera;
import com.example.Ecoride.state.EstadoVehiculo;

public abstract class Vehiculo implements Comparable<Vehiculo> {
    private String patente;
    private int porcentajeBateria;
    private double tarifaBase;
    private EstadoVehiculo estado;

    public Vehiculo() {
        this.estado = new EnEspera();
    }

    public Vehiculo(String patente, int porcentajeBateria, double tarifaBase) {
        this.patente = patente;
        this.porcentajeBateria = porcentajeBateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EnEspera();
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(int porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public String getFaseActual() {
        return estado.getNombre();
    }

    public void cambiarEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public void iniciarViaje() {
        estado.iniciarViaje(this);
    }

    public void finalizarViaje() {
        estado.finalizarViaje(this);
    }

    public void enviarAReparacion() {
        estado.enviarAReparacion(this);
    }

    public void finalizarReparacion() {
        estado.finalizarReparacion(this);
    }

    @Override
    public int compareTo(Vehiculo otro) {
        int comparacionBateria = Integer.compare(porcentajeBateria, otro.porcentajeBateria);
        if (comparacionBateria != 0) {
            return comparacionBateria;
        }
        return patente.compareToIgnoreCase(otro.patente);
    }

}
