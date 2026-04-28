package com.gyl.api_gestionComercio.dto;

public record TipoProductoResponseDto(
        Long idTipoProducto,
        String nombre,
        String descripcion
) {
}
