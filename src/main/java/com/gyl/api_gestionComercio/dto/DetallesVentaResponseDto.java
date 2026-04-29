package com.gyl.api_gestionComercio.dto;

import java.math.BigDecimal;

public record DetallesVentaResponseDto(
        Long idDetalleVenta,
        Long idProducto,
        String nombreProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {
}
