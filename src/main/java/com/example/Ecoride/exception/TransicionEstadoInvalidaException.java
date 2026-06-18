package com.example.Ecoride.exception;

public class TransicionEstadoInvalidaException extends RuntimeException {
    public TransicionEstadoInvalidaException(String mensaje) {
        super(mensaje);
    }
}
