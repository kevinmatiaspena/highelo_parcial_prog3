package com.example.Ecoride;

import com.example.Ecoride.exception.TransicionEstadoInvalidaException;
import com.example.Ecoride.model.Monopatin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class VehiculoEstadoTest {
    @Test
    void respetaLasTransicionesDelCicloDeVida() {
        Monopatin vehiculo = new Monopatin("TEST-1", 80, 10, true);
        assertEquals("EN_ESPERA", vehiculo.getFaseActual());

        vehiculo.iniciarViaje();
        assertEquals("EN_VIAJE", vehiculo.getFaseActual());
        verificarQueNoPuedeIrAReparacion(vehiculo);

        vehiculo.finalizarViaje();
        vehiculo.enviarAReparacion();
        assertEquals("EN_REPARACION", vehiculo.getFaseActual());
        vehiculo.finalizarReparacion();
        assertEquals("EN_ESPERA", vehiculo.getFaseActual());
    }

    private void verificarQueNoPuedeIrAReparacion(Monopatin vehiculo) {
        try {
            vehiculo.enviarAReparacion();
            fail("La transición de EN_VIAJE a EN_REPARACION debía rechazarse");
        } catch (TransicionEstadoInvalidaException esperada) {
            assertEquals("EN_VIAJE", vehiculo.getFaseActual());
        }
    }
}
