package com.example.Ecoride.dto;

public class DesbloqueoResponse {
    private String mensaje;
    private String patente;
    private String tipoVehiculo;
    private double montoCobrado;
    private String detallePago;

    public DesbloqueoResponse() {
    }

    public DesbloqueoResponse(String mensaje, String patente, String tipoVehiculo, double montoCobrado, String detallePago) {
        this.mensaje = mensaje;
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.montoCobrado = montoCobrado;
        this.detallePago = detallePago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public String getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(String detallePago) {
        this.detallePago = detallePago;
    }
}