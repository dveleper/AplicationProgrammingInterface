package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.domain.port.MovimientoRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.BusinessException;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.ResourceNotFoundException;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.CuentaMapper;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.MovimientoMapper;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MovimientoRepositoryMysqlImpl implements MovimientoRepository {

    private final MovimientoRepositoryMysql movimientoRepositoryMysql;
    private final MovimientoMapper movimientoMapper;
    private final CuentaMapper cuentaMapper;

    public MovimientoRepositoryMysqlImpl(@Lazy MovimientoRepositoryMysql movimientoRepositoryMysql,
                                         MovimientoMapper movimientoMapper, CuentaMapper cuentaMapper) {
        this.movimientoRepositoryMysql = movimientoRepositoryMysql;
        this.movimientoMapper = movimientoMapper;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    public Movimiento crear(Movimiento movimiento) {
        return movimientoMapper.toMovimiento(
                movimientoRepositoryMysql.save(
                        movimientoMapper.toMovimientoEntity(movimiento)));
    }

    @Override
    public Movimiento editar(Movimiento movimiento, UUID movimientoId) {
        return movimientoRepositoryMysql.findById(movimientoId)
                .map(movimientoEntity -> {
                    movimiento.setCuenta(cuentaMapper.toCuenta(movimientoEntity.getCuenta()));
                    movimiento.setMovimientoId(movimientoId);
                    movimientoRepositoryMysql.save(
                        movimientoMapper.toMovimientoEntity(movimiento));
            return movimiento;
        }).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
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

    @Override
    public List<Movimiento> obtenerEstadoCuenta(String identificacion, LocalDate fechaInicial, LocalDate fechaFinal) {
        return movimientoMapper.toMovimientos(movimientoRepositoryMysql
                .getMovimientosByClientAndDates(identificacion, fechaInicial, fechaFinal));
    }
}
