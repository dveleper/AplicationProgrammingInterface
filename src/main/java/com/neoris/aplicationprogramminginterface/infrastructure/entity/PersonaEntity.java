package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "persona")
@Inheritance(strategy= InheritanceType.JOINED)
@Data
public class PersonaEntity {
    @Id
    private UUID personaId;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
