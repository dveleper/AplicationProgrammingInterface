package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteEntity extends PersonaEntity {
    @Id
    private UUID clienteId;
    private String contrasena;
    private String estado;
}
