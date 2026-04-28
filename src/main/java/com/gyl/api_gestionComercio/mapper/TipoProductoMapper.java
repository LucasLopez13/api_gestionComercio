package com.gyl.api_gestionComercio.mapper;

import com.gyl.api_gestionComercio.dto.TipoProductoRequestDto;
import com.gyl.api_gestionComercio.dto.TipoProductoResponseDto;
import com.gyl.api_gestionComercio.entity.TipoProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {

    TipoProducto toEntity(TipoProductoRequestDto requestDTO);

    TipoProductoResponseDto toResponseDto(TipoProducto tipoProducto);

    @Mapping(target = "idTipoProducto", ignore = true)
    @Mapping(target = "productos", ignore = true)
    void updateEntityFromDTO(TipoProductoRequestDto requestDTO, @MappingTarget TipoProducto tipoProducto);
}
