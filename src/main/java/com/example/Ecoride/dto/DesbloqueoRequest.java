package com.example.Ecoride.dto;

import jakarta.validation.constraints.NotBlank;

public class DesbloqueoRequest {
    @NotBlank(message = "El id del usuario es obligatorio")
    private String idUsuario;
    @NotBlank(message = "La patente es obligatoria")
    private String patente;
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    public DesbloqueoRequest() {
    }

    public DesbloqueoRequest(String idUsuario, String patente, String metodoPago) {
        this.idUsuario = idUsuario;
        this.patente = patente;
        this.metodoPago = metodoPago;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
