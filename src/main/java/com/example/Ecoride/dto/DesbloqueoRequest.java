package com.example.Ecoride.dto;

public class DesbloqueoRequest {
    private String idUsuario;
    private String patente;
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