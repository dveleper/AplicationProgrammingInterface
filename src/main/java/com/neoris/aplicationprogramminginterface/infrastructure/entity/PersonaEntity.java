package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PersonaEntity {
    private String identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private String telefono;
}
