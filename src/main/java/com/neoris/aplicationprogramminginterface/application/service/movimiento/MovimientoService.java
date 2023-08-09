package com.neoris.aplicationprogramminginterface.application.service.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;

import java.util.List;
import java.util.UUID;

public interface MovimientoService {
    Movimiento crear(Movimiento movimiento);
    Movimiento editar(Movimiento movimiento);
    void eliminar(UUID movimientoId);
    List<Movimiento> obtenerMovimientos();
}
