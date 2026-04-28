package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.TipoProductoRequestDto;
import com.gyl.api_gestionComercio.dto.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoService {

    TipoProductoResponseDto crearTipoProducto(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> obtenerTodosLosTiposProductos();

    TipoProductoResponseDto obtenerTipoProductoPorId(Long id);

    TipoProductoResponseDto actualizarTipoProducto(Long id, TipoProductoRequestDto dto);

    void eliminarTipoProducto(Long id);
}
