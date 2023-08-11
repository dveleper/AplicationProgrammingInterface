package com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper;

import com.neoris.aplicationprogramminginterface.domain.model.Movimiento;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.MovimientoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);
    Movimiento toMovimiento(MovimientoEntity movimientoEntity);
    Iterable<Movimiento> toMovimientos(Iterable<MovimientoEntity> movimientos);
    @InheritInverseConfiguration
    MovimientoEntity toMovimientoEntity(Movimiento movimiento);
}
