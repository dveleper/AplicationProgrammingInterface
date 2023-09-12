package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;
import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.domain.port.MovimientoRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.BusinessException;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.ResourceNotFoundException;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.CuentaMapper;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.MovimientoMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MovimientoRepositoryMysqlImpl implements MovimientoRepository {

    private final MovimientoRepositoryMysql movimientoRepositoryMysql;
    private final MovimientoMapper movimientoMapper;
    private final CuentaMapper cuentaMapper;

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<EstadoCuenta> obtenerEstadoCuentaNative(String identificacion) {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT m.fecha, per.nombre, cu.numero, cu.tipo, m.saldo, cu.estado, m.valor, cu.saldo_inicial " +
                        "FROM accounts.movimiento m " +
                        "INNER JOIN accounts.cuenta cu ON cu.cuenta_id = m.cuenta_id " +
                        "INNER JOIN accounts.cliente cli ON cli.identificacion = cu.cliente_id " +
                        "INNER JOIN accounts.persona per ON per.identificacion = cli.identificacion " +
                        "WHERE per.identificacion = ?1 "/* +
                        "AND fecha between ?2 AND ?3"*/,
                Tuple.class);
        nativeQuery.setParameter(1, identificacion);
        List<Tuple> tuples = nativeQuery.getResultList();

        return tuples.stream()
                .map(this::getEstadoCuentaByTuple)
                .toList();
    }

    private EstadoCuenta getEstadoCuentaByTuple(Tuple tuple) {
        return new EstadoCuenta(
                tuple.get(0, Date.class),
                tuple.get(1, String.class),
                tuple.get(2, String.class),
                tuple.get(3, String.class),
                tuple.get(4, BigDecimal.class).toBigInteger().longValueExact(),
                tuple.get(5, String.class),
                tuple.get(6, BigDecimal.class).toBigInteger().longValueExact(),
                tuple.get(7, BigDecimal.class).toBigInteger().longValueExact()
        );
    }


}
