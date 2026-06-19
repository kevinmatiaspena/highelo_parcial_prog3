package com.example.Ecoride.service;

import com.example.Ecoride.dto.DesbloqueoRequest;
import com.example.Ecoride.dto.DesbloqueoResponse;
import com.example.Ecoride.exception.AlquilerNoEncontradoException;
import com.example.Ecoride.exception.BateriaInsuficienteException;
import com.example.Ecoride.exception.UsuarioNoEncontradoException;
import com.example.Ecoride.model.Alquiler;
import com.example.Ecoride.model.EstacionAnclaje;
import com.example.Ecoride.model.Usuario;
import com.example.Ecoride.model.Vehiculo;
import com.example.Ecoride.payments.FabricaProcesadorPago;
import com.example.Ecoride.payments.ProcesadorPago;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AlquilerService {
    private final Map<String, Usuario> usuariosPorId = new LinkedHashMap<>();
    private final Map<String, Alquiler> alquileresActivos = new LinkedHashMap<>();
    private final FabricaProcesadorPago fabricaProcesadorPago;
    private final VehiculoService vehiculoService;
    private final TarifaService tarifaService;

    public AlquilerService(FabricaProcesadorPago fabricaProcesadorPago,
                           VehiculoService vehiculoService, TarifaService tarifaService) {
        this.fabricaProcesadorPago = fabricaProcesadorPago;
        this.vehiculoService = vehiculoService;
        this.tarifaService = tarifaService;
    }

    public void agregarEstacion(EstacionAnclaje estacion) { vehiculoService.agregarEstacion(estacion); }

    public void agregarUsuario(Usuario usuario) {
        usuariosPorId.put(usuario.getId().toUpperCase(), usuario);
    }

    public DesbloqueoResponse desbloquear(DesbloqueoRequest request) {
        Usuario usuario = buscarUsuario(request.getIdUsuario());
        Vehiculo vehiculo = vehiculoService.buscarPorPatente(request.getPatente());
        validarBateria(vehiculo);
        fabricaProcesadorPago.crearProcesador(request.getMetodoPago());

        vehiculo.iniciarViaje();
        Alquiler alquiler = new Alquiler(usuario, vehiculo, request.getMetodoPago());
        alquileresActivos.put(normalizar(vehiculo.getPatente()), alquiler);

        double costoEstimado = calcularCosto(alquiler, 1);
        return new DesbloqueoResponse(vehiculo.getPatente(), costoEstimado, 0,
                vehiculo.getFaseActual(), "Cobro pendiente hasta finalizar el viaje");
    }

    public DesbloqueoResponse finalizar(String patente) {
        String clave = normalizar(patente);
        Alquiler alquiler = alquileresActivos.get(clave);
        if (alquiler == null) {
            throw new AlquilerNoEncontradoException("No existe un alquiler activo para: " + patente);
        }

        long minutos = alquiler.getMinutosTranscurridos();
        double costo = calcularCosto(alquiler, minutos);
        ProcesadorPago procesador = fabricaProcesadorPago.crearProcesador(alquiler.getMetodoPago());
        String detallePago = procesador.cobrar(costo);
        alquiler.getVehiculo().finalizarViaje();
        alquileresActivos.remove(clave);

        return new DesbloqueoResponse(alquiler.getVehiculo().getPatente(), costo, minutos,
                alquiler.getVehiculo().getFaseActual(), detallePago);
    }

    private double calcularCosto(Alquiler alquiler, long minutos) {
        double subtotal = tarifaService.calcular(alquiler.getVehiculo().getTarifaBase(), minutos);
        double total = alquiler.getUsuario().calcularTarifaFinal(subtotal);
        return Math.round(total * 100.0) / 100.0;
    }

    private Usuario buscarUsuario(String id) {
        Usuario usuario = usuariosPorId.get(normalizar(id));
        if (usuario == null) throw new UsuarioNoEncontradoException("Usuario no encontrado con id: " + id);
        return usuario;
    }

    private void validarBateria(Vehiculo vehiculo) {
        if (vehiculo.getPorcentajeBateria() < 15) {
            throw new BateriaInsuficienteException("Batería insuficiente");
        }
    }

    private String normalizar(String texto) {
        return texto == null ? "" : texto.trim().toUpperCase();
    }
}
