package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;

import java.math.BigInteger;
import java.util.UUID;

public interface CuentaService {
    Cuenta crear(Cuenta cuenta);
    Cuenta editar(Cuenta cuenta, UUID cuentaId);
    void eliminar(UUID cuentaId);
    Iterable<Cuenta> obtenerCuentas();
    Cuenta obtenerCuentaPorId(UUID cuentaId);
    Cuenta obtenerCuentaPorNumero(String numero);
    BigInteger realizarDebito(BigInteger saldoActual, BigInteger monto);
    BigInteger realizarCredito(BigInteger saldoActual, BigInteger monto);
    void actualizarSaldo(String numeroCuenta, BigInteger nuevoSaldo);
}
