package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "cuenta")
@Inheritance
@Getter
@Setter
public class CuentaEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cuenta_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID cuentaId;
    private String numero;
    private String tipo;
    private BigInteger saldoInicial;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}
