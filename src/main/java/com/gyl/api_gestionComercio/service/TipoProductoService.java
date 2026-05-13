package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.request.TipoProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.TipoProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.TipoProductoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoProductoService {

    TipoProductoResponseDto crearTipoProducto(TipoProductoRequestDto dto);

    Page<TipoProductoResponseDto> obtenerTodosLosTiposProductos(Pageable pageable);

    TipoProductoResponseDto obtenerTipoProductoPorId(Long id);

    TipoProductoResponseDto actualizarTipoProducto(Long id, TipoProductoUpdateDto dto);

    void eliminarTipoProducto(Long id);
}
