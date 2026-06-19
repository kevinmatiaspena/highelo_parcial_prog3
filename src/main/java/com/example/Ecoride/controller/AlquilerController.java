package com.example.Ecoride.controller;

import com.example.Ecoride.dto.DesbloqueoRequest;
import com.example.Ecoride.dto.DesbloqueoResponse;
import com.example.Ecoride.dto.FinalizarAlquilerRequest;
import com.example.Ecoride.service.AlquilerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {
    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping("/desbloquear")
    public ResponseEntity<DesbloqueoResponse> desbloquear(
            @Valid @RequestBody DesbloqueoRequest request) {
        return ResponseEntity.ok(alquilerService.desbloquear(request));
    }

    @PostMapping("/finalizar")
    public ResponseEntity<DesbloqueoResponse> finalizar(
            @Valid @RequestBody FinalizarAlquilerRequest request) {
        return ResponseEntity.ok(alquilerService.finalizar(request.getPatente()));
    }
}
