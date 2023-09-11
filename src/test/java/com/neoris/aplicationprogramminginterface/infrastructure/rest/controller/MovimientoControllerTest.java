package com.neoris.aplicationprogramminginterface.infrastructure.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.aplicationprogramminginterface.application.service.movimiento.MovimientoService;
import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
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

import static com.neoris.aplicationprogramminginterface.domain.model.Movimiento.TipoMovimiento.CREDITO;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovimientoController.class)
class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimientoService movimientoService;

    private Movimiento movimiento;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        buildMovimiento();
        buildMovimientos();
    }

    @Test
    void save() throws Exception {
        when(movimientoService.crear(any())).thenReturn(movimiento);
        mockMvc.perform(post("/api/movimientos").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movimiento)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valor").value(new BigInteger("500000")))
                .andExpect(jsonPath("$.tipo", is(CREDITO.toString())));
        verify(movimientoService).crear(any());
    }


    private void buildMovimiento() {
        movimiento = new Movimiento();
        movimiento.setMovimientoId(UUID.randomUUID());
        movimiento.setTipo(CREDITO);
        movimiento.setSaldo(BigInteger.ZERO);
        movimiento.setValor(new BigInteger("500000"));
        movimiento.setCuenta(buildCuenta());
    }

    private Cuenta buildCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaId(UUID.randomUUID());
        cuenta.setNumero("00000024560");
        cuenta.setTipo("AHORROS");
        cuenta.setSaldoInicial(BigInteger.ZERO);
        cuenta.setEstado("true");
        cuenta.setCliente(buildCliente());
        return cuenta;
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

    private Movimiento buildMovimientoDebito() {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(Movimiento.TipoMovimiento.DEBITO);
        movimiento.setSaldo(new BigInteger("1000"));
        movimiento.setValor(new BigInteger("500"));
        movimiento.setCuenta(buildCuenta());
        return movimiento;
    }

    private Movimiento buildMovimientoCredito() {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(CREDITO);
        movimiento.setSaldo(BigInteger.ZERO);
        movimiento.setValor(new BigInteger("100000"));
        movimiento.setCuenta(buildCuenta());
        return movimiento;
    }

    private List<Movimiento> buildMovimientos() {
        return Arrays.asList(buildMovimientoDebito(), buildMovimientoCredito());
    }

}