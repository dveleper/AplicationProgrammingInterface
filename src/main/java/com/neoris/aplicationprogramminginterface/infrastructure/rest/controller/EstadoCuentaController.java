package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.neoris.aplicationprogramminginterface.application.service.estado_cuenta.EstadoCuentaService;
import com.neoris.aplicationprogramminginterface.domain.model.EstadoCuenta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

import static com.neoris.aplicationprogramminginterface.infrastructure.Utilidades.getDateFormat;

@RestController
@RequestMapping("/api")
@Slf4j
public class EstadoCuentaController {

    private final EstadoCuentaService estadoCuentaService;

    public EstadoCuentaController(EstadoCuentaService estadoCuentaService) {
        this.estadoCuentaService = estadoCuentaService;
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<EstadoCuenta>> getEstadoCuenta(@RequestParam(name = "ini") String fecha_inicial,
                                                              @RequestParam(name = "fin") String fecha_final) {
        try {
           return new ResponseEntity<>(this.estadoCuentaService.getEstadoCuentaPorFechas("123654",
                    getDateFormat(fecha_inicial),
                    getDateFormat(fecha_final)), HttpStatus.OK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
