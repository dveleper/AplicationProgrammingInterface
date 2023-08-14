package com.neoris.aplicationprogramminginterface.application.service.estado_cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;

import java.util.Date;
import java.util.List;

public interface EstadoCuentaService {
    List<EstadoCuenta> getEstadoCuentaPorFechas(String identificacion,
                                                Date fechaInicial,
                                                Date fechaFinal);
}
