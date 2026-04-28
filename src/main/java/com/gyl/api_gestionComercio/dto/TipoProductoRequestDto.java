package com.gyl.api_gestionComercio.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoProductoRequestDto(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        String descripcion

) {
}
