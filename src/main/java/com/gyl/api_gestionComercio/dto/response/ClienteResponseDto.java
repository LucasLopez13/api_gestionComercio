package com.gyl.api_gestionComercio.dto.response;

public record ClienteResponseDto(
        Long idCliente,
        String nombre,
        String apellido,
        String email,
        String telefono,
        String direccion
) {
}
