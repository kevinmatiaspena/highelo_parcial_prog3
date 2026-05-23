package com.example.Ecoride.model;

public class Monopatin extends Vehiculo {
    private boolean amortiguacionReforzada;

    public Monopatin() {
    }

    public Monopatin(String patente, int porcentajeBateria, double tarifaBase, boolean amortiguacionReforzada) {
        super(patente, porcentajeBateria, tarifaBase);
        this.amortiguacionReforzada = amortiguacionReforzada;
    }

    public boolean isAmortiguacionReforzada() {
        return amortiguacionReforzada;
    }

    public void setAmortiguacionReforzada(boolean amortiguacionReforzada) {
        this.amortiguacionReforzada = amortiguacionReforzada;
    }
}