package com.gyl.api_gestionComercio.mapper;

import com.gyl.api_gestionComercio.dto.request.TipoProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.TipoProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.TipoProductoResponseDto;
import com.gyl.api_gestionComercio.entity.TipoProducto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {

    TipoProducto toEntity(TipoProductoRequestDto requestDTO);

    TipoProductoResponseDto toResponseDto(TipoProducto tipoProducto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idTipoProducto", ignore = true)
    @Mapping(target = "productos", ignore = true)
    void updateEntityFromDTO(TipoProductoUpdateDto requestDTO, @MappingTarget TipoProducto tipoProducto);
}
