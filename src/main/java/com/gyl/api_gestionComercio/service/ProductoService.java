package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.ProductoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {

    ProductoResponseDto crearProducto(ProductoRequestDto dto);

    Page<ProductoResponseDto> obtenerTodosLosProductos(Pageable pageable);

    ProductoResponseDto obtenerProductoPorId(Long id);

    ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto dto);

    void eliminarProducto(Long id);
}
