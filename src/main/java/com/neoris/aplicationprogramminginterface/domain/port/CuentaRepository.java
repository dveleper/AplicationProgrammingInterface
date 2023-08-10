package com.neoris.aplicationprogramminginterface.domain.port;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuentaRepository {
    Cuenta crear(Cuenta cuenta);
    Cuenta editar(Cuenta cuenta, UUID cuentaId);
    void eliminar(UUID cuentaId);
    Iterable<Cuenta> obtenerCuentas();
    Optional<Cuenta> obtenerCuentaPorId(UUID cuentaId);
}
