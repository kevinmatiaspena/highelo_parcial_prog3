package com.example.Ecoride.service;

import java.util.ArrayList;
import java.util.List;

import com.example.Ecoride.dto.DesbloqueoRequest;
import com.example.Ecoride.dto.DesbloqueoResponse;
import com.example.Ecoride.exception.BateriaInsuficienteException;
import com.example.Ecoride.exception.UsuarioNoEncontradoException;
import com.example.Ecoride.exception.VehiculoNoEncontradoException;
import com.example.Ecoride.model.EstacionAnclaje;
import com.example.Ecoride.model.Usuario;
import com.example.Ecoride.model.Vehiculo;
import com.example.Ecoride.payments.FabricaProcesadorPago;
import com.example.Ecoride.payments.ProcesadorPago;
import org.springframework.stereotype.Service;

@Service
public class AlquilerService {

    private final List<EstacionAnclaje> estaciones;
    private final List<Usuario> usuarios;
    private final FabricaProcesadorPago fabricaProcesadorPago;

    public AlquilerService(FabricaProcesadorPago fabricaProcesadorPago) {
        this.estaciones = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.fabricaProcesadorPago = fabricaProcesadorPago;
    }

    public List<EstacionAnclaje> getEstaciones() {
        return estaciones;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void agregarEstacion(EstacionAnclaje estacion) {
        estaciones.add(estacion);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public DesbloqueoResponse desbloquear(DesbloqueoRequest request) {
        Usuario usuario = buscarUsuarioPorId(request.getIdUsuario());
        Vehiculo vehiculo = buscarVehiculoEnEstaciones(request.getPatente());

        validarBateria(vehiculo);

        double montoFinal = usuario.calcularTarifaFinal(vehiculo.getTarifaBase());

        ProcesadorPago procesadorPago = fabricaProcesadorPago.crearProcesador(request.getMetodoPago());
        String detallePago = procesadorPago.cobrar(montoFinal);

        return new DesbloqueoResponse(
                "Desbloqueo realizado correctamente",
                vehiculo.getPatente(),
                vehiculo.getClass().getSimpleName(),
                montoFinal,
                detallePago
        );
    }

    private Usuario buscarUsuarioPorId(String idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(idUsuario)) {
                return usuario;
            }
        }
        throw new UsuarioNoEncontradoException("Usuario no encontrado con id: " + idUsuario);
    }

    private Vehiculo buscarVehiculoEnEstaciones(String patente) {
        for (EstacionAnclaje estacion : estaciones) {
            Vehiculo vehiculo = estacion.buscarVehiculoPorPatente(patente);
            if (vehiculo != null) {
                return vehiculo;
            }
        }
        throw new VehiculoNoEncontradoException("Vehículo no encontrado");
    }

    private void validarBateria(Vehiculo vehiculo) {
        if (vehiculo.getPorcentajeBateria() < 15) {
            throw new BateriaInsuficienteException("Batería insuficiente");
        }
    }
}