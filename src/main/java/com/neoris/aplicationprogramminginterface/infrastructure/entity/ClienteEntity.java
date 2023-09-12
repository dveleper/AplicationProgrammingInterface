package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class ClienteEntity extends PersonaEntity {
    @Column(name = "cliente_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID clienteId;
    private String contrasena;
    private String estado;
}
