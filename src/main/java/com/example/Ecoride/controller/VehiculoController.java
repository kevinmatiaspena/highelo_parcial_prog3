package com.example.Ecoride.controller;

import com.example.Ecoride.dto.VehiculoResponse;
import com.example.Ecoride.model.CoordenadaGps;
import com.example.Ecoride.service.VehiculoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;
    public VehiculoController(VehiculoService vehiculoService) { this.vehiculoService = vehiculoService; }

    @GetMapping("/prioridad-carga")
    public List<VehiculoResponse> prioridadCarga() { return vehiculoService.ordenarPorCarga(); }

    @GetMapping("/tarifa-descendente")
    public List<VehiculoResponse> tarifaDescendente() {
        return vehiculoService.ordenarPorTarifaDescendente();
    }

    @PostMapping("/alertas-gps/deduplicar")
    public List<CoordenadaGps> deduplicar(@RequestBody List<CoordenadaGps> alertas) {
        return vehiculoService.deduplicarAlertas(alertas);
    }

    @PostMapping("/{patente}/reparacion")
    public ResponseEntity<VehiculoResponse> enviarAReparacion(@PathVariable String patente) {
        vehiculoService.enviarAReparacion(patente);
        return ResponseEntity.ok(new VehiculoResponse(vehiculoService.buscarPorPatente(patente)));
    }

    @PostMapping("/{patente}/reparacion/finalizar")
    public ResponseEntity<VehiculoResponse> finalizarReparacion(@PathVariable String patente) {
        vehiculoService.finalizarReparacion(patente);
        return ResponseEntity.ok(new VehiculoResponse(vehiculoService.buscarPorPatente(patente)));
    }
}
