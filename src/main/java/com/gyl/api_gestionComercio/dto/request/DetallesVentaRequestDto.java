package com.gyl.api_gestionComercio.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallesVentaRequestDto(

        @NotNull(message = "El idProducto es obligatorio")
        Long idProducto,
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
        Integer cantidad

) {
}
