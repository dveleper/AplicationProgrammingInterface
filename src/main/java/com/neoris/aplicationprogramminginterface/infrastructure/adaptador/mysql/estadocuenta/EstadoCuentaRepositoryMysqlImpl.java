package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.estadocuenta;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;
import com.neoris.aplicationprogramminginterface.domain.port.EstadoCuentaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class EstadoCuentaRepositoryMysqlImpl implements EstadoCuentaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EstadoCuenta> obtenerEstadoCuentaNative(String identificacion, LocalDate fechaInicial, LocalDate fechaFinal) {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT m.fecha, per.nombre, cu.numero, cu.tipo, m.saldo, cu.estado, m.valor, cu.saldo_inicial " +
                        "FROM accounts.movimiento m " +
                        "INNER JOIN accounts.cuenta cu ON cu.cuenta_id = m.cuenta_id " +
                        "INNER JOIN accounts.cliente cli ON cli.identificacion = cu.cliente_id " +
                        "INNER JOIN accounts.persona per ON per.identificacion = cli.identificacion " +
                        "WHERE per.identificacion = ?1 " +
                        "AND fecha between ?2 AND ?3",
                Tuple.class);

        nativeQuery.setParameter(1, identificacion);
        nativeQuery.setParameter(2, fechaInicial);
        nativeQuery.setParameter(3, fechaFinal);

        List<Tuple> tuples = nativeQuery.getResultList();

        log.info("Estado de cuenta con {} registros", tuples.size());

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
