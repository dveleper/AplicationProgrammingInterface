package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Movimiento {
    private UUID movimientoId;
    private LocalDate fecha;
    private TipoMovimiento tipo;
    private BigInteger valor;
    private BigInteger saldo;

    public enum TipoMovimiento {
        CREDITO,
        DEBITO;
    }
}
