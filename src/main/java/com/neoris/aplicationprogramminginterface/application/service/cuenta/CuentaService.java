package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;

import java.util.UUID;

public interface CuentaService {
    Cuenta crear(Cuenta cuenta);
    Cuenta editar(Cuenta cuenta, UUID cuentaId);
    void eliminar(UUID cuentaId);
    Iterable<Cuenta> obtenerCuentas();
    Cuenta obtenerCuentaPorId(UUID cuentaId);
}
