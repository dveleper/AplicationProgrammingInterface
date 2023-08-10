package com.neoris.aplicationprogramminginterface.application.service.cliente;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.port.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente crear(Cliente cliente) {
        if (cliente.getClienteId() == null) cliente.setClienteId(UUID.randomUUID());
        log.info("saving cliente... {}", cliente.getClienteId());
        return repository.crear(cliente);
    }

    @Override
    public Cliente editar(Cliente cliente, UUID clienteId) {
        log.info("actualizando cliente... {}", clienteId);
        return repository.editar(cliente, clienteId);
    }

    @Override
    public void eliminar(UUID clienteId) {
        log.info("eliminando cliente... {}", clienteId);
        repository.eliminar(clienteId);
    }

    @Override
    public Iterable<Cliente> obtenerClientes() {
        log.info("listando clientes...");
        return repository.obtenerClientes();
    }

    @Override
    public Cliente obtenerClientePorId(UUID clienteId) {
        log.info("buscando cliente... {}", clienteId);
        return repository.obtenerClientePorId(clienteId).orElse(new Cliente());
    }
}
