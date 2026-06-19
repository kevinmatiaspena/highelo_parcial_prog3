# EcoRide Pro — Parcial II de Programación III

Evolución de EcoRide construida con Java 21 y Spring Boot 3. El proyecto aplica patrones de comportamiento, estructuras de datos eficientes, DTOs y ordenamientos naturales/externos. Toda la información vive en memoria y el procesamiento es secuencial, sin lambdas, Stream API ni concurrencia.

## Diseño aplicado

- **State:** `EnEspera`, `EnViaje` y `EnReparacion` encapsulan las transiciones permitidas. El vehículo delega en su estado y no contiene un bloque condicional creciente.
- **Strategy:** `TarifaEstandar`, `TarifaHoraPico` y `TarifaTemporalClimatico` implementan `CriterioTarifa`. El criterio activo puede cambiar durante la ejecución.
- **Strategy de pagos + Factory:** tarjeta y billetera implementan `ProcesadorPago`; la fábrica selecciona la implementación.
- **DTOs:** los controladores nunca devuelven las entidades internas `Vehiculo`, `Usuario` o `Alquiler`.
- **Manejo global de errores:** `ApiExceptionHandler` produce respuestas homogéneas y códigos HTTP apropiados.

## Ejecución

```bash
mvn spring-boot:run
```

Para ejecutar las pruebas:

```bash
mvn test
```

## API

### Iniciar y finalizar un viaje

```http
POST /api/alquileres/desbloquear
Content-Type: application/json

{
  "idUsuario": "U002",
  "patente": "BICI-VENZO",
  "metodoPago": "TARJETA"
}
```

```http
POST /api/alquileres/finalizar
Content-Type: application/json

{
  "patente": "BICI-VENZO"
}
```

La respuesta contiene únicamente patente, costo calculado, minutos transcurridos, fase actual y detalle del pago. Cada minuto iniciado se factura como un minuto completo.

### Cambiar estrategia de tarifa

```http
GET /api/tarifas/criterio
PUT /api/tarifas/criterio/ESTANDAR
PUT /api/tarifas/criterio/HORA_PICO
PUT /api/tarifas/criterio/TEMPORAL_CLIMATICO
```

- Estándar: `minutos × tarifa base`.
- Hora pico: subtotal estándar más 40 %.
- Temporal climático: subtotal estándar más un recargo fijo de $150.
- El descuento del usuario premium se aplica al resultado del criterio activo.

### Reportes y mantenimiento

```http
GET  /api/vehiculos/prioridad-carga
GET  /api/vehiculos/tarifa-descendente
POST /api/vehiculos/alertas-gps/deduplicar
POST /api/vehiculos/{patente}/reparacion
POST /api/vehiculos/{patente}/reparacion/finalizar
```

`prioridad-carga` usa el orden natural de `Vehiculo`: batería ascendente y patente como desempate. `tarifa-descendente` usa el comparador externo `VehiculoPorTarifaDescendente` y no altera el criterio natural.

## Anexo técnico de rendimiento

### Búsqueda instantánea

La versión inicial recorría estaciones y vehículos linealmente, con costo O(n). `VehiculoService` mantiene un índice `Map<String, Vehiculo>` normalizado por patente. Una búsqueda usa `get`, cuyo costo promedio es O(1), independientemente de que haya diez o cien mil vehículos. El índice ocupa memoria O(n), intercambio razonable para acelerar la operación más frecuente.

Los usuarios y alquileres activos también se indexan por identificador o patente, evitando búsquedas secuenciales.

### Deduplicación de alertas GPS

El algoritmo recorre la entrada una sola vez e intenta insertar cada coordenada en un `HashSet`. `equals` y `hashCode` de `CoordenadaGps` determinan duplicados. El tiempo promedio es O(n) y la memoria adicional O(n).

Una solución con bucles anidados compararía cada elemento con todos los demás y tendría costo O(n²). Con miles de alertas, esa diferencia evita bloquear innecesariamente la CPU.

### Dos órdenes independientes

`Vehiculo` implementa `Comparable<Vehiculo>` exclusivamente para la prioridad operativa por batería. El criterio comercial está en un `Comparator<Vehiculo>` externo. Cada reporte crea una copia de los valores del mapa y ordena esa copia, por lo que ambos órdenes pueden calcularse en memoria sin mutar el índice ni interferirse. Cada ordenamiento cuesta O(n log n) y su copia O(n).
