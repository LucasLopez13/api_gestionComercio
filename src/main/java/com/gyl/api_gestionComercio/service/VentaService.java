package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.VentaRequestDto;
import com.gyl.api_gestionComercio.dto.VentaResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VentaService {

    VentaResponseDto registrarVenta(VentaRequestDto dto);

    Page<VentaResponseDto> obtenerTodasLasVentas(Pageable pageable);

    VentaResponseDto obtenerVentaPorId(Long id);

    void eliminarVenta(Long id);
}
