package com.neoris.aplicationprogramminginterface.application.service.estado_cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;

import java.text.ParseException;
import java.util.List;

public interface EstadoCuentaService {
    List<EstadoCuenta> getEstadoCuentaPorFechas(String identificacion,
                                                String fechaInicial,
                                                String fechaFinal) throws ParseException;
}
