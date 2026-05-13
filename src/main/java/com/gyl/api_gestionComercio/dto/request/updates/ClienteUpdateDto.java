package com.gyl.api_gestionComercio.dto.request.updates;

import jakarta.validation.constraints.Email;

public record ClienteUpdateDto(
        String nombre,
        String apellido,
        @Email(message = "El formato del email no es válido")
        String email,
        String telefono,
        String direccion
) {
}
