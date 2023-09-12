package com.neoris.aplicationprogramminginterface.application.service.estado_cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;
import com.neoris.aplicationprogramminginterface.domain.port.EstadoCuentaRepository;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.List;

import static com.neoris.aplicationprogramminginterface.infrastructure.Utilidades.convertStringToLocalDate;

@Slf4j
public class EstadoCuentaServiceImpl implements EstadoCuentaService {

    private final EstadoCuentaRepository repository;

    public EstadoCuentaServiceImpl(EstadoCuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EstadoCuenta> getEstadoCuentaPorFechas(String identificacion,
                                                       String fechaInicial,
                                                       String fechaFinal) throws ParseException {
        log.info("generando estado de cuenta con identidad... {} entre fecha inicial {} y fecha final {}", identificacion, fechaInicial, fechaFinal);
        return repository.obtenerEstadoCuentaNative(identificacion,
                convertStringToLocalDate(fechaInicial),
                convertStringToLocalDate(fechaFinal));
    }



}
