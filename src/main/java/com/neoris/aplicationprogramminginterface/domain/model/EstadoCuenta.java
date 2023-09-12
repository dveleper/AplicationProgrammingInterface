package com.neoris.aplicationprogramminginterface.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Value
@AllArgsConstructor
public class EstadoCuenta {
    @JsonFormat(pattern="dd/MM/yyyy")
    @JsonProperty("Fecha")
    private Date fecha;
    @JsonProperty("Cliente")
    private String cliente;
    @JsonProperty("Numero Cuenta")
    private String numeroCuenta;
    @JsonProperty("Tipo")
    private String tipo;
    @JsonProperty("Saldo Inicial")
    private Long saldoInicial;
    @JsonProperty("Estado")
    private String estado;
    @JsonProperty("Movimiento")
    private Long movimiento;
    @JsonProperty("Saldo Disponible")
    private Long saldoDisponible;

}
