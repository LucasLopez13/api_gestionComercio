package com.gyl.api_gestionComercio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroRequestDTO (
        @NotBlank(message = "El nombre de usuario es obligatorio y no puede estar vacío")
        String username,
        @Email
        String email,
        @NotBlank(message = "La contraseña es obligatoria y no puede estar vacía")
        String password
) {}