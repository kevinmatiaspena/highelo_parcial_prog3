package com.example.Ecoride.tariff;

import org.springframework.stereotype.Component;

@Component
public class TarifaHoraPico implements CriterioTarifa {
    public double calcular(double tarifaBasePorMinuto, long minutos) {
        return tarifaBasePorMinuto * minutos * 1.40;
    }

    public String getNombre() { return "HORA_PICO"; }
}
