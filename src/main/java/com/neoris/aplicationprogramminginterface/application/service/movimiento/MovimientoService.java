package com.neoris.aplicationprogramminginterface.application.service.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;
import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovimientoService {
    Movimiento crear(Movimiento movimiento);
    Movimiento editar(Movimiento movimiento, UUID movimientoId);
    void eliminar(UUID movimientoId);
    Iterable<Movimiento> obtenerMovimientos();
    Movimiento obtenerMovimientoPorId(UUID movimientoId);
    List<EstadoCuenta> getEstadoCuentaPorFechas(String identificacion,
                                                LocalDate fechaInicial,
                                                LocalDate fechaFinal);

}
