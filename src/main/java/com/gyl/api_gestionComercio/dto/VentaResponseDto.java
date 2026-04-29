package com.gyl.api_gestionComercio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VentaResponseDto(
        Long idVenta,
        LocalDate fechaVenta,
        BigDecimal total,
        Long idCliente,
        String nombreCliente,
        List<DetallesVentaResponseDto> detallesVenta

) {
}
