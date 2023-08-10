package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.port.CuentaRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;

    public CuentaServiceImpl(CuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
        log.info("saving cuenta... {}", cuenta.getCuentaId());
        return repository.crear(cuenta);
    }

    @Override
    public Cuenta editar(Cuenta cuenta, UUID cuentaId) {
        log.info("actualizando cuenta... {}", cuentaId);
        return repository.editar(cuenta, cuentaId);
    }

    @Override
    public void eliminar(UUID cuentaId) {
        log.info("eliminando cuenta... {}", cuentaId);
        repository.eliminar(cuentaId);
    }

    @Override
    public Iterable<Cuenta> obtenerCuentas() {
        log.info("listando cuentas...");
        return repository.obtenerCuentas();
    }

    @Override
    public Cuenta obtenerCuentaPorId(UUID cuentaId) {
        log.info("buscando cuenta... {}", cuentaId);
        return repository.obtenerCuentaPorId(cuentaId).orElse(new Cuenta());
    }
}
