package com.example.Ecoride.tariff;

public interface CriterioTarifa {
    double calcular(double tarifaBasePorMinuto, long minutos);
    String getNombre();
}
