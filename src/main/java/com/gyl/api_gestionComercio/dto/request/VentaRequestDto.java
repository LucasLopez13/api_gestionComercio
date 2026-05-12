package com.gyl.api_gestionComercio.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VentaRequestDto(

        @NotNull(message = "El idCliente es obligatorio")
        Long idCliente,

        @NotEmpty(message = "El detalle de la venta es obligatorio")
        @Valid
        List<DetallesVentaRequestDto> detallesVenta
) {
}
