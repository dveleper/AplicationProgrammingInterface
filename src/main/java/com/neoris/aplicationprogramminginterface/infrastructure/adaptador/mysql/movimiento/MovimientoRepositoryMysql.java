package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.movimiento;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MovimientoRepositoryMysql extends CrudRepository<MovimientoEntity, UUID> {
    @Query(value = "SELECT m FROM MovimientoEntity m " +
                   "WHERE m.cuenta.cliente.identificacion = :identificacion " +
                   "AND m.fecha between :fechaInicial AND :fechaFinal")
    Iterable<MovimientoEntity> getMovimientosByClientAndDates(String identificacion,
                                                          LocalDate fechaInicial,
                                                          LocalDate fechaFinal);

}
