package com.example.Ecoride.tariff;

import org.springframework.stereotype.Component;

@Component
public class TarifaTemporalClimatico implements CriterioTarifa {
    private static final double RECARGO_FIJO = 150.0;

    public double calcular(double tarifaBasePorMinuto, long minutos) {
        return tarifaBasePorMinuto * minutos + RECARGO_FIJO;
    }

    public String getNombre() { return "TEMPORAL_CLIMATICO"; }
}
