package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.movimiento;

import com.neoris.aplicationprogramminginterface.infrastructure.entity.MovimientoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MovimientoRepositoryMysql extends CrudRepository<MovimientoEntity, UUID> {
}
