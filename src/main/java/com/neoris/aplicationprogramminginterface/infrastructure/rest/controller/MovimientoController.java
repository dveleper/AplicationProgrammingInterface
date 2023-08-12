package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.neoris.aplicationprogramminginterface.application.service.movimiento.MovimientoService;
import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos")
@Slf4j
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<Movimiento> saveTransaction(@RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(this.movimientoService.crear(movimiento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Movimiento>> getTransactions() {
        return new ResponseEntity<>(this.movimientoService.obtenerMovimientos(), HttpStatus.OK);
    }

    @GetMapping("{movimientoId}")
    public ResponseEntity<Movimiento> getTransactionById(@PathVariable UUID movimientoId){
        return new ResponseEntity<>(this.movimientoService.obtenerMovimientoPorId(movimientoId), HttpStatus.OK);
    }

    @DeleteMapping("{movimientoId}")
    public void deleteTransaction(@PathVariable UUID movimientoId) {
        this.movimientoService.eliminar(movimientoId);
    }

    @PutMapping("{movimientoId}")
    public ResponseEntity<Movimiento> updateTransaction(@PathVariable UUID movimientoId, @RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(this.movimientoService.editar(movimiento, movimientoId), HttpStatus.OK);
    }
}
