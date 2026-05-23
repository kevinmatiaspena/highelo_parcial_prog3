package com.example.Ecoride.model;

public abstract class Usuario {
    private String id;
    private String nombreCompleto;

    public Usuario() {
    }

    public Usuario(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public abstract double calcularTarifaFinal(double tarifaBase);
}