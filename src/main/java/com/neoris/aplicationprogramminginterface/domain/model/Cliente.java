package com.neoris.aplicationprogramminginterface.domain.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {
    private UUID clienteId;
    private String contrasena;
    private String estado;
}
