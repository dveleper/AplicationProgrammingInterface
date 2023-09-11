package com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.cuenta;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.domain.port.CuentaRepository;
import com.neoris.aplicationprogramminginterface.infrastructure.adaptador.mysql.cliente.ClienteRepositoryMysql;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.ClienteEntity;
import com.neoris.aplicationprogramminginterface.infrastructure.exceptions.ResourceNotFoundException;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.ClienteMapper;
import com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper.CuentaMapper;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;
import java.util.UUID;

public class CuentaRepositoryMysqlImpl implements CuentaRepository {

    private final CuentaRepositoryMysql cuentaRepositoryMysql;
    private final ClienteRepositoryMysql clienteRepositoryMysql;
    private final CuentaMapper cuentaMapper;
    private final ClienteMapper clienteMapper;

    public CuentaRepositoryMysqlImpl(@Lazy CuentaRepositoryMysql cuentaRepositoryMysql,
                                     @Lazy ClienteRepositoryMysql clienteRepositoryMysql,
                                     CuentaMapper cuentaMapper, ClienteMapper clienteMapper) {
        this.cuentaRepositoryMysql = cuentaRepositoryMysql;
        this.clienteRepositoryMysql = clienteRepositoryMysql;
        this.cuentaMapper = cuentaMapper;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
        ClienteEntity clienteEntity = clienteRepositoryMysql.findClienteEntityByIdentificacion(cuenta.getCliente().getIdentificacion())
                .orElseThrow(() -> new ResourceNotFoundException("El numero de identificacion no existe en el sistema"));
        cuenta.setCliente(clienteMapper.toCliente(clienteEntity));
        return cuentaMapper.toCuenta(
                cuentaRepositoryMysql.save(
                        cuentaMapper.toCuentaEntity(cuenta))
        );
    }

    @Override
    public Cuenta editar(Cuenta cuenta, UUID cuentaId) {
        return cuentaRepositoryMysql.findById(cuentaId).map(cuenta_ -> {
                    cuenta.setCuentaId(cuentaId);
                    cuenta.setCliente(clienteMapper.toCliente(cuenta_.getCliente()));
                    return cuentaMapper.toCuenta(
                            cuentaRepositoryMysql.save(
                                    cuentaMapper.toCuentaEntity(cuenta)));

                })
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado"));
    }

    @Override
    public boolean eliminar(UUID cuentaId) {
        return cuentaRepositoryMysql.findById(cuentaId)
                .map(cuentaEntity -> {
                    cuentaRepositoryMysql.delete(cuentaEntity);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado"));
    }

    @Override
    public Iterable<Cuenta> obtenerCuentas() {
        return cuentaMapper.toCuentas(cuentaRepositoryMysql.findAll());
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorId(UUID cuentaId) {
        return Optional.of(cuentaRepositoryMysql.findById(cuentaId)
                .map(cuentaMapper::toCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado")));
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorNumero(String numero) {
        return Optional.of(cuentaRepositoryMysql.findCuentaEntityByNumero(numero)
                .map(cuentaMapper::toCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado")));
    }
}
