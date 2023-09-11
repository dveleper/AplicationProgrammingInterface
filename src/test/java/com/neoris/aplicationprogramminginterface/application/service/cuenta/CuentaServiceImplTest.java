package com.neoris.aplicationprogramminginterface.application.service.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.port.CuentaRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CuentaServiceImplTest {

    @Mock
    private CuentaRepository repository;
    private Cuenta cuenta;
    private UUID cuentaId;
    private Cliente cliente;
    private BigInteger saldoActual;

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        crearCliente();
        crearCuenta();
        crearCuentaId();
        saldoActual = BigInteger.valueOf(600);
    }

    private Iterable crearCuentas() {
        return Collections.singletonList(cuenta).stream().toList();
    }

    private void crearCuentaId() {
        cuentaId = UUID.randomUUID();
    }

    private void crearCuenta() {
        cuenta = new Cuenta(
                cuentaId,
                "00000024560",
                "AHORROS",
                saldoActual,
                "activa",
                cliente
        );
    }

    private void crearCliente() {
        cliente = new Cliente();
        cliente.setNombre("José Lema");
        cliente.setIdentificacion("14265789");
        cliente.setGenero("masculino");
        cliente.setEdad(25);
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setContrasena("1234");
        cliente.setEstado("true");
    }

    @Test
    public void crearCuentaTest() {
        when(repository.crear(any(Cuenta.class))).thenReturn(cuenta);
        Assertions.assertNotNull(cuentaService.crear(cuenta));
    }

    @Test
    public void editarCuentaTest() {
        when(repository.editar(any(Cuenta.class), any(UUID.class))).thenReturn(cuenta);
        Assertions.assertNotNull(cuentaService.editar(cuenta, UUID.randomUUID()));
    }

    @Test
    public void eliminarCuentaTest() {
        when(repository.eliminar(any(UUID.class))).thenReturn(true);
        assertTrue(cuentaService.eliminar(UUID.randomUUID()));
    }

    @Test
    public void obtenerCuentasTest() {
        when(repository.obtenerCuentas()).thenReturn(crearCuentas());
        assertNotNull(cuentaService.obtenerCuentas());
    }

    @Test
    public void obtenerCuentaPorIdTest() {
        when(repository.obtenerCuentaPorId(any(UUID.class))).thenReturn(Optional.of(cuenta));
        assertEquals(cuentaService.obtenerCuentaPorId(cuentaId).getNumero(), "00000024560"); ;
    }

    @Test
    public void obtenerCuentaPorNumeroTest() {
        when(repository.obtenerCuentaPorNumero(anyString())).thenReturn(Optional.of(cuenta));
        assertEquals(cuentaService.obtenerCuentaPorNumero("00000024560").getCliente().getIdentificacion(), "14265789"); ;
    }

    @Test
    public void realizarDebitoTest() {
        BigInteger saldoCuenta = cuentaService.realizarDebito(saldoActual, BigInteger.valueOf(-575));
        assertEquals(BigInteger.valueOf(25), saldoCuenta);
    }

    @Test
    public void realizarCreditoTest() {
        BigInteger saldoCuenta = cuentaService.realizarCredito(saldoActual, BigInteger.valueOf(7000));
        assertEquals(BigInteger.valueOf(7600), saldoCuenta);
    }

    @Test
    public void actualizarSaldoTest() {
        when(repository.obtenerCuentaPorNumero(anyString())).thenReturn(Optional.of(cuenta));
        when(repository.editar(any(Cuenta.class), any(UUID.class))).thenReturn(cuenta);
        cuentaService.actualizarSaldo("00000024560", BigInteger.valueOf(10000));
        assertEquals(cuenta.getSaldoInicial(), BigInteger.valueOf(10000));
        assertEquals(cuenta.getCliente().getTelefono(), "098254785");
        assertEquals(cuenta.getEstado(), "activa");
    }

    @Test
    public void whenBusinessExceptionDebitNegativeAmountTest() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            cuentaService.realizarDebito(saldoActual, BigInteger.valueOf(1000));
        });
        assertEquals("El monto para DEBITO debe ser negativo", exception.getMessage());
    }

    @Test
    public void whenBusinessExceptionDebitBalanceNotAvailableTest() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            cuentaService.realizarDebito(saldoActual, BigInteger.valueOf(-1000));
        });
        assertEquals("Saldo no disponible", exception.getMessage());
    }

    @Test
    public void whenBusinessExceptionCreditAmountPositiveTest() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            cuentaService.realizarCredito(saldoActual, BigInteger.valueOf(-800));
        });
        assertEquals("El monto para CREDITO debe ser positivo", exception.getMessage());
    }

}