package com.neoris.aplicationprogramminginterface.infrastructure.entity;

import lombok.Data;
@Data
public abstract class PersonaEntity {
    private String identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private String telefono;
}
