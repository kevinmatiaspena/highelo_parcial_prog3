package com.example.Ecoride.controller;

import com.example.Ecoride.dto.ErrorResponse;
import com.example.Ecoride.exception.AlquilerNoEncontradoException;
import com.example.Ecoride.exception.BateriaInsuficienteException;
import com.example.Ecoride.exception.UsuarioNoEncontradoException;
import com.example.Ecoride.exception.VehiculoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({UsuarioNoEncontradoException.class, VehiculoNoEncontradoException.class,
            AlquilerNoEncontradoException.class})
    public ResponseEntity<ErrorResponse> noEncontrado(RuntimeException ex) {
        return respuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return respuesta(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler(BateriaInsuficienteException.class)
    public ResponseEntity<ErrorResponse> bateria(BateriaInsuficienteException ex) {
        return respuesta(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> reglaNegocio(RuntimeException ex) {
        return respuesta(HttpStatus.CONFLICT, ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> respuesta(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(new ErrorResponse(estado.value(), mensaje));
    }
}
