package com.example.Ecoride.payments;

import org.springframework.stereotype.Component;

@Component
public class PagoBilleteraVirtual implements ProcesadorPago {
    @Override
    public String cobrar(double monto) {
        String mensaje = "Cobro exitoso de $" + monto + " realizado con Billetera Virtual";
        System.out.println(mensaje);
        return mensaje;
    }
}