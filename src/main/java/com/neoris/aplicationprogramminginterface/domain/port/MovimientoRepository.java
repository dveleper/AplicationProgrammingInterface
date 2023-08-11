package com.neoris.aplicationprogramminginterface.domain.port;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;

import java.util.List;
import java.util.UUID;

public interface MovimientoRepository {
    Movimiento crear(Movimiento movimiento);
    Movimiento editar(Movimiento movimiento, UUID movimientoId);
    void eliminar(UUID movimientoId);
    Iterable<Movimiento> obtenerMovimientos();
}
