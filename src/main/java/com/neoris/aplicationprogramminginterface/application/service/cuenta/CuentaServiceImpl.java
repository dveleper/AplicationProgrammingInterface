package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.port.CuentaRepository;

import java.util.UUID;

public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;

    public CuentaServiceImpl(CuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
        return repository.crear(cuenta);
    }

    @Override
    public Cuenta editar(Cuenta cuenta, UUID cuentaId) {
        return repository.editar(cuenta, cuentaId);
    }

    @Override
    public void eliminar(UUID cuentaId) {
        repository.eliminar(cuentaId);
    }

    @Override
    public Iterable<Cuenta> obtenerCuentas() {
        return repository.obtenerCuentas();
    }

    @Override
    public Cuenta obtenerCuentaPorId(UUID cuentaId) {
        return repository.obtenerCuentaPorId(cuentaId).orElse(new Cuenta());
    }
}
