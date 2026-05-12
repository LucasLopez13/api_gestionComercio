package com.gyl.api_gestionComercio.dto.response;

import java.time.LocalDateTime;

public record ApiErrorResponseDto (
        LocalDateTime fecha,
        int estado,
        String error,
        String mensaje,
        String path
) {

}
