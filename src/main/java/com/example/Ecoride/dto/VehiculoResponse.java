package com.example.Ecoride.dto;

import com.example.Ecoride.model.Vehiculo;

public class VehiculoResponse {
    private final String patente;
    private final String tipo;
    private final int porcentajeBateria;
    private final double tarifaBase;
    private final String faseActual;

    public VehiculoResponse(Vehiculo vehiculo) {
        patente = vehiculo.getPatente();
        tipo = vehiculo.getClass().getSimpleName();
        porcentajeBateria = vehiculo.getPorcentajeBateria();
        tarifaBase = vehiculo.getTarifaBase();
        faseActual = vehiculo.getFaseActual();
    }

    public String getPatente() { return patente; }
    public String getTipo() { return tipo; }
    public int getPorcentajeBateria() { return porcentajeBateria; }
    public double getTarifaBase() { return tarifaBase; }
    public String getFaseActual() { return faseActual; }
}
