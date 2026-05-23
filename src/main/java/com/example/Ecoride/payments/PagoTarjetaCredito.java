package com.example.Ecoride.payments;

import org.springframework.stereotype.Component;

@Component
public class PagoTarjetaCredito implements ProcesadorPago {
    @Override
    public String cobrar(double monto) {
        String mensaje = "Cobro exitoso de $" + monto + " realizado con Tarjeta de Crédito";
        System.out.println(mensaje);
        return mensaje;
    }
}