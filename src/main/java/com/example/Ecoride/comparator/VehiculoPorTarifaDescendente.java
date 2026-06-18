package com.example.Ecoride.comparator;

import com.example.Ecoride.model.Vehiculo;
import java.util.Comparator;

public class VehiculoPorTarifaDescendente implements Comparator<Vehiculo> {
    @Override
    public int compare(Vehiculo primero, Vehiculo segundo) {
        int comparacion = Double.compare(segundo.getTarifaBase(), primero.getTarifaBase());
        if (comparacion != 0) return comparacion;
        return primero.getPatente().compareToIgnoreCase(segundo.getPatente());
    }
}
