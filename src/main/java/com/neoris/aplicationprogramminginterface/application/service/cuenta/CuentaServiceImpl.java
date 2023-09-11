package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.domain.port.CuentaRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
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
    public boolean eliminar(UUID cuentaId) {
        log.info("eliminando cuenta... {}", cuentaId);
        return repository.eliminar(cuentaId);
    }

    @Override
    public Iterable<Cuenta> obtenerCuentas() {
        log.info("listando cuentas...");
        return repository.obtenerCuentas();
    }

    @Override
    public Cuenta obtenerCuentaPorId(UUID cuentaId) {
        log.info("buscando cuenta... {}", cuentaId);
        return repository.obtenerCuentaPorId(cuentaId)
                .orElseThrow(() -> new BusinessException("Cuenta no existe"));
    }

    @Override
    public Cuenta obtenerCuentaPorNumero(String numero) {
        return repository.obtenerCuentaPorNumero(numero)
                .orElseThrow(() -> new BusinessException("Cuenta no existe"));
    }

    @Override
    public BigInteger realizarDebito(BigInteger saldoActual, BigInteger monto) {
        validarMonto(Movimiento.TipoMovimiento.DEBITO, saldoActual, monto);
        return saldoActual.add(monto);
    }

    private void validarMonto(Movimiento.TipoMovimiento tipoMovimiento, BigInteger saldoActual, BigInteger monto) {
        switch (tipoMovimiento) {
            case DEBITO -> {
                if(monto.intValue() > 0) {
                    throw new BusinessException("El monto para DEBITO debe ser negativo");
                }
                if (saldoActual.add(monto).compareTo(BigInteger.ZERO) < 0 ) {
                    throw new BusinessException("Saldo no disponible");
                }
            }
            case CREDITO -> {
                if(monto.intValue() < 0) {
                    throw new BusinessException("El monto para CREDITO debe ser positivo");
                }
            }
        }
    }

    @Override
    public BigInteger realizarCredito(BigInteger saldoActual, BigInteger monto) {
        validarMonto(Movimiento.TipoMovimiento.CREDITO, saldoActual, monto);
        return saldoActual.add(monto);
    }

    @Override
    public void actualizarSaldo(String numeroCuenta, BigInteger nuevoSaldo) {
        repository.obtenerCuentaPorNumero(numeroCuenta).map(cuenta -> {
            cuenta.setSaldoInicial(nuevoSaldo);
            repository.editar(cuenta, cuenta.getCuentaId());
            return true;
        });
    }

}
