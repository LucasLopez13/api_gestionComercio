package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.request.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.ProductoUpdateDto;
import com.gyl.api_gestionComercio.dto.response.ProductoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    ProductoResponseDto crearProducto(ProductoRequestDto dto);

    Page<ProductoResponseDto> obtenerTodosLosProductos(Pageable pageable);

    ProductoResponseDto obtenerProductoPorId(Long id);

    ProductoResponseDto actualizarProducto(Long id, ProductoUpdateDto dto);

    void eliminarProducto(Long id);
}
