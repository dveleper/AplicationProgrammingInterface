package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.domain.port.MovimientoRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.BusinessException;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.ResourceNotFoundException;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.MovimientoMapper;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;

public class MovimientoRepositoryMysqlImpl implements MovimientoRepository {

    private final MovimientoRepositoryMysql movimientoRepositoryMysql;
    private final MovimientoMapper movimientoMapper;

    public MovimientoRepositoryMysqlImpl(@Lazy MovimientoRepositoryMysql movimientoRepositoryMysql,
                                         MovimientoMapper movimientoMapper) {
        this.movimientoRepositoryMysql = movimientoRepositoryMysql;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    public Movimiento crear(Movimiento movimiento) {
        return movimientoMapper.toMovimiento(
                movimientoRepositoryMysql.save(
                        movimientoMapper.toMovimientoEntity(movimiento)));
    }

    @Override
    public Movimiento editar(Movimiento movimiento, UUID movimientoId) {
        return movimientoMapper.toMovimiento(
                movimientoRepositoryMysql.save(
                        movimientoMapper.toMovimientoEntity(movimiento)));
    }

    @Override
    public void eliminar(UUID movimientoId) {
        movimientoRepositoryMysql.findById(movimientoId)
                .map(movimientoEntity -> {
                    movimientoRepositoryMysql.delete(movimientoEntity);
                            return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
    }

    @Override
    public Iterable<Movimiento> obtenerMovimientos() {
        return movimientoMapper.toMovimientos(movimientoRepositoryMysql.findAll());
    }

    @Override
    public Movimiento obtenerMovimientoPorId(UUID movimientoId) {
        return movimientoMapper.toMovimiento(
                movimientoRepositoryMysql.findById(movimientoId)
                        .orElseThrow(() -> new BusinessException("Movimiento no encontrado")));
    }
}
