package com.example.Ecoride.config;

import com.example.Ecoride.model.*;
import com.example.Ecoride.service.AlquilerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AlquilerService alquilerService;

    public DataLoader(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @Override
    public void run(String... args) {
        UsuarioRegular usuario1 = new UsuarioRegular("U001", "Kevin Peña");
        UsuarioPremium usuario2 = new UsuarioPremium("U002", "Elias Luna", 10.0);

        alquilerService.agregarUsuario(usuario1);
        alquilerService.agregarUsuario(usuario2);

        EstacionAnclaje estacionCentro = new EstacionAnclaje("Estacion Centro");
        EstacionAnclaje estacionParque = new EstacionAnclaje("Estacion Parque");

        estacionCentro.agregarVehiculo(new Monopatin("MONO-1", 80, 500.0, true));
        estacionCentro.agregarVehiculo(new Monopatin("MONO-2", 10, 450.0, false));
        estacionParque.agregarVehiculo(new BicicletaElectrica("BICI-VENZO", 70, 650.0, 1200.0));
        estacionParque.agregarVehiculo(new BicicletaElectrica("BICI-SLP", 14, 700.0, 1500.0));

        alquilerService.agregarEstacion(estacionCentro);
        alquilerService.agregarEstacion(estacionParque);
    }
}