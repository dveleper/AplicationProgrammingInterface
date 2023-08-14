package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.aplicationprogramminginterface.application.service.cliente.ClienteService;
import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente;
    private List clientes;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        buildCliente();
        buildClientes();
    }

    @Test
    void crear() throws Exception {
        when(clienteService.crear(any())).thenReturn(cliente);
        mockMvc.perform(post("/api/clientes").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre", is("felix the cat")))
                .andExpect(jsonPath("$.identificacion", is("123")));
        verify(clienteService).crear(any());
    }

    @Test
    void obtenerClientePorId() throws Exception {
        when(clienteService.obtenerClientePorId(UUID.randomUUID())).thenReturn(cliente);
        mockMvc.perform(get("/api/clientes/{clienteId}", UUID.fromString("a2720191-1cc6-22eb-9a2c-107d1a24f955")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(clienteService).obtenerClientePorId(UUID.fromString("a2720191-1cc6-22eb-9a2c-107d1a24f955"));
    }

    @Test
    void getAll() throws Exception {
        when(clienteService.obtenerClientes()).thenReturn(clientes);
        mockMvc.perform(get("/api/clientes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].nombre").value("felix the cat"))
                .andExpect(jsonPath("[0].estado").value("true"))
                .andExpect(jsonPath("[1].nombre").value("max the dog"))
                .andExpect(jsonPath("[1].estado").value("false"));

        verify(clienteService).obtenerClientes();
    }

    @Test
    void update() throws Exception {
        Cliente client = new Cliente();
        client.setNombre("felix of the cat");
        client.setEstado("false");
        when(clienteService.editar(any(), any())).thenReturn(client);
        mockMvc.perform(put("/api/clientes/{clienteId}", UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre", is("felix of the cat")))
                .andExpect(jsonPath("$.estado", is("false")));
        verify(clienteService).editar(any(), any());
    }

    private void buildCliente() {
        cliente = new Cliente();
        cliente.setClienteId(UUID.fromString("a2720191-1cc6-22eb-9a2c-107d1a24f955"));
        cliente.setContrasena("12345678");
        cliente.setGenero("masculino");
        cliente.setEdad(40);
        cliente.setDireccion("cra 3 #76-20 Jardin etapa 1");
        cliente.setNombre("felix the cat");
        cliente.setIdentificacion("123");
        cliente.setEstado("true");
        cliente.setTelefono("0000000000");
    }

    private void buildClientes() {
        Cliente clientOne = new Cliente();
        clientOne.setNombre("felix the cat");
        clientOne.setIdentificacion("123");
        clientOne.setEstado("true");

        Cliente clientTwo = new Cliente();
        clientTwo.setNombre("max the dog");
        clientTwo.setIdentificacion("456");
        clientTwo.setEstado("false");

        clientes = Arrays.asList(clientOne, clientTwo);
    }

}