package com.example.Ecoride;

import com.example.Ecoride.dto.VehiculoResponse;
import com.example.Ecoride.model.CoordenadaGps;
import com.example.Ecoride.model.EstacionAnclaje;
import com.example.Ecoride.model.Monopatin;
import com.example.Ecoride.service.VehiculoService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class VehiculoServiceTest {
    private VehiculoService servicio;

    @BeforeEach
    void preparar() {
        servicio = new VehiculoService();
        EstacionAnclaje estacion = new EstacionAnclaje("Test");
        estacion.agregarVehiculo(new Monopatin("ALTA", 90, 20, true));
        estacion.agregarVehiculo(new Monopatin("BAJA", 10, 30, false));
        estacion.agregarVehiculo(new Monopatin("MEDIA", 50, 10, true));
        servicio.agregarEstacion(estacion);
    }

    @Test
    void buscaEnTiempoConstantePorPatente() {
        assertSame(servicio.buscarPorPatente("alta"), servicio.buscarPorPatente("ALTA"));
    }

    @Test
    void conservaSeparadosLosDosCriteriosDeOrdenamiento() {
        List<VehiculoResponse> porCarga = servicio.ordenarPorCarga();
        List<VehiculoResponse> porTarifa = servicio.ordenarPorTarifaDescendente();
        assertEquals("BAJA", porCarga.get(0).getPatente());
        assertEquals("ALTA", porCarga.get(2).getPatente());
        assertEquals("BAJA", porTarifa.get(0).getPatente());
        assertEquals("MEDIA", porTarifa.get(2).getPatente());
    }

    @Test
    void deduplicaGpsEnUnaSolaPasada() {
        List<CoordenadaGps> alertas = new ArrayList<>();
        alertas.add(new CoordenadaGps(-24.78, -65.41));
        alertas.add(new CoordenadaGps(-24.78, -65.41));
        alertas.add(new CoordenadaGps(-24.79, -65.42));
        assertEquals(2, servicio.deduplicarAlertas(alertas).size());
    }
}
