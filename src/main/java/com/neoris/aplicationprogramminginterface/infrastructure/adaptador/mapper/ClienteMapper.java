package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mapper;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.ClienteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toCliente(ClienteEntity clienteEntity);
    List<Cliente> toClientes(List<ClienteEntity> clienteList);
    @InheritInverseConfiguration
    ClienteEntity toClienteEntity(Cliente cliente);
}
