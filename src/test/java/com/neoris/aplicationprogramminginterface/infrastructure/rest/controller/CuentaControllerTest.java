package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.aplicationprogramminginterface.application.service.cuenta.CuentaService;
import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    private Cuenta cuenta;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        buildCuenta();
    }

    @Test
    void save() throws Exception {
        when(cuentaService.crear(any())).thenReturn(cuenta);
        mockMvc.perform(post("/api/cuentas").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numero", is("00000024560")))
                .andExpect(jsonPath("$.tipo", is("AHORROS")));
        verify(cuentaService).crear(any());
    }

    @Test
    void getByNumeroCuenta() throws Exception {
        when(cuentaService.obtenerCuentaPorNumero(anyString())).thenReturn(cuenta);
        mockMvc.perform(get("/api/cuentas/00000024560").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tipo").value("AHORROS"))
                .andExpect(jsonPath("$.cliente.identificacion").value("123"));
        verify(cuentaService).obtenerCuentaPorNumero("00000024560");
    }

    @Test
    void getAll() throws Exception {
        when(cuentaService.obtenerCuentas()).thenReturn(buildCuentas());
        mockMvc.perform(get("/api/cuentas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].tipo").value("AHORROS"))
                .andExpect(jsonPath("[0].estado").value("true"))
                .andExpect(jsonPath("[1].tipo").value("CORRIENTE"))
                .andExpect(jsonPath("[1].estado").value("false"));
        verify(cuentaService).obtenerCuentas();
    }


    private void buildCuenta() {
        cuenta = new Cuenta();
        cuenta.setCuentaId(UUID.randomUUID());
        cuenta.setNumero("00000024560");
        cuenta.setTipo("AHORROS");
        cuenta.setSaldoInicial(BigInteger.ZERO);
        cuenta.setEstado("true");
        cuenta.setCliente(buildCliente());
    }

    private Cliente buildCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(UUID.randomUUID());
        cliente.setContrasena("12345678");
        cliente.setGenero("masculino");
        cliente.setEdad(40);
        cliente.setDireccion("cra 3 #76-20 Jardin etapa 1");
        cliente.setNombre("felix the cat");
        cliente.setIdentificacion("123");
        cliente.setEstado("true");
        cliente.setTelefono("0000000000");
        return cliente;
    }

    private List<Cuenta> buildCuentas() {
        Cuenta cuentaOne = new Cuenta();
        cuenta.setCuentaId(UUID.randomUUID());
        cuentaOne.setNumero("00000024560");
        cuentaOne.setTipo("AHORROS");
        cuentaOne.setSaldoInicial(BigInteger.ZERO);
        cuentaOne.setEstado("true");
        cuentaOne.setCliente(buildCliente());

        Cuenta cuentaTwo = new Cuenta();
        cuentaTwo.setCuentaId(UUID.randomUUID());
        cuentaTwo.setNumero("00000024561");
        cuentaTwo.setTipo("CORRIENTE");
        cuentaTwo.setSaldoInicial(BigInteger.ZERO);
        cuentaTwo.setEstado("false");
        cuentaTwo.setCliente(buildCliente());

        return Arrays.asList(cuentaOne, cuentaTwo);
    }

}