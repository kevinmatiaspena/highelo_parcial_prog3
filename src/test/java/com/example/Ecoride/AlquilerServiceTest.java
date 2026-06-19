package com.example.Ecoride;

import com.example.Ecoride.dto.DesbloqueoRequest;
import com.example.Ecoride.dto.DesbloqueoResponse;
import com.example.Ecoride.model.EstacionAnclaje;
import com.example.Ecoride.model.Monopatin;
import com.example.Ecoride.model.UsuarioPremium;
import com.example.Ecoride.payments.FabricaProcesadorPago;
import com.example.Ecoride.payments.PagoBilleteraVirtual;
import com.example.Ecoride.payments.PagoTarjetaCredito;
import com.example.Ecoride.service.AlquilerService;
import com.example.Ecoride.service.TarifaService;
import com.example.Ecoride.service.VehiculoService;
import com.example.Ecoride.tariff.TarifaEstandar;
import com.example.Ecoride.tariff.TarifaHoraPico;
import com.example.Ecoride.tariff.TarifaTemporalClimatico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlquilerServiceTest {
    @Test
    void desbloqueaFinalizaYCobraConDescuento() {
        VehiculoService vehiculos = new VehiculoService();
        TarifaService tarifas = new TarifaService(
                new TarifaEstandar(), new TarifaHoraPico(), new TarifaTemporalClimatico());
        FabricaProcesadorPago pagos = new FabricaProcesadorPago(
                new PagoTarjetaCredito(), new PagoBilleteraVirtual());
        AlquilerService alquileres = new AlquilerService(pagos, vehiculos, tarifas);
        alquileres.agregarUsuario(new UsuarioPremium("U1", "Ada", 10));
        EstacionAnclaje estacion = new EstacionAnclaje("Centro");
        estacion.agregarVehiculo(new Monopatin("M1", 80, 100, true));
        alquileres.agregarEstacion(estacion);

        DesbloqueoResponse inicio = alquileres.desbloquear(
                new DesbloqueoRequest("U1", "M1", "TARJETA"));
        assertEquals("EN_VIAJE", inicio.getFaseActual());
        assertEquals(90.0, inicio.getCostoFinalCalculado());

        DesbloqueoResponse fin = alquileres.finalizar("M1");
        assertEquals("EN_ESPERA", fin.getFaseActual());
        assertEquals(90.0, fin.getCostoFinalCalculado());
        assertEquals(1, fin.getTiempoTranscurridoMinutos());
    }
}
