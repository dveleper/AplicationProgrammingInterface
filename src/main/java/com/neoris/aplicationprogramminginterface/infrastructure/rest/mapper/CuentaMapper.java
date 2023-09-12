package com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper;

import com.neoris.aplicationprogramminginterface.domain.model.Cuenta;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.CuentaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    Cuenta toCuenta(CuentaEntity cuentaEntity);
    Iterable<Cuenta> toCuentas(Iterable<CuentaEntity> cuentas);
    @InheritInverseConfiguration
    CuentaEntity toCuentaEntity(Cuenta cuenta);
}
