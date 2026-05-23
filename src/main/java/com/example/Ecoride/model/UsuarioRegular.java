package com.example.Ecoride.model;

public class UsuarioRegular extends Usuario {

    public UsuarioRegular() {
    }

    public UsuarioRegular(String id, String nombreCompleto) {
        super(id, nombreCompleto);
    }

    @Override
    public double calcularTarifaFinal(double tarifaBase) {
        return tarifaBase;
    }
}