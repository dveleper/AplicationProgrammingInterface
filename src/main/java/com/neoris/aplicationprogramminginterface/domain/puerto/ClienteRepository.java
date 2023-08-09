package com.neoris.aplicationprogramminginterface.domain.puerto;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {
    Cliente crear(Cliente cliente);
    Cliente editar(Cliente cliente);
    void eliminar(UUID clienteId);
    List<Cliente> obtenerClientes();
}
