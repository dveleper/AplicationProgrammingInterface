package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Movimiento {
    private UUID movimientoId = UUID.randomUUID();
    private LocalDate fecha = LocalDate.now();
    private TipoMovimiento tipo;
    private BigInteger valor;
    private BigInteger saldo;
    private Cuenta cuenta;

    public enum TipoMovimiento {
        CREDITO,
        DEBITO;
    }
}
