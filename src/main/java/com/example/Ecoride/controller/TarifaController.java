package com.example.Ecoride.controller;

import com.example.Ecoride.dto.CriterioTarifaResponse;
import com.example.Ecoride.service.TarifaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaController {
    private final TarifaService tarifaService;
    public TarifaController(TarifaService tarifaService) { this.tarifaService = tarifaService; }

    @GetMapping("/criterio")
    public CriterioTarifaResponse consultar() {
        return new CriterioTarifaResponse(tarifaService.getCriterioActivo());
    }

    @PutMapping("/criterio/{nombre}")
    public ResponseEntity<CriterioTarifaResponse> cambiar(@PathVariable String nombre) {
        return ResponseEntity.ok(new CriterioTarifaResponse(tarifaService.cambiarCriterio(nombre)));
    }
}
