package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.cliente;

import com.neoris.aplicationprogramminginterface.infrastructure.entity.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepositoryMysql extends CrudRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findClienteEntityByIdentificacion(String identification);
}
