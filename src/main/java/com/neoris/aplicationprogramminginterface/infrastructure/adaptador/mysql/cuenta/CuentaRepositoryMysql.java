package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.cuenta;

import com.neoris.aplicationprogramminginterface.infrastructure.entity.CuentaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CuentaRepositoryMysql extends CrudRepository<CuentaEntity, UUID> {
}
