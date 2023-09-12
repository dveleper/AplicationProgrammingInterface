package com.neoris.aplicationprogramminginterface.application.service.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;

import java.util.UUID;

public interface MovimientoService {
    Movimiento crear(Movimiento movimiento);
    Movimiento editar(Movimiento movimiento, UUID movimientoId);
    void eliminar(UUID movimientoId);
    Iterable<Movimiento> obtenerMovimientos();
    Movimiento obtenerMovimientoPorId(UUID movimientoId);

}
