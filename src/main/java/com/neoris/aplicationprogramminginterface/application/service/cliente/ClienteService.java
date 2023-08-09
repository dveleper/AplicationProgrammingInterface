package com.neoris.aplicationprogramminginterface.application.service.cliente;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    Cliente crear(Cliente cliente);
    Cliente editar(Cliente cliente);
    void eliminar(UUID clienteId);
    List<Cliente> obtenerClientes();
    Cliente obtenerClientePorId(UUID clienteId);
}
