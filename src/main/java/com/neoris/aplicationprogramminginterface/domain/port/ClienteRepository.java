package com.neoris.aplicationprogramminginterface.domain.port;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    Cliente crear(Cliente cliente);
    Cliente editar(Cliente cliente, UUID clienteId);
    void eliminar(UUID clienteId);
    Iterable<Cliente> obtenerClientes();
    Optional<Cliente> obtenerClientePorId(UUID clienteId);
}
