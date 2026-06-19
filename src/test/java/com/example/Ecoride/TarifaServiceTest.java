package com.example.Ecoride;

import com.example.Ecoride.service.TarifaService;
import com.example.Ecoride.tariff.TarifaEstandar;
import com.example.Ecoride.tariff.TarifaHoraPico;
import com.example.Ecoride.tariff.TarifaTemporalClimatico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifaServiceTest {
    @Test
    void cambiaLaEstrategiaEnTiempoDeEjecucion() {
        TarifaService servicio = new TarifaService(
                new TarifaEstandar(), new TarifaHoraPico(), new TarifaTemporalClimatico());

        assertEquals(100.0, servicio.calcular(10, 10));
        servicio.cambiarCriterio("HORA_PICO");
        assertEquals(140.0, servicio.calcular(10, 10));
        servicio.cambiarCriterio("TEMPORAL_CLIMATICO");
        assertEquals(250.0, servicio.calcular(10, 10));
    }
}
