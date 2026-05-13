package com.gyl.api_gestionComercio.dto.request.updates;

import java.math.BigDecimal;

public record ProductoUpdateDto(
        String nombre,
        BigDecimal precio,
        Integer stock,
        Long idTipoProducto
) {
}
