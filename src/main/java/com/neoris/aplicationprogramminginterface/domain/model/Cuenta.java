package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class Cuenta {
    private UUID cuentaId;
    private String numero;
    private String tipo;
    private BigInteger saldoInicial;
    private String estado;
}
