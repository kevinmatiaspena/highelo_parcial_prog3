package com.example.Ecoride.exception;

public class BateriaInsuficienteException extends RuntimeException {
    public BateriaInsuficienteException(String mensaje) {
        super(mensaje);
    }
}