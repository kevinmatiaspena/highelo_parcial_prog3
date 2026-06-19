package com.example.Ecoride.dto;

import java.time.Instant;

public class ErrorResponse {
    private final Instant timestamp = Instant.now();
    private final int estado;
    private final String error;

    public ErrorResponse(int estado, String error) {
        this.estado = estado;
        this.error = error;
    }

    public Instant getTimestamp() { return timestamp; }
    public int getEstado() { return estado; }
    public String getError() { return error; }
}
