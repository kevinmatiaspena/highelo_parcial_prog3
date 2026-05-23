package com.example.Ecoride.controller;

import com.example.Ecoride.dto.DesbloqueoRequest;
import com.example.Ecoride.dto.DesbloqueoResponse;
import com.example.Ecoride.exception.BateriaInsuficienteException;
import com.example.Ecoride.exception.MetodoPagoNoSoportadoException;
import com.example.Ecoride.exception.UsuarioNoEncontradoException;
import com.example.Ecoride.exception.VehiculoNoEncontradoException;
import com.example.Ecoride.service.AlquilerService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/desbloquear")
    public ResponseEntity<?> desbloquear(@RequestBody DesbloqueoRequest request) {
        try {
            DesbloqueoResponse response = alquilerService.desbloquear(request);
            return ResponseEntity.ok(response);
        } catch (VehiculoNoEncontradoException | BateriaInsuficienteException |
                 UsuarioNoEncontradoException | MetodoPagoNoSoportadoException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}