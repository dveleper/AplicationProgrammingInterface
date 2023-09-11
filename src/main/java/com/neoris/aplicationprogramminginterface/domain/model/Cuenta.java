package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private UUID cuentaId;
    private String numero;
    private String tipo;
    private BigInteger saldoInicial;
    private String estado;
    private Cliente cliente;
}
