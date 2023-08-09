package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Persona {
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
