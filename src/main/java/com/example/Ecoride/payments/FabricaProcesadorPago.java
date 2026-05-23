package com.example.Ecoride.payments;

import com.example.Ecoride.exception.MetodoPagoNoSoportadoException;
import org.springframework.stereotype.Component;

@Component
public class FabricaProcesadorPago {

    private final PagoTarjetaCredito pagoTarjetaCredito;
    private final PagoBilleteraVirtual pagoBilleteraVirtual;

    public FabricaProcesadorPago(PagoTarjetaCredito pagoTarjetaCredito,
                                 PagoBilleteraVirtual pagoBilleteraVirtual) {
        this.pagoTarjetaCredito = pagoTarjetaCredito;
        this.pagoBilleteraVirtual = pagoBilleteraVirtual;
    }

    public ProcesadorPago crearProcesador(String metodoPago) {
        if (metodoPago == null) {
            throw new MetodoPagoNoSoportadoException("Debe indicar un método de pago.");
        }

        if (metodoPago.equalsIgnoreCase("TARJETA")) {
            return pagoTarjetaCredito;
        }

        if (metodoPago.equalsIgnoreCase("BILLETERA")) {
            return pagoBilleteraVirtual;
        }

        throw new MetodoPagoNoSoportadoException("Método de pago no soportado: " + metodoPago);
    }
}