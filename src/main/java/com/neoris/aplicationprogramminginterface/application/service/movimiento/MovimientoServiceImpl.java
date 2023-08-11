package com.neoris.aplicationprogramminginterface.application.service.movimiento;

import com.neoris.aplicationprogramminginterface.application.service.cuenta.CuentaService;
import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.domain.port.MovimientoRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository repository;
    private final CuentaService cuentaService;

    public MovimientoServiceImpl(MovimientoRepository repository, CuentaService cuentaService) {
        this.repository = repository;
        this.cuentaService = cuentaService;
    }

    @Override
    public Movimiento crear(Movimiento movimiento) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorNumero(movimiento.getCuenta().getNumero());
        movimiento.setCuenta(cuenta);

        afectarSaldo(movimiento, cuenta);

        movimiento.setMovimientoId(UUID.randomUUID());
        movimiento.setFecha(LocalDate.now());

        log.info("saving movimiento... {}", movimiento.getMovimientoId());

        return repository.crear(movimiento);
    }



    private void afectarSaldo(Movimiento movimiento, Cuenta cuenta) {
        log.info("afectando nuevo saldo en la cuenta");
        movimiento.setSaldo(cuenta.getSaldoInicial());
        BigInteger nuevoSaldo;
        switch (movimiento.getTipo()) {
            case DEBITO -> nuevoSaldo = cuentaService.realizarDebito(cuenta.getSaldoInicial(), movimiento.getValor());
            case CREDITO -> nuevoSaldo = cuentaService.realizarCredito(cuenta.getSaldoInicial(), movimiento.getValor());
            default -> throw new RuntimeException(String.format("tipo de movimiento: %s no disponible", movimiento.getTipo()));
        }
        cuentaService.actualizarSaldo(cuenta.getNumero(), nuevoSaldo);
        cuenta.setSaldoInicial(nuevoSaldo);
        movimiento.setCuenta(cuenta);
    }

    @Override
    public Movimiento editar(Movimiento movimiento, UUID movimientoId) {
        log.info("actualizando movimiento... {}", movimiento.getMovimientoId());
        return repository.editar(movimiento, movimientoId);
    }

    @Override
    public void eliminar(UUID movimientoId) {
        log.info("eliminando movimiento... {}", movimientoId);
        repository.eliminar(movimientoId);
    }

    @Override
    public Iterable<Movimiento> obtenerMovimientos() {
        log.info("listando movimientos...");
        return repository.obtenerMovimientos();
    }

    @Override
    public Movimiento obtenerMovimientoPorId(UUID movimientoId) {
        log.info("buscando movimiento por id... {}", movimientoId);
        return repository.obtenerMovimientoPorId(movimientoId);
    }
}
