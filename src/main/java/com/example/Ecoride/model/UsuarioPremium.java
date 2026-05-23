package com.example.Ecoride.model;

public class UsuarioPremium extends Usuario {
    private double porcentajeDescuento;

    public UsuarioPremium() {
    }

    public UsuarioPremium(String id, String nombreCompleto, double porcentajeDescuento) {
        super(id, nombreCompleto);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularTarifaFinal(double tarifaBase) {
        return tarifaBase - (tarifaBase * porcentajeDescuento / 100.0);
    }
}