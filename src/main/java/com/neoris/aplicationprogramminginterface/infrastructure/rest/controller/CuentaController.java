package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.neoris.aplicationprogramminginterface.application.service.cuenta.CuentaService;
import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cuentas")
@Slf4j
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<Cuenta> saveAccount(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(this.cuentaService.crear(cuenta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Cuenta>> getAccounts() {
        return new ResponseEntity<>(this.cuentaService.obtenerCuentas(), HttpStatus.OK);
    }

    @GetMapping("/id/{cuentaId}")
    public ResponseEntity<Cuenta> getAccountById(@PathVariable UUID cuentaId) {
        return new ResponseEntity<>(this.cuentaService.obtenerCuentaPorId(cuentaId), HttpStatus.OK);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Cuenta> getAccountByNumber(@PathVariable String numero) {
        return new ResponseEntity<>(this.cuentaService.obtenerCuentaPorNumero(numero), HttpStatus.OK);
    }

    @DeleteMapping("/{cuentaId}")
    public void deleteAccount(@PathVariable UUID cuentaId) {
        this.cuentaService.eliminar(cuentaId);
    }

    @PutMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> updateAccount(@PathVariable UUID cuentaId, @RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(this.cuentaService.editar(cuenta, cuentaId), HttpStatus.OK);
    }
}
