package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.cliente;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.port.ClienteRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.ResourceNotFoundException;
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
        return clienteMapper.toCliente(
                repositoryMysql.save(
                        clienteMapper.toClienteEntity(cliente))
        );
    }

    @Override
    public Cliente editar(Cliente cliente, UUID clienteId) {
        return repositoryMysql.findById(clienteId)
                .map(client_ ->
                    clienteMapper.toCliente(
                            repositoryMysql.save(
                                    clienteMapper.toClienteEntity(cliente))))
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado"));
    }

    @Override
    public void eliminar(UUID clienteId) {
         repositoryMysql.findById(clienteId)
                .map(entity -> {
                    repositoryMysql.delete(entity);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado"));
    }

    @Override
    public Iterable<Cliente> obtenerClientes() {
        return clienteMapper.toClientes(repositoryMysql.findAll());
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(UUID clienteId) {
        return Optional.of(repositoryMysql.findById(clienteId)
                .map(clienteMapper::toCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado")));
    }
}
