package com.example.Ecoride.service;

import com.example.Ecoride.comparator.VehiculoPorTarifaDescendente;
import com.example.Ecoride.dto.VehiculoResponse;
import com.example.Ecoride.exception.VehiculoNoEncontradoException;
import com.example.Ecoride.model.CoordenadaGps;
import com.example.Ecoride.model.EstacionAnclaje;
import com.example.Ecoride.model.Vehiculo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    private final Map<String, Vehiculo> vehiculosPorPatente = new LinkedHashMap<>();
    private final List<EstacionAnclaje> estaciones = new ArrayList<>();

    public void agregarEstacion(EstacionAnclaje estacion) {
        estaciones.add(estacion);
        List<Vehiculo> vehiculos = estacion.getVehiculosDisponibles();
        for (Vehiculo vehiculo : vehiculos) {
            String clave = normalizar(vehiculo.getPatente());
            if (vehiculosPorPatente.containsKey(clave)) {
                throw new IllegalArgumentException("Patente duplicada: " + vehiculo.getPatente());
            }
            vehiculosPorPatente.put(clave, vehiculo);
        }
    }

    public Vehiculo buscarPorPatente(String patente) {
        Vehiculo vehiculo = vehiculosPorPatente.get(normalizar(patente));
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehículo no encontrado con patente: " + patente);
        }
        return vehiculo;
    }

    public List<VehiculoResponse> ordenarPorCarga() {
        List<Vehiculo> copia = new ArrayList<>(vehiculosPorPatente.values());
        Collections.sort(copia);
        return convertir(copia);
    }

    public List<VehiculoResponse> ordenarPorTarifaDescendente() {
        List<Vehiculo> copia = new ArrayList<>(vehiculosPorPatente.values());
        Collections.sort(copia, new VehiculoPorTarifaDescendente());
        return convertir(copia);
    }

    public List<CoordenadaGps> deduplicarAlertas(List<CoordenadaGps> alertas) {
        Set<CoordenadaGps> unicas = new HashSet<>();
        List<CoordenadaGps> resultado = new ArrayList<>();
        for (CoordenadaGps alerta : alertas) {
            if (unicas.add(alerta)) resultado.add(alerta);
        }
        return resultado;
    }

    public void enviarAReparacion(String patente) { buscarPorPatente(patente).enviarAReparacion(); }
    public void finalizarReparacion(String patente) { buscarPorPatente(patente).finalizarReparacion(); }

    private List<VehiculoResponse> convertir(List<Vehiculo> vehiculos) {
        List<VehiculoResponse> respuesta = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) respuesta.add(new VehiculoResponse(vehiculo));
        return respuesta;
    }

    private String normalizar(String patente) {
        return patente == null ? "" : patente.trim().toUpperCase();
    }
}
