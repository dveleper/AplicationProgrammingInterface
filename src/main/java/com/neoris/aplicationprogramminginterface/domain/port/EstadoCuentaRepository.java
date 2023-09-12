package com.neoris.aplicationprogramminginterface.domain.port;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;

import java.time.LocalDate;
import java.util.List;

public interface EstadoCuentaRepository {
    List<EstadoCuenta> obtenerEstadoCuentaNative(String identificacion, LocalDate fechaInicial, LocalDate fechaFinal);
}
