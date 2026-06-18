package com.example.Ecoride.model;

import java.util.Objects;

public class CoordenadaGps {
    private double latitud;
    private double longitud;

    public CoordenadaGps() { }
    public CoordenadaGps(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof CoordenadaGps)) return false;
        CoordenadaGps otra = (CoordenadaGps) objeto;
        return Double.compare(latitud, otra.latitud) == 0
                && Double.compare(longitud, otra.longitud) == 0;
    }

    @Override
    public int hashCode() { return Objects.hash(latitud, longitud); }
}
