package com.example.Ecoride.service;

import com.example.Ecoride.exception.CriterioTarifaNoSoportadoException;
import com.example.Ecoride.tariff.CriterioTarifa;
import com.example.Ecoride.tariff.TarifaEstandar;
import com.example.Ecoride.tariff.TarifaHoraPico;
import com.example.Ecoride.tariff.TarifaTemporalClimatico;
import org.springframework.stereotype.Service;

@Service
public class TarifaService {
    private final TarifaEstandar estandar;
    private final TarifaHoraPico horaPico;
    private final TarifaTemporalClimatico temporal;
    private CriterioTarifa criterioActivo;

    public TarifaService(TarifaEstandar estandar, TarifaHoraPico horaPico,
                         TarifaTemporalClimatico temporal) {
        this.estandar = estandar;
        this.horaPico = horaPico;
        this.temporal = temporal;
        this.criterioActivo = estandar;
    }

    public double calcular(double tarifaBase, long minutos) {
        return criterioActivo.calcular(tarifaBase, minutos);
    }

    public String cambiarCriterio(String nombre) {
        if ("ESTANDAR".equalsIgnoreCase(nombre)) {
            criterioActivo = estandar;
        } else if ("HORA_PICO".equalsIgnoreCase(nombre)) {
            criterioActivo = horaPico;
        } else if ("TEMPORAL_CLIMATICO".equalsIgnoreCase(nombre)) {
            criterioActivo = temporal;
        } else {
            throw new CriterioTarifaNoSoportadoException("Criterio de tarifa no soportado: " + nombre);
        }
        return criterioActivo.getNombre();
    }

    public String getCriterioActivo() { return criterioActivo.getNombre(); }
}
