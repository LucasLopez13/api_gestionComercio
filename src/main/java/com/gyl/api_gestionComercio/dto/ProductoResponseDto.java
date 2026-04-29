package com.gyl.api_gestionComercio.dto;

import java.math.BigDecimal;

public record ProductoResponseDto(
        Long idProducto,
        String nombre,
        BigDecimal precio,
        Integer stock,
        Long idTipoProducto,
        String nombreTipoProducto
) {
}
