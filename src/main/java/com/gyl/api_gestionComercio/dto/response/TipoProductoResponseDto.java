package com.gyl.api_gestionComercio.dto.response;

public record TipoProductoResponseDto(
        Long idTipoProducto,
        String nombre,
        String descripcion
) {
}
