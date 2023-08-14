package com.neoris.aplicationprogramminginterface.application.service.estado_cuenta;

import com.neoris.aplicationprogramminginterface.application.service.movimiento.MovimientoService;
import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EstadoCuentaServiceImpl implements EstadoCuentaService {

    private final MovimientoService movimientoService;

    public EstadoCuentaServiceImpl(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @Override
    public List<EstadoCuenta> getEstadoCuentaPorFechas(String identificacion,
                                                       Date fechaInicial,
                                                       Date fechaFinal) {
        return movimientoService.getEstadoCuentaPorFechas(identificacion,
                convertToLocalDate(fechaInicial),
                convertToLocalDate(fechaFinal));
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
