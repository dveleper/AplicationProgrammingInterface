package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.neoris.aplicationprogramminginterface.application.service.cliente.ClienteService;
import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Slf4j
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> saveClient(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crear(cliente), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClients() {
        return new ResponseEntity<>(this.clienteService.obtenerClientes(), HttpStatus.OK);
    }
}
