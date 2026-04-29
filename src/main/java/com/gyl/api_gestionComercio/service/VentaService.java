package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.VentaRequestDto;
import com.gyl.api_gestionComercio.dto.VentaResponseDto;

import java.util.List;

public interface VentaService {

    VentaResponseDto registrarVenta(VentaRequestDto dto);

    List<VentaResponseDto> obtenerTodasLasVentas();

    VentaResponseDto obtenerVentaPorId(Long id);

    void eliminarVenta(Long id);
}
