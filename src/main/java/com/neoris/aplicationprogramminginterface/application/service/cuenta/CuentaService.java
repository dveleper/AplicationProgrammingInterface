package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;

import java.util.List;
import java.util.UUID;

public interface CuentaService {
    Cuenta crear(Cuenta cuenta);
    Cuenta editar(Cuenta cuenta);
    void eliminar(UUID cuentaId);
    List<Cuenta> obtenerCuentas();
    Cuenta obtenerCuentaPorId(UUID cuentaId);
}
