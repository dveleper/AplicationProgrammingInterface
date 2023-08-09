package com.neoris.aplicationprogramminginterface.application.service.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.domain.port.MovimientoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository repository;

    public MovimientoServiceImpl(MovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movimiento crear(Movimiento movimiento) {
        if (movimiento.getMovimientoId() == null) {
            movimiento.setFecha(LocalDate.now());
        }
        return repository.crear(movimiento);
    }

    @Override
    public Movimiento editar(Movimiento movimiento) {
        return repository.editar(movimiento);
    }

    @Override
    public void eliminar(UUID movimientoId) {
        repository.eliminar(movimientoId);
    }

    @Override
    public List<Movimiento> obtenerMovimientos() {
        return repository.obtenerMovimientos();
    }
}
