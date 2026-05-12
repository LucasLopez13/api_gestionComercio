package com.gyl.api_gestionComercio.mapper;

import com.gyl.api_gestionComercio.dto.request.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.response.ProductoResponseDto;
import com.gyl.api_gestionComercio.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "tipoProducto", ignore = true)
    Producto toEntity(ProductoRequestDto requestDTO);

    @Mapping(source = "tipoProducto.idTipoProducto", target = "idTipoProducto")
    @Mapping(source = "tipoProducto.nombre", target = "nombreTipoProducto")
    ProductoResponseDto toResponseDto(Producto producto);

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "tipoProducto", ignore = true)
    void updateEntityFromDTO(ProductoRequestDto requestDTO, @MappingTarget Producto producto);
}
