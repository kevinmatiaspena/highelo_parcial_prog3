package com.example.Ecoride.tariff;

import org.springframework.stereotype.Component;

@Component
public class TarifaEstandar implements CriterioTarifa {
    public double calcular(double tarifaBasePorMinuto, long minutos) {
        return tarifaBasePorMinuto * minutos;
    }

    public String getNombre() { return "ESTANDAR"; }
}
