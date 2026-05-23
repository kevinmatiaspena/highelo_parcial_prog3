package com.example.Ecoride.model;

public class BicicletaElectrica extends Vehiculo {
    private double capacidadCanastoCc;

    public BicicletaElectrica() {
    }

    public BicicletaElectrica(String patente, int porcentajeBateria, double tarifaBase, double capacidadCanastoCc) {
        super(patente, porcentajeBateria, tarifaBase);
        this.capacidadCanastoCc = capacidadCanastoCc;
    }

    public double getCapacidadCanastoCc() {
        return capacidadCanastoCc;
    }

    public void setCapacidadCanastoCc(double capacidadCanastoCc) {
        this.capacidadCanastoCc = capacidadCanastoCc;
    }
}