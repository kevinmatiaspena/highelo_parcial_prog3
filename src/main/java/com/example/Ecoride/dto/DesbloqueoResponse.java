package com.example.Ecoride.dto;

public class DesbloqueoResponse {
    private final String patente;
    private final double costoFinalCalculado;
    private final long tiempoTranscurridoMinutos;
    private final String faseActual;
    private final String detallePago;

    public DesbloqueoResponse(String patente, double costoFinalCalculado,
                              long tiempoTranscurridoMinutos, String faseActual,
                              String detallePago) {
        this.patente = patente;
        this.costoFinalCalculado = costoFinalCalculado;
        this.tiempoTranscurridoMinutos = tiempoTranscurridoMinutos;
        this.faseActual = faseActual;
        this.detallePago = detallePago;
    }

    public String getPatente() { return patente; }
    public double getCostoFinalCalculado() { return costoFinalCalculado; }
    public long getTiempoTranscurridoMinutos() { return tiempoTranscurridoMinutos; }
    public String getFaseActual() { return faseActual; }
    public String getDetallePago() { return detallePago; }
}
