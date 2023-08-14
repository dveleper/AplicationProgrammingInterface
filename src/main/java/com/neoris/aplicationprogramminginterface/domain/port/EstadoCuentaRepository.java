package com.neoris.aplicationprogramminginterface.domain.port;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EstadoCuentaRepository {
    Iterable<EstadoCuenta> getEstadoCuentaPorFechas(String identificacion,
                                                LocalDate fechaInicial,
                                                LocalDate fechaFinal);
}
