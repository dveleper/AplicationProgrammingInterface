package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.port.ClienteRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mapper.ClienteMapper;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClienteRepositoryMysqlImpl implements ClienteRepository {

    private final ClienteRepositoryMysql repositoryMysql;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteRepositoryMysqlImpl(ClienteRepositoryMysql repositoryMysql) {
        this.repositoryMysql = repositoryMysql;
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
                .map(entity -> clienteMapper.toCliente(entity))
                .orElseThrow()); //TODO: IMPLEMENTAR EXCEPCION PERSONALIZADA
    }
}
