package com.gyl.api_gestionComercio.service;

import com.gyl.api_gestionComercio.dto.ProductoRequestDto;
import com.gyl.api_gestionComercio.dto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {

    ProductoResponseDto crearProducto(ProductoRequestDto dto);

    List<ProductoResponseDto> obtenerTodosLosProductos();

    ProductoResponseDto obtenerProductoPorId(Long id);

    ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto dto);

    void eliminarProducto(Long id);
}
