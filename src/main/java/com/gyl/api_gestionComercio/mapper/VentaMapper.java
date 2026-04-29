package com.gyl.api_gestionComercio.mapper;

import com.gyl.api_gestionComercio.dto.DetallesVentaResponseDto;
import com.gyl.api_gestionComercio.dto.VentaResponseDto;
import com.gyl.api_gestionComercio.entity.DetalleVenta;
import com.gyl.api_gestionComercio.entity.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(source = "cliente.idCliente", target = "idCliente")
    @Mapping(source = "cliente.nombre", target = "nombreCliente")
    @Mapping(source = "detalles", target = "detallesVenta")
    VentaResponseDto toResponseDto(Venta venta);

    @Mapping(source = "producto.idProducto", target = "idProducto")
    @Mapping(source = "producto.nombre", target = "nombreProducto")
    DetallesVentaResponseDto toDetallesVentaResponseDto(DetalleVenta detalleVenta);
}
