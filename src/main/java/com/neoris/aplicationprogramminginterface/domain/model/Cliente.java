package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {
    private UUID clienteId;
    private String contrasena;
    private String estado;
}
