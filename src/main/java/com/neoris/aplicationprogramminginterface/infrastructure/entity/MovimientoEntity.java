package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

//@Entity
//@Table(name = "movimiento")
//@Data
public class MovimientoEntity {
//    @Id
    private UUID movimientoId;
    private LocalDate fecha;
    private String tipo;
    private BigInteger valor;
    private BigInteger saldo;
}
