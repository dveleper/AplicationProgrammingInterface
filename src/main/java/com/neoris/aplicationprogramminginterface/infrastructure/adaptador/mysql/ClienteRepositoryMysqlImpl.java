package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.port.ClienteRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.ClienteMapper;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.ClienteEntity;
import org.springframework.context.annotation.Lazy;

import java.util.*;

public class ClienteRepositoryMysqlImpl implements ClienteRepository {

    private final ClienteRepositoryMysql repositoryMysql;

    private final ClienteMapper clienteMapper;

    public ClienteRepositoryMysqlImpl(@Lazy ClienteRepositoryMysql repositoryMysql, ClienteMapper clienteMapper) {
        this.repositoryMysql = repositoryMysql;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente crear(Cliente cliente) {
        ClienteEntity saved = repositoryMysql.save(clienteMapper.toClienteEntity(cliente));
        return clienteMapper.toCliente(saved);
    }

    @Override
    public Cliente editar(Cliente cliente) {
        ClienteEntity saved = repositoryMysql.save(clienteMapper.toClienteEntity(cliente));
        return clienteMapper.toCliente(saved);
    }

    @Override
    public void eliminar(UUID clienteId) {
         repositoryMysql.findById(clienteId)
                .map(entity -> {
                    repositoryMysql.delete(entity);
                    return true;
                })
                 .orElseThrow(RuntimeException::new); //TODO: IMPLEMENTAR EXCEPCION PERSONALIZADA
    }

    @Override
    public List<Cliente> obtenerClientes() {
        List<ClienteEntity> clienteEntities = (List<ClienteEntity>) repositoryMysql.findAll();
        if (clienteEntities.isEmpty()) throw new RuntimeException();
        return clienteMapper.toClientes(clienteEntities);
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(UUID clienteId) {
        return Optional.of(repositoryMysql.findById(clienteId)
                .map(clienteMapper::toCliente)
                .orElseThrow()); //TODO: IMPLEMENTAR EXCEPCION PERSONALIZADA
    }
}
