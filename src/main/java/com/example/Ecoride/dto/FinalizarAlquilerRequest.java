package com.example.Ecoride.dto;

import jakarta.validation.constraints.NotBlank;

public class FinalizarAlquilerRequest {
    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    public FinalizarAlquilerRequest() { }
    public FinalizarAlquilerRequest(String patente) { this.patente = patente; }
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
}
