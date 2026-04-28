package com.gyl.api_gestionComercio.dto;

public record ClienteResponseDto(
        Long idCliente,
        String nombre,
        String apellido,
        String email,
        String telefono,
        String direccion
) {
}
