package com.example.Ecoride.exception;

public class MetodoPagoNoSoportadoException extends RuntimeException {
    public MetodoPagoNoSoportadoException(String mensaje) {
        super(mensaje);
    }
}