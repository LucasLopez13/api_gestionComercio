package com.gyl.api_gestionComercio.mapper;

import com.gyl.api_gestionComercio.dto.request.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.ProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.ProductoResponseDto;
import com.gyl.api_gestionComercio.entity.Producto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "tipoProducto", ignore = true)
    Producto toEntity(ProductoRequestDto requestDTO);

    @Mapping(source = "tipoProducto.idTipoProducto", target = "idTipoProducto")
    @Mapping(source = "tipoProducto.nombre", target = "nombreTipoProducto")
    ProductoResponseDto toResponseDto(Producto producto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "tipoProducto", ignore = true)
    void updateEntityFromDTO(ProductoUpdateDto requestDTO, @MappingTarget Producto producto);
}
