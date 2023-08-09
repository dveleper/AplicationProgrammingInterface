package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class ClienteEntity extends PersonaEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cliente_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID clienteId;
    private String contrasena;
    private String estado;
}
